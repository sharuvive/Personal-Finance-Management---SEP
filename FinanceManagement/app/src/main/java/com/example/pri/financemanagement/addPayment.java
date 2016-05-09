package com.example.pri.financemanagement;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by Sharu Vive on 2/06/2016.
 */


public class addPayment extends ActionBarActivity {
    // Declare UI elements
    DataBaseHelper helper;
    SQLiteDatabase db;
    Spinner spinner;
    EditText description;
    static TextView date;
    EditText rs;
    Spinner sp;
    CheckBox chk;
    Button t1, btnView;
    public static String SELECTED_DATE = null;

    @Override
    /** Called when the activity is first created. */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ps_addpayment);

        description = (EditText) findViewById(R.id.et_description);
        rs = (EditText) findViewById(R.id.et_rs);
        date = (TextView) findViewById(R.id.et_date1);
        chk = (CheckBox) findViewById(R.id.chk_clear);
        t1 = (Button) findViewById(R.id.btn_save);
        btnView = (Button) findViewById(R.id.btn_view);
        sp = (Spinner) findViewById(R.id.spinner);

        helper = new DataBaseHelper(this);

        ArrayList<category> mArrayList = helper.getCategories(); //set the category object into the arraylist

        ArrayList<String> catStringArray = new ArrayList<String>();

        for (category cat : mArrayList)
            catStringArray.add(cat.getName()); //get the name from the id

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_row, R.id.tv, catStringArray); //set the items into the adapter
        sp.setAdapter(adapter); //set the adapter in to the spinner

        t1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                save();  //Calling the save method on button click
                clear();


            }

        });

        btnView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent mInt = new Intent(addPayment.this, viewMonthly.class);
                startActivity(mInt);


            }

        });

        Calendar c = Calendar.getInstance();
        int date = c.get(Calendar.DAY_OF_MONTH);
        int tmw = date + 1;

        int m = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        int month = m+1;


        String tmwString = year+"-"+month+"-"+tmw ;

        Double val = helper.getFuturePayment(tmwString);

        if(val != 0)
        {
            showNotification();
        }
        else
            Toast.makeText(this, tmwString, Toast.LENGTH_LONG).show();


    }

    public void showNotification() {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, addPayment.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Warning")
                .setSmallIcon(R.drawable.alert)
                .setContentTitle("Warning")
                .setContentText("You have a payment Tomorrow!")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
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

        String desc = description.getText().toString();
        String Rs = rs.getText().toString();
        String category = sp.getSelectedItem().toString();

        String paymentDate = date.getText().toString();
        String year = SELECTED_DATE.split("-")[0];
        String month = SELECTED_DATE.split("-")[1];


/*
        ContentValues values = new ContentValues();
        values.put(DBhelper.CATEGORY, category);
        values.put(DBhelper.AMOUNT1, Rs);
        values.put(DBhelper.DETAIL, desc);
        values.put(DBhelper.DATE_T1, date.getText().toString());
        values.put(DBhelper.EX_YEAR, SELECTED_DATE.split("-")[0]);  //Split the date to get the year
        values.put(DBhelper.EX_MONTH, SELECTED_DATE.split("-")[1]);  //split the date to get month
        values.put(DBhelper.STATUS, "Ondue"); */

        //Checking for the validation
        if (isValidnum2(Rs) && isValidWord(desc)) {
            /*db = helper.getWritableDatabase();
            db.insert(DBhelper.TABLE2, null, values); //Insert values to the database
            db.close(); */

            boolean isInserted = helper.insertData(paymentDate, category, desc, Rs, year, month);

            if(isInserted) {

                Toast.makeText(this, "Payment add Successfully", Toast.LENGTH_LONG).show(); //success message

                AlertDialog.Builder builder = new AlertDialog.Builder(addPayment.this); //Alert dialog box
                builder.setTitle("Confirm");
                builder.setMessage("Do you want to add another Payment?");

                //if we click the yes button
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        dialog.dismiss();
                    }

                });

                //if we click the No button in the alert dialog box
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Redirecting to another activity
                        //addPayment.this.finish();
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }


        } else {
            if (!isValidnum2(Rs)) {
                rs.setError("Invalid Amount"); //set error ,if the validations fails
            }
            if (!isValidWord(desc)) {
                description.setError("Invalid detail");//set error ,if the validations fails
            }


        }

    }

    //clear the edit text fields
    public void clear() {

        rs.setText("");
        description.setText("");
        SELECTED_DATE = null;
        initializeDate();
    }

    public void clear(View view) {
        clear();
    }

    //Method to pick date on button click
    public void pickDate(View v) {
        android.app.DialogFragment newFragment = new DatePickerFragment_ps();
        newFragment.show(getFragmentManager(), "datePicker");
    }


    public boolean isValidnum2(String e) {
        return e.matches("^(?![.0]*$)\\d+(?:\\.\\d{1,2})?$");
    }

    public boolean isValidWord(String w) {
        return w.matches("[A-Za-z][^.]*");
    }


}