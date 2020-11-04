package com.atul.android.columnnumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.atul.android.columnnumbers.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding b;
    int selectedID ;
    boolean checked ;
    int startNo ;
    int endNo ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(b.startNumber.getText().toString().isEmpty()) && !(b.endNumber.getText().toString().isEmpty())) {
                    intializeValues();
                    new Dialog(selectedID,checked,startNo,endNo).show(MainActivity.this);
                } else {
                    Toast.makeText(MainActivity.this, "Enter both values to see picker", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void intializeValues() {
        selectedID = b.radioGroup2.getCheckedRadioButtonId();
        checked = b.checkBox.isChecked();
        startNo = Integer.parseInt(b.startNumber.getText().toString());
        endNo = Integer.parseInt(b.endNumber.getText().toString());
    }
}