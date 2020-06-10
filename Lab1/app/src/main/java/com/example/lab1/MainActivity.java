package com.example.lab1;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    String[] cities = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth"};
    TextView selection;
    String choosed_option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selection = (TextView) findViewById(R.id.selection);
        selection.setText("Nothing was selected");
        Spinner spinner = (Spinner) findViewById(R.id.cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        OnItemSelectedListener itemSelectedListener = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (choosed_option != null || choosed_option!="") {
                    choosed_option = (String) parent.getItemAtPosition(position);
                }
                else{
                    choosed_option = "Nothing was selected";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                choosed_option = "Nothing was selected";
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }

    public void ok(View view) {
        selection.setText(choosed_option);
    }

    public void cancel(View view) {
        selection.setText("Nothing was selected");
    }

}