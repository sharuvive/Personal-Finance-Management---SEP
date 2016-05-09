package com.example.pri.financemanagement;
//Jathevan, Shangavi

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * The Common database for the project. It contains Category Tables and SubCategory tables for Expense and Income. The relevant attributes
 * are also mentioned. With the relevant attributes/filed names the table is created. The table is created using oncreate method which is
 * extended by SQLiteOpenHelper class method. This java class contains several methods regarding to database.
 */

public class DataBaseHelper extends SQLiteOpenHelper
{
    SQLiteDatabase db;

    public static final String DATABASE_NAME = "personal_finance.db";
    public static final int database_version = 1;

    /*Category table attributes*/
    public static final String Category_ID = "Category_ID";
    public static final String CATEGORY_NAME = "Category_Name";
    public static final String CATEGORY_TABLE_NAME = "category";

    /*Income type category table attributes*/
    public static final String CategoryIncome_ID = "categoryIncone_ID";
    public static final String categoryIncone_Name = "categoryIncone_Name";
    public static final String categoryIncone_table_name = "categoryIncone";

    /*Subcategory table attributes*/
    public static final String SUBCATEGORY_ID = "SuCat_ID";
    public static final String CATID = "CategoryID";
    public static final String CATNAME = "CategoryName";
    public static final String SUBCAT_NAME = "SubCategoryName";
    public static final String SUBCATEGORY_TABLE_NAME = "subcategory";


    static final String TABLE1 = "Exp_Category";
    static final String TABLE5 = "Inc_Category";
    static final String TABLE2 = "Budget";
    static final String TABLE3 = "Expenses";
    static final String TABLE4 = "Incomes";
    static final String SUBCAT = "Sub_Categories";
    public final String TABLE6 = "month_budget_table";
    //------------------------------------------------
    static final String TABLE7 = "PaymentScheduler";


    static final String C_ID = "_id";
    static final String Name = "name";
    static final String B_ID = "_id";
    static final String Description = "description";
    static final String Amount = "amount";

    public static final String ID1 = "_id";
    public static final String DATE_T1 = "date1";
    public static final String CATEGORY = "category";
    public static final String DETAIL = "detail";
    public static final String AMOUNT1 = "amount1";
    public static final String STATUS = "status";
    public static final String EX_YEAR = "exyear";
    public static final String EX_MONTH = "exmonth";




    public  static  final  String COL_1 = "YEAR";
    public  static  final  String COL_2 = "MONTH";
    public  static  final  String COL_3 = "AMOUNT";

    /**
     * Create table queries with the use of defined attributes names which is above mentioned.Category Table for income and expense is
     * created seperately and Subcategory for expense is also created.
     */
    public String CREATE_CATEGORY_QUERY =
            "Create table "+ CATEGORY_TABLE_NAME+
                    "("+ Category_ID+ " Integer PRIMARY KEY AUTOINCREMENT, "+
                    CATEGORY_NAME+ " Text " +
                    " );";

    public String CREATE_categoryIncone_QUERY =
            "Create table "+ categoryIncone_table_name+
                    "("+ CategoryIncome_ID+ " Integer PRIMARY KEY AUTOINCREMENT, "+
                    categoryIncone_Name+ " Text " +
                    " );";

    public String CREATE_SUBCATEGORY_QUERY =
            "CREATE TABLE "+ SUBCATEGORY_TABLE_NAME+
                    "("+SUBCATEGORY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    CATID+" INTEGER, "+
                    CATNAME+" TEXT,"+
                    SUBCAT_NAME+ " TEXT, "+
                    "FOREIGN KEY ("+CATID+", "+CATNAME+") REFERENCES "+CATEGORY_TABLE_NAME+
                    " ("+Category_ID+", "+CATEGORY_NAME+")"+
                    "ON DELETE CASCADE ON UPDATE CASCADE"+
                    ")";



