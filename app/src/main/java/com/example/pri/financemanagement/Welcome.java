package com.example.pri.financemanagement;
//Jathevan
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Jaathu on 5/2/2016.
 */
public class Welcome extends Activity {

    ImageButton bLog,bProf, bInc, bExp, bBud, bCat, bPScedh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);


        bProf = (ImageButton)findViewById(R.id.btnProfile);
        bLog = (ImageButton)findViewById(R.id.btnLogout);
        bInc = (ImageButton)findViewById(R.id.btnInc);
        bExp = (ImageButton)findViewById(R.id.btnExp);
        bBud = (ImageButton)findViewById(R.id.btnBud);
        bCat = (ImageButton)findViewById(R.id.btnCategory);
        bPScedh = (ImageButton)findViewById(R.id.imageButton7);


        bLog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Logout.class);
                startActivity(i);
            }
        });

        final String uname1 = getIntent().getStringExtra("USERNAME");

        bProf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), updateActivity.class);
                i.putExtra("USERNAME", uname1);
                startActivity(i);
            }
        });

        bInc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Income_activity.class);
                i.putExtra("USERNAME", uname1);
                startActivity(i);
            }
        });

        bExp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Expenses_activity.class);
                i.putExtra("USERNAME", uname1);
                startActivity(i);
            }
        });

        bBud.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MainActivityBudget.class);
                i.putExtra("USERNAME", uname1);
                startActivity(i);
            }
        });

        bCat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MainActivityShangavi.class);
                i.putExtra("USERNAME", uname1);
                startActivity(i);
            }
        });

        bPScedh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MainActivity_ps.class);
                i.putExtra("USERNAME", uname1);
                startActivity(i);
            }
        });


    }




}

