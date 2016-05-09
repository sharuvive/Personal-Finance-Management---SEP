package com.example.pri.financemanagement;

/**
 * Created by Sharu Vive on 2/11/2016.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class List_activity_ps extends ActionBarActivity {
    //Delclare UI elements
    ListView rldlist3;
    SimpleCursorAdapter adapter3;
    DataBaseHelper helper;
    SQLiteDatabase db;
    String selected_id;
    String value, upd, ex;
    Context context;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ps_list_activity);

        context = this;

        helper = new DataBaseHelper(this); //initializing helper

        rldlist3 = (ListView) findViewById(R.id.list3);

        //assigning adapter,and position
        rldlist3.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> arg0, View arg1, final int position, long arg3) {

                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.ps_custom);
                dialog.setTitle("Manage Payments");

                // set the custom dialog components
                Button btnupdate = (Button) dialog.findViewById(R.id.Update);

                Button btnDelete = (Button) dialog.findViewById(R.id.Delete);

                Button btnDone = (Button)dialog.findViewById(R.id.Done);
                // if button is clicked, close the custom dialog
                btnupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor row1 = (Cursor) arg0.getItemAtPosition(position); //get the position of item into the cursor
                        selected_id = row1.getString(0);
                        ex = row1.getString(0);

                        Intent myIntent = new Intent(List_activity_ps.this, u_payments.class);//redirecting to another activity
                        myIntent.putExtra("data key", ex);// pass your values and retrieve them in the other Activity using keyName
                        startActivity(myIntent);
                        fetchData3();
                    }
                });
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //set up the alert dialog box
                        AlertDialog.Builder builder = new AlertDialog.Builder(List_activity_ps.this);

                        builder.setTitle("Confirm");
                        builder.setMessage("Do you want to delete this selected Payment?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                Cursor row = (Cursor) arg0.getItemAtPosition(position); //Get the position of item
                                selected_id = row.getString(0);

                                db = helper.getWritableDatabase();
                                db.delete(DataBaseHelper.TABLE7, DataBaseHelper.ID1 + "=?", new String[]{selected_id}); //Delete the particular item
                                db.close();

                                fetchData3(); //fetch data again to listview to refresh
                                Toast.makeText(List_activity_ps.this, "successfully deleted", Toast.LENGTH_LONG).show(); //Success message

                                dialog.cancel();
                            }

                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                            @Override

                            public void onClick(DialogInterface dialog, int which) {
                                // Redirecting to another activity

                                dialog.cancel();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show(); //show the alert


                    }
                });
                btnDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //set up the alert dialog box
                        AlertDialog.Builder builder = new AlertDialog.Builder(List_activity_ps.this);

                        builder.setTitle("Confirm");
                        builder.setMessage("Have you paid the selected Payment?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                Cursor row = (Cursor) arg0.getItemAtPosition(position); //Get the position of item
                                selected_id = row.getString(0);

                                helper.markDone(selected_id);

                                fetchData3(); //fetch data again to listview to refresh
                                Toast.makeText(List_activity_ps.this, "successfully marked", Toast.LENGTH_LONG).show(); //Success message

                                dialog.cancel();
                            }

                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                            @Override

                            public void onClick(DialogInterface dialog, int which) {
                                // Redirecting to another activity

                                dialog.cancel();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show(); //show the alert


                    }
                });

                dialog.show();

            }

        });

        Bundle data_from_list = getIntent().getExtras();
        value = data_from_list.getString("passed data key"); // retrieve the data using keyName
        fetchData3();

    }



    private void fetchData3() {
        db = helper.getReadableDatabase();
        //Cursor c3 = db.query(DBhelper.TABLE2, null, DBhelper.CATEGORY + "='" + value + "'", null, null, null, null);
        Cursor c3 = helper.getCategoryPayments(value);
        adapter3 = new SimpleCursorAdapter(
                this,
                R.layout.row3,
                c3,
                new String[]{DataBaseHelper.DATE_T1, DataBaseHelper.DETAIL, DataBaseHelper.AMOUNT1},
                new int[]{R.id.date, R.id.txtDesc, R.id.txtAmount});
        rldlist3.setAdapter(adapter3);
    }


}

