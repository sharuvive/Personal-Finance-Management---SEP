package com.example.pri.financemanagement;
//Jathevan
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Logout extends Activity {
    LoginDataBaseAdapter loginDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);



        loginDatabaseAdapter=new LoginDataBaseAdapter(this);
        loginDatabaseAdapter=loginDatabaseAdapter.open();

       TextView logoutsys = (TextView) findViewById(R.id.buttonLogout);
        logoutsys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Logout.this, MainActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// clear back stack
                startActivity(myIntent);
                finish();

            }


        });



    }
}
