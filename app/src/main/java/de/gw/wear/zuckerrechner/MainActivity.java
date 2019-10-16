package de.gw.wear.zuckerrechner;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends WearableActivity {

    private TextView mTextView;

    private EditText kolenhydrate;

    private EditText eiweis;

    private EditText fett;

    private Button btnCalc;

    public static final String EXTRA_DATA_RESULT="extra_data_result";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);
        kolenhydrate = (EditText)  findViewById(R.id.editTextKH);
        eiweis = (EditText)  findViewById(R.id.editTextEiweis);
        fett = (EditText)  findViewById(R.id.editTextFett);

        btnCalc = (Button) findViewById(R.id.buttonBerechnen);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double eiweisWert = Double.valueOf(eiweis.getText().toString());
                double fettWert = Double.valueOf(fett.getText().toString());
                double khWert = Double.valueOf(kolenhydrate.getText().toString());
                double ergebnis = calcFPE(fettWert,eiweisWert);
                goToResult(concatKhFpe(khWert,ergebnis));
            }
        });

        // Enables Always-on
        setAmbientEnabled();
    }

    private void goToResult(String result){
        Intent intent = new Intent(this,Result.class);
        intent.putExtra(EXTRA_DATA_RESULT, result);
        startActivity(intent);
    }

    private String concatKhFpe(double kh,double fpe){
        return String.format("%s %s",khString(kh),fpeString(fpe));
    }

    private String khString(double kh){
       return String.format("Bitte für  %.2f sofort und",kh/12);
    }

    private String fpeString(double fpe){
        String ergStr =  "Bitte für %.2f, verzögert auf %s Std. Spritzen";
        int hours =0;
        if(fpe<=1){
            return "kein verzögertes Insolin nötig";
        }else if(fpe >1 && fpe <=2){
            return String.format(ergStr, fpe,"4");
        }else if(fpe >2 && fpe <3){
            return String.format(ergStr, fpe,"5");
        }else{
          return  String.format(ergStr, fpe,"6-8");
        }
    }

    private double calcFPE(double fett, double eiweis){
        return fett/11+eiweis/25;
    }
}
