package com.example.tipcalc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView resultTextBox;
    Button calcButton;
    EditText weightNum, timeNum;
    Spinner spinOps;
    String[] activities={"Aerobics","Bicycling","Dancing","Running","Swimming", "Walking"};
    String activitySelected;
    double activity;
    double kgInLbs = 2.2;
    int minutesInHour = 60;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextBox = findViewById(R.id.amount_total);

        calcButton = findViewById(R.id.calculate_button);
        calcButton.setText("Display result");
        calcButton.setOnClickListener(myClickListener);

        weightNum = findViewById(R.id.amount_weight);
        timeNum = findViewById(R.id.amount_time);

        timeNum.setText("");
        weightNum.setText("");

        /* Spinner list of activities */
        spinOps = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, activities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinOps.setAdapter(adapter);
        spinOps.setOnItemSelectedListener(this);

    }

    private View.OnClickListener myClickListener = new View.OnClickListener()
    {
        public void onClick(View v) {
            EditText etWeight = (EditText) findViewById(R.id.amount_weight);
            EditText etTime = (EditText) findViewById(R.id.amount_time);

            String strWeight = etWeight.getText().toString();
            String strTime = etTime.getText().toString();

            double mets[] = {7.3,7.5,5,9.8,6,4.3};
            //aerobics, bicycling, dancing, running, swimming, walking

            if (activitySelected=="Aerobics"){activity=mets[0]; resultTextBox.setText(Double.toString(activity));}
            else if (activitySelected=="Bicycling"){activity=mets[1]; resultTextBox.setText(Double.toString(activity));}
            else if (activitySelected=="Dancing"){activity=mets[2]; resultTextBox.setText(Double.toString(activity));}
            else if (activitySelected=="Running"){activity=mets[3]; resultTextBox.setText(Double.toString(activity));}
            else if (activitySelected=="Swimming"){activity=mets[4]; resultTextBox.setText(Double.toString(activity));}
            else if (activitySelected=="Walking"){activity=mets[5]; resultTextBox.setText(Double.toString(activity));}
            else {resultTextBox.setText("Please select the operation");}

            Double dblWt = Double.parseDouble(strWeight) / (kgInLbs);
            Double dblTime = Double.parseDouble(strTime) / (minutesInHour);
            Double calc = dblWt * activity * dblTime;

            resultTextBox.setText(String.format("%.0f", calc));
        }
    };

    public void onItemSelected (AdapterView<?> p,View v,int position,long id) {
        activitySelected=activities[position];
        resultTextBox.setText("You have selected " + activitySelected);
    }
    public void onNothingSelected(AdapterView<?> p) {
        resultTextBox.setText("Please select ops");
    }
}