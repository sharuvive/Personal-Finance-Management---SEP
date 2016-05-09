package com.example.pri.financemanagement;
//Shangavi
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;


public class addSubCategoryExpense extends ActionBarActivity implements View.OnClickListener  {

    DataBaseHelper helper;
    EditText text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_category_expense);

        helper = new DataBaseHelper(this);

        text1 = (EditText) findViewById(R.id.txtAddSubCatExpense);

    }

    @Override
    public void onClick(View v) {

    }
}
