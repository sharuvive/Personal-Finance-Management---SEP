package com.example.pri.financemanagement;
//shangavi
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 3/10/2016.
 */
public class ListDataAdapter extends BaseAdapter implements ListAdapter {

    public ArrayList<category> list = new ArrayList<category>();
    public Context context;
    public EditText editText;
    DataBaseHelper helper;
    SQLiteDatabase db;

    public ListDataAdapter (ArrayList<category> list, Context context) {
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
        return list.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_sh, null);
        }

        //Handle TextView and display string from your list
        final TextView listItemText = (TextView) view.findViewById(R.id.lblReload);
        listItemText.setText(list.get(position).getName());

        listItemText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentNavigation(position);
            }
        });



        SQLiteDatabase db = new DataBaseHelper(context.getApplicationContext()).getWritableDatabase();
        //Handle buttons and add onClickListeners
        final TextView deleteBtn = (TextView) view.findViewById(R.id.btnDelete);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               delete(position);
            }
        });

        return view;
    }


    //delete any category what we want
    public void delete(final int position){

        if (context instanceof expense) {
            new AlertDialog.Builder((expense) context) //Alert dialog box
                    .setTitle("Delete Category")
                    .setMessage("Are you sure you want to Delete this Category along with its subcategories?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SQLiteDatabase db = new DataBaseHelper(context.getApplicationContext()).
                                    getWritableDatabase();
                            db.delete(DataBaseHelper.CATEGORY_TABLE_NAME, DataBaseHelper.Category_ID + "=?",
                                    new String[]{Integer.toString(list.get(position).getId())});
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

    public void intentNavigation(final int position){

        category row = (category) list.get(position);
        int selected_id = row.getId();
        String expenseCateory = row.getName();

        Intent myIntent = new Intent(context, SubCategory_expense.class); //Redirecting to another activity

        // pass your values and retrieve them in the other Activity using keyName
        myIntent.putExtra("passed data key", expenseCateory);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
    }
}
