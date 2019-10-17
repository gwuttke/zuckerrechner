package de.gw.android.phone.zuckerrechner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.gw.core.zuckerrechner.Zuckerrechner;

public class Start extends AppCompatActivity {

    private Zuckerrechner zuckerrechner = new Zuckerrechner();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

       final EditText kolenhydrate = (EditText)  findViewById(R.id.phoneKohlenhydrate);

       final EditText eiweis= (EditText)  findViewById(R.id.phoneEiweis);

       final EditText fett= (EditText)  findViewById(R.id.phoneFett);

       final Button btnCalc = (Button) findViewById(R.id.phoneButtonCalc);

       final TextView phoneErgebnis= (TextView) findViewById(R.id.phoneErgebnis);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double eiweisWert = toDouble(eiweis.getText().toString());
                double fettWert = toDouble(fett.getText().toString());
                double khWert = toDouble(kolenhydrate.getText().toString());
                double ergebnis = zuckerrechner.calcFPE(fettWert,eiweisWert);
                String text = zuckerrechner.concatKhFpe(khWert,ergebnis);

                phoneErgebnis.setText(text);
            }
        });
    }

    private double toDouble(String s){
        return (s==null || s.length()==0)?0d:Double.valueOf(s);
    }
}
