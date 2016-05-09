package com.example.pri.financemanagement;
//Jathevan
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jaathu on 5/2/2016.
 */
public class Viewprof extends Activity {

    TextView txtName ,txtUname , txtPhone ;
    private DataBaseHelper dbHelper;
    public SQLiteDatabase db;

    LoginDataBaseAdapter loginDataBaseAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_prof);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        txtName = (TextView)findViewById(R.id.textName);
        txtPhone = (TextView)findViewById(R.id.textPhone);
        txtUname= (TextView)findViewById(R.id.textMail);


        final String uname1 = getIntent().getStringExtra("USERNAME");
        //  txtName.setText(uname1);

        //  String query = "Select NAME, PHONE From LOGIN Where USERNAME ='" + uname1 + "'";

        //Cursor result = db.rawQuery("Select NAME, PHONE From LOGIN Where USERNAME ='" + uname1 + "'", null);
        Cursor result = loginDataBaseAdapter.getDetail(uname1);
        if(result.moveToNext()){
            txtName.setText(result.getString(1));
            txtPhone.setText(result.getString(2));
            txtUname.setText(result.getString(4));
        }
        else
            Toast.makeText(Viewprof.this, "Noo", Toast.LENGTH_LONG).show();

    }


}
