package com.altice.bmicalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /**
     * Locks "?" button while
     * BMI it's not calculated.
     */
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Function that allows us to calculate
     * BMI.
     *
     * Note: The function is called when
     * user press the "Calculate BMI"
     * button.
     *
     * @param view - actual view
     */
    public void calcBMI(View view) {
        // Do something in response to button click
        TextView txtResult = (TextView) findViewById(R.id.textViewResult);
        EditText txtWeight = (EditText) findViewById(R.id.editTextWeight);
        EditText txtHeightFeet = (EditText) findViewById(R.id.editTextHeightFeet);
        EditText txtHeightInches = (EditText) findViewById(R.id.editTextHeightInches);

        try{
            //Imperial Us Method = (weightpounds * 703 / height in inches ^ 2)
            final double imperialUS = 703;
            double weight = Double.parseDouble(txtWeight.getText().toString());
            int heightFeet = Integer.parseInt(txtHeightFeet.getText().toString());
            int heightInches = Integer.parseInt(txtHeightInches.getText().toString());
            double result = (weight * imperialUS) / Math.pow(((heightFeet * 12) + heightInches), 2);
            txtResult.setText(String.format("%.1f", result));
            flag = true;

        } catch (NumberFormatException e){
            Log.d("Fail", "Error: " + e);
        }

    }

    /**
     * Function that allows to
     * change to other page.
     *
     * @param view - actual view.
     */
    public void changePage(View view) {
        if (flag) {
            TextView txtResult = (TextView) findViewById(R.id.textViewResult);
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("imcResult", txtResult.getText());
            startActivity(intent);
        } else {
            Log.d("BMI: ", "BMI not calculated.");
        }
    }
}
