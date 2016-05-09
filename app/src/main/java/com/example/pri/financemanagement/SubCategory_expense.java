package com.example.pri.financemanagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SubCategory_expense extends Activity implements View.OnClickListener{

    int selected_id;
    TextView menubtn;
    ListView rldlist;
    EditText textAdd;
    Context context;
    DataBaseHelper helper;
    SQLiteDatabase db;
    String SubCatexpense;
    String getname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category_expense);

        context = this;
        helper = new DataBaseHelper(this);

        menubtn = (TextView) findViewById(R.id.txtSubCatExpense);
        menubtn.setOnClickListener(this);

        loadListSubCategory();
    }

    @Override
    public void onClick(View v) {
        // get addSubcategoryExpense.xml view
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.activity_add_sub_category_expense, null);

        textAdd = (EditText) promptsView.findViewById(R.id.txtAddSubCatExpense);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set addSubcategoryExpense.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        //set dialog message
        alertDialogBuilder.setCancelable(false).setPositiveButton("ADD",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        AddSubCategory();
                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener(){


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

    private void AddSubCategory() {

        ContentValues value = new ContentValues();
        value.put(DataBaseHelper.SUBCATEGORY_TABLE_NAME, textAdd.getText().toString());
        db = helper.getWritableDatabase();

        String SubcatExName = textAdd.getText().toString();

        //validation for edit text

        if (isValidWord(SubcatExName)){
            //check whether the subcategory is already there or not
            if(helper.checkIdExist(textAdd.getText().toString())){
                //ae.addingExpense(this, catExName);
                checkSubCategory(value);
            }

            else{
                Toast.makeText(getApplicationContext(), "Duplication SubCategory Name!",
                        Toast.LENGTH_LONG).show();
            }
        }

        else{
            Toast.makeText(getApplicationContext(), "Enter a valid SubCategory name!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void checkSubCategory(ContentValues value){

        Bundle extras = getIntent().getExtras();
        getname = extras.getString("passed data key");
        if(helper.checkIdExistSubcategory(textAdd.getText().toString(), getname)){
            //ae.addingExpense(this, catExName);

            db.insert(DataBaseHelper.SUBCATEGORY_TABLE_NAME, null, value); //insert values to the database
            db.close();
            Toast.makeText(SubCategory_expense.this, "New Sub Category added Successfully",
                    Toast.LENGTH_LONG).show();

            Intent i = new Intent(SubCategory_expense.this, expense.class);
            startActivity(i);
            loadListSubCategory();
        }

        else{
            Toast.makeText(getApplicationContext(), "Duplication Category Name!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public boolean isValidWord(String w) {
        return w.matches("[A-Za-z][^.]*");
    }

    public void loadListSubCategory() {

        rldlist = (ListView) findViewById(R.id.listViewRowSubCatExpense);

        ArrayList<SubCategoryExpense> mArrayList = helper.getSubCategoriesExpense(); //calling the method from DBhelper class
        ListDataSubCatExpense adapter = new ListDataSubCatExpense(mArrayList, this);

        //handle listview and assign adapter,view,position
        rldlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                SubCategoryExpense row = (SubCategoryExpense) arg0.getItemAtPosition(position); //assigning the position to the adapter
                selected_id = row.getSubCatId();
                SubCatexpense= row.getSubCatName();

                Intent myIntent = new Intent(SubCategory_expense.this, addSubCategoryExpense.class); //redirecting to another activity
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
}
