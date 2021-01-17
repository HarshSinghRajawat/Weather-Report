package com.example.weatherreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        WeatherAsyncTask task=new WeatherAsyncTask();task.execute();


    }


    private class WeatherAsyncTask extends AsyncTask<URL,Void,Data> {
        @Override
        protected Data doInBackground(URL... urls) {
            Intent intent= getIntent();
            String lat=(String) intent.getSerializableExtra("lat");
            String lon=(String)intent.getSerializableExtra("lon");
            TextView longitute=(TextView) findViewById(R.id.lon);
            TextView latitute=(TextView) findViewById(R.id.lat);
            String stringURL="http://api.airpollutionapi.com/1.0/aqi?lat="+lat+"&lon="+lon+"&APPID=j4ubmjnp9eaf0r3op6ugc32qne";


            URL url = createURL(stringURL);
            String jsonResponse="";

            try {
                jsonResponse=HttpRequest(url);
            }catch (IOException e) {
                e.printStackTrace();
            }
            Data reponse= JsonFormatter(jsonResponse);
            if(reponse==null){
                return null;
            }
            return reponse;
        }

        @Override
        protected void onPostExecute(Data response) {
            //super.onPostExecute(response);
            if(response==null){
                Intent intent=new Intent(Report.this,MainActivity.class);
                intent.putExtra("Status","Invalid");
                startActivity(intent);
                return;
            }else {
                updateData(response);
            }
        }
    }
    private void updateData(Data res){
        ArrayList<Data> str= new ArrayList<Data>();

        str.add(new Data(res.getText(),null,res.getAlert()));
        str.add(new Data("Color",res.getColor()));
        str.add(new Data("Updated",res.getUpdated()));
        str.add(new Data("Country",res.getCountry()));
        str.add(new Data("Clouds",res.getClouds()));
        str.add(new Data("Co-Ordinates (Longitude And Latitude)",res.getCoOrdinates()));
        str.add(new Data("Source",res.getSource()));
        str.add(new Data("Humidity",res.getHumidity()));
        str.add(new Data("Barometric Pressure",res.getPressure()));
        str.add(new Data("Wind Speed",res.getWindSpeed()));
        str.add(new Data("Wind Direction",res.getWindDirection()));
        ReportAdapter Adapter=new ReportAdapter(this, str);
        ListView list=(ListView)findViewById(R.id.list);
        list.setAdapter(Adapter);
    }

    private Data JsonFormatter(String Response){
        try {
            JSONObject baseResponse= new JSONObject(Response);
            String check=baseResponse.getString("status");
            if("success".equals(check)){
            JSONObject data=baseResponse.getJSONObject("data");
            String alert=data.getString("alert");
            String country=data.getString("country");
            JSONObject Coordinates=data.getJSONObject("coordinates");
            double Lat=Coordinates.getDouble("longitude");
            double Lon=Coordinates.getDouble("latitude");
            JSONObject objectSource=data.getJSONObject("source");
            String source = objectSource.getString("name");
            String color=data.getString("color");
            String Clouds=data.getString("clouds");
            JSONArray Details=data.getJSONArray("aqiParams");
            String humidity=Finder(Details,"Humidity");
            String pressure=Finder(Details,"Barometric Pressure");
            String WindSpeed=Finder(Details,"Wind Speed");
            String WindDirection=Finder(Details,"Wind Direction");
            String updated=data.getString("updated");
            String Text=data.getString("text");


            return new Data(
                    Text,
                    alert,
                    color,
                    updated,
                    country,
                    Clouds,
                    ConvertString(Lon,Lat),
                    source,
                    humidity,
                    pressure,
                    WindSpeed,
                    WindDirection);}
            else {
                return null;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String Finder(JSONArray arr,String find){
        int i;
        JSONObject str;
        String Check="";

        String Value="";
        try {
            for (i = 0; i < arr.length();i++ ) {
                str = arr.optJSONObject(i);
                Check=str.getString("name");
                if(Check.equals(find)){
                    Value=str.getString("value");
                    return Value;
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String ConvertString(double d,double e){
        String str=""+d+"-"+e;
        return str;
    }


    private URL createURL(String StringUrl){
        URL url=null;
        try{
            url=new URL(StringUrl);

        } catch (MalformedURLException exception) {
            Log.e("Error with creating URL", String.valueOf(exception));
            return null;
        }
        return url;
    }
    private String HttpRequest(URL url) throws IOException {
        String jsonResponse="";
        HttpURLConnection UrlConnection=null;
        InputStream inputStream=null;

        try {
            UrlConnection=(HttpURLConnection) url.openConnection();
            UrlConnection.setRequestMethod("GET");
            UrlConnection.setReadTimeout(10000);
            UrlConnection.setConnectTimeout(15000);
            UrlConnection.connect();
            if(UrlConnection.getResponseCode()==200){
                inputStream=UrlConnection.getInputStream();
                jsonResponse=readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(UrlConnection!=null){
                UrlConnection.disconnect();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output=new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream, Charset.forName("UTF_8"));

            BufferedReader reader=new BufferedReader(inputStreamReader);
            String line=reader.readLine();
            while(line!=null){
                output.append(line);
                line=reader.readLine();
            }
        }

        return output.toString();
    }

}