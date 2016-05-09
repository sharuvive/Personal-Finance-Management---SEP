package com.example.pri.financemanagement;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * Created by Sharu Vive on 2/28/2016.
 */
public class viewMonthly extends ActionBarActivity {

    //EditText etMonth;
    Button btnView;
    ListView lstMonthly;
    Spinner spnMonth;
    TextView tvTotal;

    SimpleCursorAdapter adapter;
    DataBaseHelper helper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ps_list_month);

        helper = new DataBaseHelper(this); //initialize the helper
        //etMonth = (EditText)findViewById(R.id.month);
        btnView = (Button)findViewById(R.id.btn_viewPayments);
        spnMonth = (Spinner)findViewById(R.id.sp_month);
        tvTotal = (TextView)findViewById(R.id.txtAmt);



        lstMonthly = (ListView)findViewById(R.id.lstMonth);

        btnView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String month = spnMonth.getSelectedItem().toString(); //etMonth.getText().toString();
                fetchData(month);  //Calling the fetchData method on button click

                Double tot = helper.calcMonthlyTotal(month);
                String totString = Double.toString(tot);
                tvTotal.setText(totString);


            }

        });

        //

    }




    private void fetchData(String m) {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = helper.getMonthlyPayments(m);
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.row3,
                c,
                new String[]{DataBaseHelper.DATE_T1, DataBaseHelper.DETAIL, DataBaseHelper.AMOUNT1},
                new int[]{R.id.date, R.id.txtDesc, R.id.txtAmount});
        lstMonthly.setAdapter(adapter); //set the adapter to listview
    }


}