    public DataBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, database_version);
    }
    // Called when no database exists in disk and the helper class needs
    // to create a new one.

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE1 + "(" + C_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + Name + " text unique not null)");

        db.execSQL("CREATE TABLE " + TABLE5 + "(" + C_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + Name + " text unique not null)");

        db.execSQL("CREATE TABLE " + TABLE2 + "(" + B_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + Description + " text,"
                + Amount + " text, FOREIGN KEY (" + Description + ") REFERENCES " + TABLE1 + "(" + Name + "));");


        db.execSQL("CREATE TABLE " + TABLE3 + " ( "
                + ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DATE_T1 + " text, "
                + CATEGORY + " text, "
                + DETAIL + " text, "
                + STATUS + " text, "
                + EX_YEAR + " text, "
                + EX_MONTH + " text, "
                + AMOUNT1 + " text, FOREIGN KEY (" + CATEGORY + ") REFERENCES " + TABLE1 + "(" + Name + "));");


        db.execSQL("CREATE TABLE " + TABLE4 + " ( "
                + ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DATE_T1 + " text, "
                + CATEGORY + " text, "
                + DETAIL + " text, "
                + STATUS + " text, "
                + EX_YEAR + " text, "
                + EX_MONTH + " text, "
                + AMOUNT1 + " text, FOREIGN KEY (" + CATEGORY + ") REFERENCES " + TABLE5 + "(" + Name + "));");


       /* db.execSQL("CREATE TABLE " + TABLE_NAME + " ( "
               // +COL_1 + " INTEGER, "
                +COL_2 + " INTEGER PRIMARY KEY, "
                +COL_3 + " DOUBLE)");

*/
        db.execSQL("CREATE TABLE " + TABLE6 + "(" + COL_2
                + " INTEGER PRIMARY KEY ," + COL_3 + " DOUBLE)");

        // db.execSQL("CREATE TABLE month_budget_table (Month INTEGER PRIMARY KEY, Amount DOUBLE)");

        //---------------------------------------------------------------------------------------------------------------------------
        //Payment scheduler table create query
        db.execSQL("CREATE TABLE " + TABLE7 + " ( "
                + ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DATE_T1 + " text, "
                + CATEGORY + " text, "
                + DETAIL + " text, "
                + STATUS + " text, "
                + EX_YEAR + " text, "
                + EX_MONTH + " text, "
                + AMOUNT1 + " text, FOREIGN KEY (" + CATEGORY + ") REFERENCES " + TABLE1 + "(" + Name + "));");

        db.execSQL("insert into   month_budget_table (MONTH, AMOUNT) values(3,25000)");

        db.execSQL("insert into " +  TABLE1 +  "(" + C_ID +"," + Name +") values(1,'Food')");
        db.execSQL("insert into " +  TABLE1 +  "(" + C_ID +"," + Name +") values(2,'Clothing')");
        db.execSQL("insert into " +  TABLE1 +  "(" + C_ID +"," + Name +") values(3,'Home')");

        db.execSQL("insert into " +  TABLE3 +  "(" + ID1 +"," + DATE_T1 +"," + CATEGORY +"," + DETAIL +"," + STATUS +"," + EX_YEAR +"," + EX_MONTH +"," + AMOUNT1 +") values(1,'4-1-2016','Food','lunch','paid','2017','1','500')");


        db.execSQL("insert into " +  TABLE5 +  "(" + C_ID +"," + Name +") values(1,'Salary')");
        db.execSQL("insert into " +  TABLE5 +  "(" + C_ID +"," + Name +") values(2,'Pocket Money')");
        db.execSQL("insert into " +  TABLE5 +  "(" + C_ID +"," + Name +") values(3,'Repayment')");


        db.execSQL(CREATE_CATEGORY_QUERY);
        db.execSQL(CREATE_categoryIncone_QUERY);
        db.execSQL(CREATE_SUBCATEGORY_QUERY);
        db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE);


    }


    // Called when there is a database version mismatch meaning that the version
    // of the database on disk needs to be upgraded to the current version.
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        // Log the version upgrade.
        Log.w("TaskDBAdapter", "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");

        // Upgrade the existing database to conform to the new version. Multiple
        // previous versions can be handled by comparing _oldVersion and _newVersion
        // values.
        // The simplest case is to drop the old table and create a new one.
        _db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        // Create a new one.
        onCreate(_db);
    }

    //Check whether the same data is in the table by using this method. Here the method pass an attribute called name which the user get any
    //category name. that name attribute is checked through the database if the results is false, an negative aspect will be sent as a
    //feedback.
    public boolean checkIdExist(String name) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(DataBaseHelper.CATEGORY_TABLE_NAME, null, null, null, null
                , null, null);
        while (c.moveToNext()) {
            if (c.getString(1).equals(name))
                return false;
        }

        return true;
    }

    public boolean checkIdExistSubcategory(String name, String Catname) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(DataBaseHelper.SUBCATEGORY_TABLE_NAME, null, null, null, null
                , null, null);
        while (c.moveToNext()) {
            if (c.getString(3).equals(name) && (c.getString(2).equals(Catname)))
                return false;
        }

        return true;
    }


    /**
     * methods for each type of categories and subcategories to get the values
     * @return it
     */

    public ArrayList<category> getCategories() {
        ArrayList<category> arrayList = new ArrayList<category>(); //create an arraylist having objects in it
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(DataBaseHelper.TABLE1, null, null, null, null
                , null, null); //cursor contain collection of data for particula query
        while (c.moveToNext()) {
            category cat = new category(c.getInt(0), c.getString(1)); //get the name and id
            arrayList.add(cat);//assign those name to the arraylist

        }

        return arrayList;
    }

    //income
    public ArrayList<category> getCategories1() {
        ArrayList<category> arrayList = new ArrayList<category>(); //create a araaylist having objects in it
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(DataBaseHelper.TABLE5, null, null, null, null, null, null); //cursor contain collection of data for particula query
        while (c.moveToNext()) {
            category cat = new category(c.getInt(0), c.getString(1)); //get the name and id
            arrayList.add(cat);//assign those name and id to the arraylist

        }

        return arrayList;
    }

    public ArrayList<categoryIncone> getIncomeCategories() {
        ArrayList<categoryIncone> arrayList = new ArrayList<categoryIncone>(); //create an arraylist having objects in it
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(DataBaseHelper.TABLE1, null, null, null,
                null, null, null); //cursor contain collection of data for particula query - is this right???????????? TABLE1 is expense category!!
        while (c.moveToNext()) {
            categoryIncone cat = new categoryIncone(c.getString(1)); //get the name
            arrayList.add(cat);//assign those name to the arraylist

        }

        return arrayList;
    }

    public ArrayList<SubCategoryExpense> getSubCategoriesExpense() {
        ArrayList<SubCategoryExpense> arrayList = new ArrayList<SubCategoryExpense>(); //create an arraylist having objects in it
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(DataBaseHelper.SUBCAT, null, null, null,
                null, null, null); //cursor contain collection of data for particula query
        while (c.moveToNext()) {
            SubCategoryExpense Subcat = new SubCategoryExpense(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3)); //get the name
            arrayList.add(Subcat);//assign those name to the arraylist

        }

        return arrayList;
    }


    public int getId(String name){

        Cursor c = db.rawQuery("SELECT "+ Category_ID + " FROM"+ CATEGORY_TABLE_NAME +
                " WHERE "+ CATEGORY_NAME + "like '"+name+"'", null);

        if(c.moveToNext()){
            int id = Integer.parseInt(c.getString(0));
            return id;
        }
        return -1;
    }

    //check the budget amount with expenses
    public String checkBudget(String cat, int month) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT e.category " +
                "FROM Expenses e, Budget b " +
                "WHERE e.category=b.description and e.exmonth = " + month + " and e.category='" + cat +
                "' GROUP BY e.category " +
                "HAVING sum(amount1)>b.amount";


        Cursor c = db.rawQuery(query, null);
        if (c.moveToNext()) {
            String category = c.getString(0);
            return category;
        }

        return null;
    }

    //get the category expenses
    public double getCategoryExpences(String category) {
        ArrayList<Expence> expences = new ArrayList<Expence>();

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int tmonth = c.get(Calendar.MONTH);
        int month = tmonth + 1;
        double total = 0.0;
        String amount;

        String query = "SELECT SUM(amount1) FROM " + TABLE3 + " WHERE " + EX_YEAR + "='" + year + "' and " + EX_MONTH + "='" + month + "' and " + CATEGORY + "='" + category + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToNext()) {

            amount = cursor.getString(0);

        }
        else {
            return total;
        }
        return Double.parseDouble(amount);
    }

    public boolean insertMonthlyBudget(int month, double amount){
        long result;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL_1, 2016);
        contentValues.put(COL_2,month);
        contentValues.put(COL_3,amount);

        Cursor res = db.rawQuery("select * from " + TABLE6 , null);

        if (res.getCount() == 0) {
            result = db.insert(TABLE6,null,contentValues);
        }
        else {
            result = db.update(TABLE6,contentValues, COL_2+"="+month , null);
        }

        if(result == -1)
            return  false;
        else
            return true;
    }

    public Cursor getMonthlyBudget(int month){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE6 + " where " + COL_2 + "=" + month, null);

        return  res;
    }

    public Cursor calBudget(int month){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE6 + " where " + COL_2 + " = " + month, null);



        return  res;

    }

    public ArrayList getCategoryDetail(String category)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[]columns=new String[]{ "_id","category","detail","amount1"};
        Cursor c =db.query(TABLE3, columns, null, null, null, null, null, null);



        ArrayList<String> expenses = new ArrayList<String>();




        // looping through all rows and adding to list
        String detail="";
        if (c.moveToFirst()) {
            do {

                if(category.equals(c.getString((c.getColumnIndex("category")))))

                {
                    detail=(c.getString(c.getColumnIndex("detail")));


                    expenses.add(detail);}
            } while (c.moveToNext());
        }
        c.close();
        return expenses;


    }

    /**check the budget amount with expenses*/
    public boolean checkBudget(String cat) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT e.category " +
                "FROM expenses e, budget b " +
                "WHERE e.category=b.description and e.category='" + cat +
                "' GROUP BY e.category " +
                "HAVING sum(amount1)>b.amount";


        Cursor c = db.rawQuery(query, null);
        if (c.moveToNext()) {
            return true;

        }

        return false;
    }

    public boolean checkBudgetEarly(String cat, int month){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select sum(amount1),b.amount from expenses e, budget b where e.category=b.description and e.exmonth = " + month + " and e.category='" + cat + "'";

        Cursor c = db.rawQuery(query, null);
        if (c.moveToNext()){
            double dif = c.getDouble(1) - c.getDouble(0);

            if (dif <= 2000){
                return true;
            }
            return false;
        }
        return false;
    }

    public ArrayList<SubCategory> getSubCategories(String category){
        ArrayList<SubCategory> arrayList = new ArrayList<SubCategory>(); //create a araaylist having objects in it
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select *  from " + SUBCAT + " where " + CATNAME + " =  '" + category + "'", null); //cursor contain collection of data for particula query
        while (c.moveToNext()) {
            SubCategory subcat = new SubCategory(c.getInt(0),c.getString(1),c.getString(2)); //get the name of category and sub categoryand id
            arrayList.add(subcat);//assign those names and id to the arraylist

        }

        return arrayList;
    }

    public double getTodaysIncome(){
        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-M-d");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);

        double amount;

        String query = "SELECT SUM(amount1) FROM " + TABLE4 + " WHERE " + DATE_T1 + "= '"  + thisDate + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext()) {
            amount = cursor.getDouble(0);
        }
        else {
            return 1;
        }
        return amount;
    }


    public double getTodaysExpense(){
        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-M-d");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);

        double amount;

        String query = "SELECT SUM(amount1) FROM " + TABLE3 + " WHERE " + DATE_T1 + "= '" + thisDate +"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext()) {
            amount = cursor.getDouble(0);
        }
        else {
            return 1;
        }
        return amount;
    }

    public Cursor getAmountDistribution(double amount){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from Expenses where exmonth = 3",null);

        return  c;
    }

    public double getBudgetForMonth(int month){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE6 + " where " + COL_2 + " = " + month , null );

        if (res.moveToNext()){
            return res.getDouble(1);
        }
        else{
            return 1;
        }

    }

    public double yearlyBudget(int month) {
        SQLiteDatabase db = this.getReadableDatabase();
        double yearBudget = 0.0;
        double fBudget = 0;
        double pBudget = 0;

        Cursor res = db.rawQuery("select " + COL_3 + " from " + TABLE6 + " where " + COL_2 + " = " + month, null);
        Cursor res1 = db.rawQuery("select sum(" + COL_3 + ") from " + TABLE6 + " where " + COL_2 + " < " + month, null);

        if (res.moveToNext()) {
            fBudget = res.getDouble(0);
        }


        if (res1.moveToNext()) {
            pBudget = res1.getDouble(0);
        }


        int balMonths = 12 - month;
        yearBudget = (fBudget * (balMonths + 1)) + pBudget;

        return yearBudget;
    }

    public boolean fixDup(String cat,String des , String date, String am){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c1 = db.rawQuery("Select " + AMOUNT1 + " from " + TABLE3 + " Where CATEGORY = '"+ cat + "' AND " + DATE_T1 + " = '" + date + "' AND " + DETAIL + " = '" + des + "'",null);

        if(c1.moveToNext())
        {
            String amo = c1.getString(0);
            double total = Integer.parseInt(amo) + Integer.parseInt(am);
            String total_amo = String.valueOf(total);
            db.execSQL("update " + TABLE3 + " set " + AMOUNT1 + " = '" + total_amo + "' Where CATEGORY = '" + cat + "' AND " + DATE_T1 + " = '" + date + "' AND " + DETAIL + " = '" + des + "'");

            return true;
        }

        return  false;

    }

    //Payment Scheduler
    //-----------------------------------------------------------------------------------------------------------------------------------
    /*
    **Method to insert data into payment scheduler table
    * Take details as parameters
    * Returns a boolean value
    */
    String status = "onDue";

    public boolean insertData(String _date, String _category, String _description, String _amount, String _year, String _month)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE_T1, _date);
        contentValues.put(CATEGORY, _category);
        //contentValues.put(T1_SubCategory, _subcategory);
        contentValues.put(DETAIL, _description);
        contentValues.put(STATUS, status);
        contentValues.put(EX_YEAR, _year);
        contentValues.put(EX_MONTH, _month);
        contentValues.put(AMOUNT1, _amount);
        long result = db.insert(TABLE7, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;

    }

    /*
    **To get category details from expense category table
    * Returns a Cursor with the details
    */
    public Cursor getAllPaymentsData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE1, null);
        return result;
    }

    /*
    **To get payment details from payment scheduler table for a specific category
    * Takes category string value as the parameter
    * Returns a Cursor with the details
    */
    public Cursor getCategoryPayments(String category)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE7 + " where " +CATEGORY+ " = '" + category +"' AND " +STATUS+ " = 'onDue'", null);
        return result;
    }

    /*
    **To mark a payment in payment scheduler table as "Done"
    * Takes id as the parameter
    */
    public void markDone(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update " + TABLE7 + " set " + STATUS + " = 'Done' where " + ID1 + " = " + id);
    }

    /*
    **To get payment details from payment scheduler table for a specific month
    * Takes month as the parameter
    */
    public Cursor getMonthlyPayments(String month)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int monthVal = getMonthInt(month);
        Cursor result = db.rawQuery("select * from " + TABLE7 + " where " +EX_MONTH+ " = '"+monthVal+"' AND "+EX_YEAR+" = '"+year+"' AND " +STATUS+ " = 'onDue'", null);
        return result;
    }

    /*
    **To get Amount of a payment from payment scheduler table for a specific date
    * Takes date as the parameter
    * Returns the amount as double value
    */
    public double getFuturePayment(String date)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select " + AMOUNT1 + " from " + TABLE7 + " where " + DATE_T1 + " = '" + date + "'", null);
        double e = 0;

        if(res.moveToNext())
        {

            e = res.getDouble(0);
            return e;
        }

        return e;

    }

    /*
    **To get Total Amount from payment scheduler table for a specific month
    * Takes month as the parameter
    * Returns the total amount as double value
    */
    public double calcMonthlyTotal(String month)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int monthVal = getMonthInt(month);
        Cursor result = db.rawQuery("select SUM(amount1) from " + TABLE7 + " where " +EX_MONTH+ " = '" + monthVal+"' AND "+EX_YEAR+" = '"+year+"' AND " +STATUS+ " = 'onDue'", null);
        result.moveToFirst();
        double e = 0;
        e = result.getDouble(0);

        return e;
    }

    /*
    **To get the integer value for a specific month
    * Takes month as the parameter
    * Returns the number
    */
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