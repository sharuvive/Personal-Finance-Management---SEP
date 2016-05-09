package com.example.pri.financemanagement;

/**
 * Created by pri on 2/1/2016.
 */

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;


public class MainActivityBudget extends TabActivity {
    public static TabHost tabHost;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_budget);


        // create the TabHost that will contain the Tabs
        tabHost = (TabHost) findViewById(android.R.id.tabhost);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("1");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("2");
       // TabHost.TabSpec tab3 = tabHost.newTabSpec("3");
       // TabHost.TabSpec tab4 = tabHost.newTabSpec("4");
        TabHost.TabSpec tab5 = tabHost.newTabSpec("5");
        TabHost.TabSpec tab6 = tabHost.newTabSpec("6");
        TabHost.TabSpec tab7 = tabHost.newTabSpec("7");

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected

        tab1.setIndicator("Budget");
        Intent intent1 = new Intent(MainActivityBudget.this, MonthlyBudget.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        tab1.setContent(new Intent(intent1));


        tab2.setIndicator("Category Budget");
        Intent intent2 = new Intent(MainActivityBudget.this, Budget_activity.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        tab2.setContent(new Intent(intent2));






        tab5.setIndicator("Today");
        Intent intent5 = new Intent(MainActivityBudget.this, TodaysTransaction.class);
        intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        tab5.setContent(new Intent(intent5));

        tab6.setIndicator("Amount");
        Intent intent6 = new Intent(MainActivityBudget.this, AmountDistribution.class);
        intent6.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        tab6.setContent(new Intent(intent6));

        tab7.setIndicator("Budget");
        Intent intent7 = new Intent(MainActivityBudget.this, BudgetForMonth.class);
        intent7.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        tab7.setContent(new Intent(intent7));





        /** Add the tabs  to the TabHost to display. */

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
       // tabHost.addTab(tab3);
       // tabHost.addTab(tab4);
        tabHost.addTab(tab5);
        tabHost.addTab(tab6);
        tabHost.addTab(tab7);

        //tabHost.addTab(tab3);
        tabHost.getTabWidget().setBackgroundColor(Color.parseColor("#FFFFFF"));


    }
}