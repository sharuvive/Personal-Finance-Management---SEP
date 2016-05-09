package com.example.pri.financemanagement;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.util.ArrayList;

import static java.lang.String.*;

/**
 * Created by pri on 3/20/2016.
 */
public class AmountDistribution extends ActionBarActivity {

    EditText txtAmount;
    Spinner spnMonth;
    ListView lstDetail;
    DataBaseHelper helper;
    Button btnSearch;
    SQLiteDatabase db;
    SimpleCursorAdapter adapter3;

    @Override
    /** Called when the activity is first created. */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amount_distribution);

        txtAmount = (EditText) findViewById(R.id.txtAmount);
        spnMonth = (Spinner) findViewById(R.id.spnMonth);
        lstDetail = (ListView) findViewById(R.id.lstDetails);
        btnSearch = (Button) findViewById(R.id.btnSearch);

        helper = new DataBaseHelper(this);



        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String amount = txtAmount.getText().toString();
                String m = spnMonth.getSelectedItem().toString();
                int month = getMonthInt(m);

                fetchData3(amount,String.valueOf(month));

            }

        });

    }

    private void fetchData3(String amount, String month) {
        double total = 0.0;
        String[] matches = new String[10];
        ArrayList exp = new ArrayList();
        int i=0,id;
        db = helper.getReadableDatabase();
        Cursor c3 = db.rawQuery("select * from " + DataBaseHelper.TABLE3 + " where " + DataBaseHelper.EX_MONTH + "= " + month + " limit 1",null);

        while(c3.moveToNext()){



                id = c3.getInt(0);


                total = total + c3.getDouble(7);

                if (total <= Double.parseDouble(amount)) {
                    //matches[i] = c3.getString(7);
                    //i++;

                    Expence e = new Expence(c3.getString(3), c3.getString(1), c3.getString(7));
                    exp.add(i++,e.getDetail());



                 /*   adapter3 = new SimpleCursorAdapter(
                            this,
                            R.layout.row3,
                            c3,
                            new String[]{DBhelper.DATE_T1, DBhelper.DETAIL, DBhelper.AMOUNT1},
                            new int[]{R.id.date, R.id.txtDesc, R.id.txtAmount});
                  */  //lstDetail.setAdapter(adapter3);

                      //lstDetail.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,matches));

                }
                id += 1;

                //Toast.makeText(this, valueOf(id), Toast.LENGTH_LONG).show();
                c3 = db.rawQuery("select * from " + DataBaseHelper.TABLE3 + " where " + DataBaseHelper.EX_MONTH + "= " + month + " and " + DataBaseHelper.ID1 + " = " + id + " limit 1", null);
                //continue;
            //if (c4.moveToNext())
              //  Toast.makeText(this, c4.getString(7), Toast.LENGTH_LONG).show();





        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                exp);

        lstDetail.setAdapter(arrayAdapter);

        //lstDetail.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,exp));


            //lstDetail.setAdapter(adapter3);


    }

    public int getMonthInt(String month)
    {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        int val = 0;
        for(int i=0; i<12; i++){
            if(month.equals(months[i])){
                val = i;
            }
        }
        return val+1;
    }
}
