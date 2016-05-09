package com.example.pri.financemanagement;
//Jathevan

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter
{
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 2;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"+ "NAME text,PHONE int,CURRENCY text, USERNAME  text,PASSWORD text); ";
    // Variable to hold the database instance
    public  SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public  LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context);
    }
    public  LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String userName,String password)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD",password);

        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }


    public void addProf(String uname,String uphone,String currency,String un,String  upw)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.


        newValues.put("NAME", uname);
        newValues.put("PHONE",uphone);
        newValues.put("CURRENCY",currency);
        newValues.put("PASSWORD",upw);
        newValues.put("USERNAME",un);


        // Insert the row into your table
        db.update("LOGIN", newValues, " USERNAME ='" + un + "'", null);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public void updateProf(String uname,String uphone,String currency,String un,String  upw){

        //SQLiteDatabase db = this.getWritableDatabase();
        /*String query = "update LOGIN set NAME = '" + uname + "' , PHONE = '" + uphone + "' , CURRENCY = '" + currency + "' , PASSWORD = '" + upw + "' where USERNAME = '" + un + "'";

        Cursor c = db.rawQuery(query, null);


        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("NAME", uname);
        updatedValues.put("PHONE",uphone);
        updatedValues.put("CURRENCY",currency);
        updatedValues.put("PASSWORD",un);
        updatedValues.put("USERNAME",upw);



        String where="USERNAME = ?";
        db.update("LOGIN",updatedValues, where, new String[]{upw});*/
        db.execSQL("update LOGIN set NAME = '" + uname + "' , PHONE = '" + uphone + "' , CURRENCY = '" + currency + "' , PASSWORD = '" + upw + "' where USERNAME = '" + un + "'");

    }
    public int deleteEntry(String UserName)
    {
        //String id=String.valueOf(ID);
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    public String getSinlgeEntry(String userName)
    {
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public String getUserName(){
        String query = "select USERNAME from LOGIN ";

        Cursor c = db.rawQuery(query, null);

        if(c.moveToNext()) {

            return c.getString(0);
        }
        return null;
    }
    public void  updateEntry(String userName,String password)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD",password);

        String where="USERNAME = ?";
        db.update("LOGIN",updatedValues, where, new String[]{userName});
    }


    public Cursor getDetail(String un)
    {


        Cursor c = db.rawQuery("select * from LOGIN where USERNAME = '" + un + "'",null);

        return  c;
    }


}