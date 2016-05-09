package com.example.pri.financemanagement;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 3/10/2016.
 */
public class Listata extends BaseAdapter implements ListAdapter {
    public ArrayList<categoryIncone> list = new ArrayList<categoryIncone>();
    public Context context;

    public Listata (ArrayList<categoryIncone> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getCatIdIncome();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_income, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView) view.findViewById(R.id.lblReloadIncome);
        listItemText.setText(list.get(position).getNameIncome());

        listItemText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryIncone row;
                row = list.get(position);
                int selected_id = row.getCatIdIncome();
                String incomeCateory = row.getNameIncome();

                Intent myIntent = new Intent(context, MainActivityShangavi.class); //Redirecting to another activity

                // pass your values and retrieve them in the other Activity using keyName
                myIntent.putExtra("passed data key", incomeCateory);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(myIntent);
            }
        });

        SQLiteDatabase db = new DataBaseHelper(context.getApplicationContext()).getWritableDatabase();
        //Handle buttons and add onClickListeners
        TextView deleteBtn = (TextView) view.findViewById(R.id.btnDeleteIncome);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof income) {
                    new AlertDialog.Builder((income) context) //Alert dialog box
                            .setTitle("Delete Category")
                            .setMessage("Are you sure you want to Delete this Category?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    SQLiteDatabase db = new DataBaseHelper(context.getApplicationContext()).
                                            getWritableDatabase();
                                    db.delete(DataBaseHelper.categoryIncone_table_name, DataBaseHelper.CategoryIncome_ID +
                                            "=?", new String[]{Integer.toString(list.get(position).getCatIdIncome())});
                                    db.close();
                                    list.remove(position);  //delete the item
                                    notifyDataSetChanged(); //refersh the listview

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();  //show dialog box

                }
            }
        });

        return view;
    }
}
