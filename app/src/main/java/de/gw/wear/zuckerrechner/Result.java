package de.gw.wear.zuckerrechner;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

public class Result extends WearableActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mTextView = (TextView) findViewById(R.id.text);

        Intent intent = getIntent();

        String result = intent.getStringExtra(MainActivity.EXTRA_DATA_RESULT);

        mTextView.setText(result);
        // Enables Always-on
        setAmbientEnabled();
    }
}
