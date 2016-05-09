package com.example.pri.financemanagement;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.text.DateFormatSymbols;

/**
 * Created by pri on 3/22/2016.
 */
public class BudgetForMonth extends ActionBarActivity {

    Spinner spnMonth;
    Button btnGetbud;
    DataBaseHelper helper;

    @Override
    /** Called when the activity is first created. */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_for_month);

        spnMonth = (Spinner) findViewById(R.id.spnMonth);
        btnGetbud = (Button) findViewById(R.id.btnGetBud);

        helper = new DataBaseHelper(this);

        btnGetbud.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String m = spnMonth.getSelectedItem().toString();
                int month = getMonthInt(m);

                double budget = helper.getBudgetForMonth(month);

                if(budget != 1){
                    showMessage("Budget", "Budget is " + budget);
                }
                else {
                    showMessage("Budget", "Budget is not setted");
                }

            }

        });
    }

    public  void  showMessage(String title,String Message){
        AlertDialog.Builder builder = new  AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public int getMonthInt(String month)
    {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        int val = 0;
        for(int i=0; i<12; i++){
            if(month.equals(months[i])){
                val = i;
            }
        }
        return val+1;
    }
}
