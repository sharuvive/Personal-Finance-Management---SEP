package com.example.pri.financemanagement;
//Shangavi
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
public class ListDataSubCatExpense extends BaseAdapter implements ListAdapter {

    public ArrayList<SubCategoryExpense> list = new ArrayList<SubCategoryExpense>();
    public Context context;

    public ListDataSubCatExpense (ArrayList<SubCategoryExpense> list,
                                  Context context) {
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
        return list.get(position).getSubCatId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_subcategory, null);
        }

        //Handle TextView and display string from your list
        final TextView listItemText = (TextView) view.findViewById(R.id.lblreloadExpenseSubCategory);
        listItemText.setText(list.get(position).getSubCatName());

        SQLiteDatabase db = new DataBaseHelper(context.getApplicationContext()).getWritableDatabase();
        //Handle buttons and add onClickListeners
        TextView deleteBtn = (TextView) view.findViewById(R.id.btnDeleteSubCatExpense);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof SubCategory_expense) {
                    new AlertDialog.Builder((SubCategory_expense) context) //Alert dialog box
                            .setTitle("Delete Category")
                            .setMessage("Are you sure you want to Delete this Category with SubCategories?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    SQLiteDatabase db = new DataBaseHelper(context.getApplicationContext()).
                                            getWritableDatabase();
                                    db.delete(DataBaseHelper.SUBCATEGORY_TABLE_NAME, DataBaseHelper.SUBCATEGORY_ID+ "=?",
                                            new String[]{Integer.toString(list.get(position).getSubCatId())});
                                    db.close();
                                    list.remove(position);  //delete the item
                                    notifyDataSetChanged(); //refersh the listview

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    SQLiteDatabase db = new DataBaseHelper(context.getApplicationContext()).
                                            getWritableDatabase();
                                    db.delete(DataBaseHelper.SUBCATEGORY_TABLE_NAME, DataBaseHelper.SUBCAT_NAME+ "='?'",
                                            new String[]{(list.get(position).getSubCatName())});
                                    db.close();
                                    list.remove(position);  //delete the item
                                    notifyDataSetChanged(); //refersh the listview
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
