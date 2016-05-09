package com.example.pri.financemanagement;
//shangavi
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pri.financemanagement.DataBaseHelper;


/**
 * The class which used to add any new expenses
 */

public class addExpenseCat extends ActionBarActivity implements View.OnClickListener {

    EditText text1;
    Button button1;


    DataBaseHelper helper;
    SQLiteDatabase db;
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        helper = new DataBaseHelper(this);

        text1 = (EditText) findViewById(R.id.txtAddExpense);

    }
    @Override
    public void onClick(View v) {

    }

    private void clearfield() {


    }


}
