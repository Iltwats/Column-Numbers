package com.atul.android.columnnumbers;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import androidx.appcompat.app.AlertDialog;

import com.atul.android.columnnumbers.databinding.DialogActivityBinding;

public class Dialog {
    private final int selectedID;
    private final boolean checked;
    private final int startNo;
    private final int endNo;
    MainActivity main;

    public Dialog(int selectedID, boolean checked, int startNo, int endNo) {

        this.selectedID = selectedID;
        this.checked = checked;
        this.startNo = startNo;
        this.endNo = endNo;
    }

    public void show(Context context) {
       DialogActivityBinding b = DialogActivityBinding.inflate(LayoutInflater.from(context));
        new AlertDialog.Builder(context)
                .setTitle("Column Names")
                .setView(b.getRoot())
                .setPositiveButton("CHECK AGAIN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();

        setupNumberPickers(b.numberPicker);
    }

    private void setupNumberPickers(NumberPicker cmsPicker) {
        cmsPicker.setMaxValue(endNo);
        cmsPicker.setMinValue(startNo);
        cmsPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                String ans;
                switch (selectedID)
                {
                    case R.id.radioNormal:
                        if(checked){
                            ans = getColName(value);
                            return value+" "+ ans;
                        }
                        else{
                            ans = getColName(value);
                            return ans;
                        }

                    case R.id.radioReverse:
                        if(checked){
                            ans = getReverseColName(value);
                            return value+" "+ ans;
                        }
                        else{
                            ans = getReverseColName(value);
                            return ans;
                        }
                    default:
                        throw new IllegalStateException("Unexpected value: " + selectedID);
                }
            }
        });
        View firstItem = cmsPicker.getChildAt(0);
        if (firstItem != null) {
            firstItem.setVisibility(View.INVISIBLE);
        }
    }
    // To get column name
    private String getColName(int col) {
        StringBuilder CV = new StringBuilder();
        while (col > 0) {
            int rem = col % 26;
            if (rem == 0) {
                CV.append('Z');
                col = (col / 26) - 1;
            } else {
                CV.append((char) (64 + rem));
                col /= 26;
            }
        }
        return CV.reverse().toString();
    }

    // To get column name in reverse order
    private String getReverseColName(int col) {
        StringBuilder CV = new StringBuilder();
        while (col > 0) {
            int rem = col % 26;
            if (rem == 0) {
                CV.append('A');
                col = (col / 26) - 1;
            } else {
                CV.append((char) (65 + 26 - rem));
                col /= 26;
            }
        }
        return CV.reverse().toString();
    }

}
