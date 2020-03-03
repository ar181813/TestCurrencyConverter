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
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView resultTextBox;
    Button calcButton;
    EditText dollarNum;
    Spinner spinOps;
    String[] currencies={"Euro","Yuan","Pounds","Rupies","Yen"};
    String currencySelected;
    double currency;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextBox = findViewById(R.id.amount_total);

        calcButton = findViewById(R.id.calculate_button);
        calcButton.setText("Display result");
        calcButton.setOnClickListener(myClickListener);

        dollarNum = findViewById(R.id.amount_Dollars);
        dollarNum.setText("");

        /* Spinner list of currencies */
        spinOps = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinOps.setAdapter(adapter);
        spinOps.setOnItemSelectedListener(this);
    }

    private View.OnClickListener myClickListener = new View.OnClickListener()
    {
        public void onClick(View v) {
            EditText etDollars = findViewById(R.id.amount_Dollars);
            String strDollars = etDollars.getText().toString();

            double mets[] = {0.93,7.02,0.78,71.88,112.07};
            //euro, yuan, pounds, rupies, yen

            if (currencySelected=="Euro"){currency=mets[0]; resultTextBox.setText(Double.toString(currency));}
            else if (currencySelected=="Yuan"){currency=mets[1]; resultTextBox.setText(Double.toString(currency));}
            else if (currencySelected=="Pounds"){currency=mets[2]; resultTextBox.setText(Double.toString(currency));}
            else if (currencySelected=="Rupies"){currency=mets[3]; resultTextBox.setText(Double.toString(currency));}
            else if (currencySelected=="Yen"){currency=mets[4]; resultTextBox.setText(Double.toString(currency));}
            else {resultTextBox.setText("Please select the operation");}

            Double dblDollars = Double.parseDouble(strDollars);
            Double calc = dblDollars * currency;
            DecimalFormat df = new DecimalFormat("0.00");

            resultTextBox.setText(df.format(calc) + " " + currencySelected);
           // resultTextBox.setText(String.format("%.0f", calc));
        }
    };

    public void onItemSelected (AdapterView<?> p,View v,int position,long id) {
        currencySelected=currencies[position];
        resultTextBox.setText("You have selected " + currencySelected);
    }
    public void onNothingSelected(AdapterView<?> p) {
        resultTextBox.setText("Please select ops");
    }
}