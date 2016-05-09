package com.example.pri.financemanagement;

/**
 * Created by pri on 2/1/2016.
 * Purpose : Records the details of new expense
 */

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class Addexpense extends ActionBarActivity {
    // Declare UI elements
    DataBaseHelper helper;
    SQLiteDatabase db;
    EditText txtdescription;
    static TextView date;
    EditText txtRs;
    Spinner spnCat,spnSubCat;
    Button btnSave;
    public static String SELECTED_DATE = null;

    @Override
    /** Called when the activity is first created. */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addexpenses);

        txtdescription = (EditText) findViewById(R.id.txtDes);
        txtRs = (EditText) findViewById(R.id.txtRs);
        date = (TextView) findViewById(R.id.txtDate);
        btnSave = (Button) findViewById(R.id.btnSave);
        spnCat = (Spinner) findViewById(R.id.spnCat);
        spnSubCat = (Spinner) findViewById(R.id.spnSubCat);


        helper = new DataBaseHelper(this);

        ArrayList<category> mArrayList = helper.getCategories(); //set the category object into the arraylist

        ArrayList<String> catStringArray = new ArrayList<String>();

        for (category cat : mArrayList)
            catStringArray.add(cat.getName()); //get the name from the id

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_row, R.id.tv, catStringArray); //set the items into the adapter
        spnCat.setAdapter(adapter); //set the adapter in to the spinner

        spnCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                setSubCat();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        //setSubCat();
// Jathevan /********************** Fix duplication****************************/
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Addexpense.this);
                alertDialogBuilder.setMessage("Do you want to combine this transaction with previous same data");

                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        String desc = txtdescription.getText().toString();
                        String Rs = txtRs.getText().toString();
                        String category = spnCat.getSelectedItem().toString();
                        String date1 = date.getText().toString();

                        if (helper.fixDup(category, desc, date1, Rs)) {
                            Toast.makeText(Addexpense.this, "Sucessfully added", Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(Addexpense.this, "Cannot add the expenses", Toast.LENGTH_LONG).show();


                    }


                }});

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        save();
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


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
  public void save() {

        String desc = txtdescription.getText().toString();
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
            db.insert(DataBaseHelper.TABLE3, null, values); //Insert values to the database
            db.close();



            Toast.makeText(this, "Expenses add Successfully", Toast.LENGTH_LONG).show(); //success message
            Intent st = new Intent(Addexpense.this, Addexpense.class);
            clear();
            startActivity(st);

            checkLimit(category);


        } else {
            if (!isValidnum2(Rs)) {
                txtRs.setError("Invalid Amount"); //set error ,if the validations fails
            }
            if (!isValidWord(desc)) {
                txtdescription.setError("Invalid detail");//set error ,if the validations fails
            }


        }

      //  boolean rate = new DBhelper(getApplicationContext()).checkBudget(category);
        boolean rate = new DataBaseHelper(getApplicationContext()).checkBudgetEarly(category,5);

        if (rate) {


            showNotification();

    }}

    public void showNotification() {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, Budget_activity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(r.getString(R.string.notification_title))
                .setSmallIcon(R.drawable.alert)
                .setContentTitle(r.getString(R.string.notification_title))
                .setContentText(r.getString(R.string.notification_text))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }

    public void showNotificationExceed() {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, Budget_activity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(r.getString(R.string.notification_title))
                .setSmallIcon(R.drawable.images)
                .setContentTitle(r.getString(R.string.notification_title))
                .setContentText(r.getString(R.string.notification_text_exceed))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    /**check the expenses of particular category whether it's exceed the budget limit of particular category */
    public void checkLimit(String category){
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);

        String rate = new DataBaseHelper(getApplicationContext()).checkBudget(category, month + 1);

        if (rate != null) {
            showMessage("Warning", rate + " Expenses are Exceeded");
            showNotificationExceed();

        } else {
            System.out.println("false");
        }
    }


    public  void  showMessage(String title,String Message){
        AlertDialog.Builder builder = new  AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    //clear the edit text fields
    public void clear() {

        txtRs.setText("");
        txtdescription.setText("");
        SELECTED_DATE = null;
        initializeDate();
    }

    public void clear(View view) {
        clear();
    }

    //Method to pick date on button click
    public void pickDate(View v) {
        android.app.DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void setSubCat(){


        helper = new DataBaseHelper(this);
        String category = spnCat.getSelectedItem().toString();

        ArrayList<SubCategory> mArrayList = helper.getSubCategories(category); //set the category object into the arraylist

        ArrayList<String> catStringArray = new ArrayList<String>();

        for (SubCategory subcat : mArrayList)
            catStringArray.add(subcat.getName()); //get the name from the id

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_subcat_row, R.id.subtv, catStringArray); //set the items into the adapter
        spnSubCat.setAdapter(adapter); //set the adapter in to the spinner
    }


    public boolean isValidnum2(String e) {
        return e.matches("^(?![.0]*$)\\d+(?:\\.\\d{1,2})?$");
    }

    public boolean isValidWord(String w) {
        return w.matches("[A-Za-z][^.]*");
    }


}
