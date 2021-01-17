package com.example.weatherreport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.core.content.ContextCompat;

import java.util.ArrayList;


public class ReportAdapter extends ArrayAdapter<Data> {

    public ReportAdapter(Activity context, ArrayList<Data> data){
        super(context,0, data);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem= convertView;
        if(listItem==null){
            listItem=LayoutInflater.from(getContext()).inflate(R.layout.custom_layout,parent,false);
        }
        Data curWord=getItem(position);


        TextView Context=(TextView) listItem.findViewById(R.id.context);
        TextView Value=(TextView) listItem.findViewById(R.id.value);
        TextView Text=(TextView) listItem.findViewById(R.id.text);
        TextView Detail=(TextView) listItem.findViewById(R.id.detail);


        if(curWord.getContext()==null){
            Context.setVisibility(View.GONE);
        }
        else{
            Context.setText(curWord.getContext());
        }

        if(curWord.getValue()==null){
            Value.setVisibility(View.GONE);
        }else{
            Value.setText(curWord.getValue());
        }

        if(curWord.getAlert()!=null){

            GradientDrawable circle = (GradientDrawable) Text.getBackground();
            int color=getColorId(curWord.getText());
            circle.setColor(color);
            Text.setText(curWord.getText());
            Detail.setText(curWord.getAlert());
            curWord.Alert(null);
        }else{
            Detail.setVisibility(View.GONE);
            Text.setVisibility(View.GONE);
        }



        return listItem;
    }
    public int getColorId(String str){
        int color;
        switch(str){
            case "Severe":
                color=R.color.L8;
                break;
            case "Poor":
                color=R.color.L5;
                break;
            case "Good":
                color=R.color.L4;
                break;
            case "Satisfactory":
                color=R.color.L3;
                break;
            default:color=R.color.L9;
                break;

        }

        return ContextCompat.getColor(getContext(),color);
    }

}




