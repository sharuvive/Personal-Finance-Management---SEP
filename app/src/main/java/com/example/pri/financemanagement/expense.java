package com.example.pri.financemanagement;
//shangavi
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * what should do under expense class
 */

public class expense extends Activity implements View.OnClickListener, View.OnLongClickListener{

    int selected_id;
    TextView menubtn;
    ListView rldlist;
    EditText textlsdk, editText;
    Context context;
    DataBaseHelper helper;
    SQLiteDatabase db;
    String expense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        context = this;
        helper = new DataBaseHelper(this);

        menubtn = (TextView) findViewById(R.id.txtExpense);
        menubtn.setOnClickListener(this);

        loadList();


    }

    @Override
    public void onClick(View v) {
        // get addcategory.xml view
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.activity_add_expense, null);

        textlsdk = (EditText) promptsView.findViewById(R.id.txtAddExpense);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        // set addcategory.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        //set dialog message
        alertDialogBuilder.setCancelable(false).setPositiveButton("ADD",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        AddCategory();
                    }
                }) // Call the method which will add the category for the usage of other transactions
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        //create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        //show it
        alertDialog.show();
    }


    //check whether the word is valid. In the sense whether the text is texted or did not give any names.
    public boolean isValidWord(String w) {
        return w.matches("[A-Za-z][^.]*");
    }

    public void loadList() {

        rldlist = (ListView) findViewById(R.id.listViewRowExpense);

        ArrayList<category> mArrayList = helper.getCategories(); //calling the method from DBhelper class
        ListDataAdapter adapter = new ListDataAdapter(mArrayList, this);

        //handle listview and assign adapter,view,position
        rldlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                category row = (category) arg0.getItemAtPosition(position); //assigning the position to the adapter
                selected_id = row.getId();
                expense= row.getName();

                Intent myIntent = new Intent(expense.this, addExpenseCat.class); //redirecting to another activity
                myIntent.putExtra("passed data key", expense); // pass your values and retrieve them in the other Activity using keyName
                startActivity(myIntent);
            }

        });

        rldlist.setOnLongClickListener(new AdapterView.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        rldlist.setAdapter(adapter); //set the adaptor which contain list of items

    }

    public void check(ContentValues value){

        if(helper.checkIdExist(textlsdk.getText().toString())){
            //ae.addingExpense(this, catExName);

            db.insert(DataBaseHelper.CATEGORY_TABLE_NAME, null, value); //insert values to the database
            db.close();
            Toast.makeText(expense.this, "New Category added Successfully",
                    Toast.LENGTH_LONG).show();

            Intent i = new Intent(expense.this, MainActivityShangavi.class);
            startActivity(i);
            loadList();
        }

        else{
            Toast.makeText(getApplicationContext(), "Duplication Category Name!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void AddCategory(){
        ContentValues value = new ContentValues();
        value.put(DataBaseHelper.CATEGORY_NAME, textlsdk.getText().toString());
        db = helper.getWritableDatabase();

        String catExName = textlsdk.getText().toString();

        //validation for edit text

        if (isValidWord(catExName)){
            //check whether the category is already there or not
            //addExpenseCat ae = new addExpenseCat();
            check(value);
        }

        else{
            Toast.makeText(getApplicationContext(), "Enter a valid Category name!",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.update_expense, null);
        editText = (EditText) promptsView.findViewById(R.id.editTextUpdateexpense);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(promptsView);

            builder.setCancelable(false).setPositiveButton("Update",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            UpdateCategory();
                        }
                    })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
            //create alert dialog
            AlertDialog alertDialog = builder.create();
            //show it
            alertDialog.show();


        return false;
    }

    public void UpdateCategory(){
        ContentValues value = new ContentValues();
        value.put(DataBaseHelper.CATEGORY_NAME, editText.getText().toString());
        db = helper.getWritableDatabase();

        String catExName = editText.getText().toString();

        //validation for edit text
        expense e = new expense();
        if (e.isValidWord(catExName)){
            //check whether the category is already there or not
            //addExpenseCat ae = new addExpenseCat();
            e.checkUpdate(value);
        }
    }

    public void checkUpdate(ContentValues value){

        if(helper.checkIdExist(textlsdk.getText().toString())){
            //ae.addingExpense(this, catExName);

            int id = helper.getId(textlsdk.getText().toString());

            db.update(DataBaseHelper.CATEGORY_TABLE_NAME, value, "Category_ID = "+id, null); //insert values to the database
            db.close();
            Toast.makeText(expense.this, "Updated Successfully",
                    Toast.LENGTH_LONG).show();

            Intent i = new Intent(expense.this, MainActivityShangavi.class);
            startActivity(i);
            loadList();
        }

        else{
            Toast.makeText(getApplicationContext(), "Duplication Category Name!",
                    Toast.LENGTH_LONG).show();
        }
    }
}
