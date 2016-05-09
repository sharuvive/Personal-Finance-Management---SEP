package com.example.pri.financemanagement;

/**
 * Created by pri on 2/4/2016.
 * Purpose : Records the details of new income
 */

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class Addincome extends ActionBarActivity {
    // Declare UI elements
    DataBaseHelper helper;
    SQLiteDatabase db;
    EditText txtDescription;
    static TextView date;
    EditText txtRs;
    Spinner spnCat;
    Button btnSave;
    public static String SELECTED_DATE = null;

    @Override
    /** Called when the activity is first created. */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addincomes);

        txtDescription = (EditText) findViewById(R.id.txtDes);
        txtRs = (EditText) findViewById(R.id.txtRs);
        date = (TextView) findViewById(R.id.txtDate);
        btnSave = (Button) findViewById(R.id.btnSave);
        spnCat = (Spinner) findViewById(R.id.spnCat);

        helper = new DataBaseHelper(this);

        ArrayList<category> mArrayList = helper.getCategories1(); //set the category object into the arraylist

        ArrayList<String> catStringArray = new ArrayList<String>();

        for (category cat : mArrayList)
            catStringArray.add(cat.getName()); //get the name from the id

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_row, R.id.tv, catStringArray); //set the items into the adapter
        spnCat.setAdapter(adapter); //set the adapter in to the spinner

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                save();  //Calling the save method on button click


            }

        });

    }


    public static void initializeDate() {
        if (SELECTED_DATE != null) {
            date.setText(SELECTED_DATE);
            date.setVisibility(View.VISIBLE);
        } else {
            date.setVisibility(View.INVISIBLE);
        }
    }

    //method to add expenses to the database
    private void save() {

        String desc = txtDescription.getText().toString();
        String Rs = txtRs.getText().toString();
        String category = spnCat.getSelectedItem().toString();



        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.CATEGORY, category);
        values.put(DataBaseHelper.AMOUNT1, Rs);
        values.put(DataBaseHelper.DETAIL, desc);
        values.put(DataBaseHelper.DATE_T1, date.getText().toString());
        values.put(DataBaseHelper.EX_YEAR, SELECTED_DATE.split("-")[0]);  //Split the date to get the year
        values.put(DataBaseHelper.EX_MONTH, SELECTED_DATE.split("-")[1]);  //split the date to get month

        //Checking for the validation
        if (isValidnum2(Rs) && isValidWord(desc)) {
            db = helper.getWritableDatabase();
            db.insert(DataBaseHelper.TABLE4, null, values); //Insert values to the database
            db.close();

            Toast.makeText(this, "Income add Successfully", Toast.LENGTH_LONG).show(); //success message
            Intent st = new Intent(Addincome.this, Income_activity.class);
            startActivity(st);

        } else {
            if (!isValidnum2(Rs)) {
                txtRs.setError("Invalid Amount"); //set error ,if the validations fails
            }
            if (!isValidWord(desc)) {
                txtDescription.setError("Invalid detail");//set error ,if the validations fails
            }


        }
    }

    //clear the edit text fields
    public void clear() {

        txtRs.setText("");
        txtDescription.setText("");
        SELECTED_DATE = null;
        initializeDate();
    }

    public void clear(View view) {
        clear();
    }

    //Method to pick date on button click
    public void pickDate(View v) {
        android.app.DialogFragment newFragment = new DatePickerFragment_inc();
        newFragment.show(getFragmentManager(), "datePicker");
    }


    public boolean isValidnum2(String e) {
        return e.matches("^(?![.0]*$)\\d+(?:\\.\\d{1,2})?$");
    }

    public boolean isValidWord(String w) {
        return w.matches("[A-Za-z][^.]*");
    }


}
