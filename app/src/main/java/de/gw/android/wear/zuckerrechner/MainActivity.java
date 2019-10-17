package de.gw.android.wear.zuckerrechner;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import de.gw.core.zuckerrechner.Zuckerrechner;
public class MainActivity extends WearableActivity {

    private EditText kolenhydrate;

    private EditText eiweis;

    private EditText fett;

    private Button btnCalc;

    private Zuckerrechner zuckerrechner = new Zuckerrechner();

    public static final String EXTRA_DATA_RESULT="extra_data_result";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kolenhydrate = (EditText)  findViewById(R.id.editTextKH);
        eiweis = (EditText)  findViewById(R.id.editTextEiweis);
        fett = (EditText)  findViewById(R.id.editTextFett);

        btnCalc = (Button) findViewById(R.id.buttonBerechnen);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double eiweisWert = Double.valueOf(eiweis.getText().toString());
                double fettWert = Double.valueOf(fett.getText().toString());
                double khWert = Double.valueOf(kolenhydrate.getText().toString());
                double ergebnis = zuckerrechner.calcFPE(fettWert,eiweisWert);
                goToResult(zuckerrechner.concatKhFpe(khWert,ergebnis));
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


}
