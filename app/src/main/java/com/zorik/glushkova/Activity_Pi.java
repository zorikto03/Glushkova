package com.zorik.glushkova;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Activity_Pi extends AppCompatActivity {
    boolean Running = true;
    String out;
    TextView output_textview, output_count;
    EditText input;
    protected Double Eta;
    protected Double p; //значение пи
    private Double n; // число членов ряда
    private Double chr; // значение члена ряда


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__pi);
        output_textview = findViewById(R.id.output_tv);
        output_count = findViewById(R.id.output_count_n);
        input = findViewById(R.id.input);
        Eta = 0.0;
    }

    public void Button_Calc(View view) {
        //
        if(input.getText().toString()!=""){
            if(Eta > Double.parseDouble(input.getText().toString())){
                Eta = Double.parseDouble(input.getText().toString());

            }
            else{
                chr = 1.0D;
                n = 1.0D;
                p = 3.0D;
                String string = input.getText().toString();
                Eta = Double.parseDouble(string);
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Calculate_Pi();
                }
            }).start();

            input.setText("");
        }
        else
            Toast.makeText(this, "Введите точность вычисления", Toast.LENGTH_SHORT).show();

    }


    public void Calculate_Pi(){

        while(Eta < (Math.abs(chr))) {
            p += chr;
            chr = ((Math.pow(-1,  n)) * (4 / ((2 * n) + 1)));

            n++;
            output_textview.setText(""+p);
            output_count.setText(""+n);
        }
        //output_textview.setText("Pi = "+p+"\n"+n);

    }




}
