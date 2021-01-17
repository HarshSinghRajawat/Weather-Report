package com.example.weatherreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView button=(TextView) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                /*if("Invalid".equals(err)){
                    Toast.makeText(view.getContext(), "Enter Vaild Input", Toast.LENGTH_SHORT).show();

                }*/
                EditText input1 = (EditText) findViewById(R.id.lon);
                EditText input2 = (EditText) findViewById(R.id.lat);
                String lon = input1.getText().toString();
                String lat = input2.getText().toString();
                Toast.makeText(view.getContext(), "Please Wait", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Report.class);
                intent.putExtra("lon", lon);
                intent.putExtra("lat", lat);
                startActivity(intent);
            }
        });
    }
}