package com.example.pri.financemanagement;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * The class which used to add any new incomes
 */

public class addIncomeCat extends ActionBarActivity implements View.OnClickListener {


    EditText text;
    Button button;


    DataBaseHelper helper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        helper = new DataBaseHelper(this);

        text = (EditText) findViewById(R.id.txtAddIncome);  //assign the layout's EditText variable
    }

    @Override
    public void onClick(View v) {

    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();


    }

    private void clearfield() {
        text.setText("");

    }
}
