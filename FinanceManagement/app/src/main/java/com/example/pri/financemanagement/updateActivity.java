package com.example.pri.financemanagement;
//Jathevan
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class updateActivity extends Activity {
    EditText editTextName, editTextPhone, editTextPassword, editTextConfirmPassword, editTextCurrency, editusername;
    Button updatebtn ,addbtn ,Profilebtn;

    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        // get Instance  of Database Adapter
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        updatebtn = (Button) findViewById(R.id.updatebtn);
        addbtn= (Button)findViewById(R.id.buttonAdd);
        Profilebtn = (Button)findViewById(R.id.btnProfile);


        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        editTextCurrency = (EditText) findViewById(R.id.editTextCurrency);
        editusername = (EditText) findViewById(R.id.editUseName);

        //String uname1 = loginDataBaseAdapter.getUserName();
        //editusername.setText(uname1);

        final String uname1 = getIntent().getStringExtra("USERNAME");
        editusername.setText(uname1);



        Profilebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                // Create Intent for SignUpActivity  abd Start The Activity
                Intent intentPro = new Intent(getBaseContext(), Viewprof.class);
                intentPro.putExtra("USERNAME", uname1);
                startActivity(intentPro);
            }
        });

    addbtn.setOnClickListener(new View.OnClickListener() {


                                         public void onClick(View v) {
                                             // TODO Auto-generated method stub


                                          



                                             // Get Refferences of Views
                                             String Name = editTextName.getText().toString();
                                             String password = editTextPassword.getText().toString();
                                             String confirmPassword = editTextConfirmPassword.getText().toString();
                                             String phone = editTextPhone.getText().toString();
                                             String currency = editTextCurrency.getText().toString();
                                             //String uname1=editusername.getText().toString();

                                             String uname1 =  getIntent().getStringExtra("USERNAME");


                                             //  updatebtn=(Button)findViewById(R.id.buttonCreateAccount);
                                             //   updatebtn.setOnClickListener(new View.OnClickListener() {


                                             //      public void onClick(View v) {
                                             // TODO Auto-generated method stub

       /*         String Name=editTextName.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword=editTextConfirmPassword.getText().toString();
                String phone=editTextPhone.getText().toString();
                String currency=editTextCurrency.getText().toString();
                //String uname1=editusername.getText().toString();

                String uname1 = loginDataBaseAdapter.getUserName();
                editusername.setText(uname1);
          */      //*********  String check=checkBoxChange.getText();


                                             // check if any of the fields are vaccant
                                             if (Name.equals("") || phone.equals("") || currency.equals(""))

                                             {
                                                 Toast.makeText(getApplicationContext(), "Please enter the details", Toast.LENGTH_LONG).show();
                                                 return;
                                             }

/*
        if (((CheckBox) v).

                isChecked()

                )

        {

            //****     Toast.makeText(checkBoxChange.this, "Selected", Toast.LENGTH_SHORT).show();
            editTextPassword.setFocusable(true);
            //checkbox.setChecked(false);

        }
*/

                                             // check if both password matches
                                             if (!password.equals(confirmPassword))

                                             {
                                                 Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                                                 return;
                                             } else

                                             {


                                                 // Save the Data in Database


                                                 //   Toast.makeText(getApplicationContext(), "Your account sucessfully updated222 ", Toast.LENGTH_LONG).show();
                                                 loginDataBaseAdapter.addProf(Name, phone, currency, uname1, password);
                                                 Toast.makeText(getApplicationContext(), "Your details sucessfully added ", Toast.LENGTH_LONG).show();
                                             }

                                         }
                                     }

        );



        updatebtn.setOnClickListener(new View.OnClickListener() {
                                         public void onClick(View v) {
                                             // TODO Auto-generated method stub

                                             // Get Refferences of Views
                                             update();

                                         }
                                     }

        );
}


    public void update() {
        String Name = editTextName.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();
        String phone = editTextPhone.getText().toString();
        String currency = editTextCurrency.getText().toString();
        //String uname1=editusername.getText().toString();

        String uname1 =  getIntent().getStringExtra("USERNAME");


        //  updatebtn=(Button)findViewById(R.id.buttonCreateAccount);
        //   updatebtn.setOnClickListener(new View.OnClickListener() {


        //      public void onClick(View v) {
        // TODO Auto-generated method stub

       /*         String Name=editTextName.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword=editTextConfirmPassword.getText().toString();
                String phone=editTextPhone.getText().toString();
                String currency=editTextCurrency.getText().toString();
                //String uname1=editusername.getText().toString();

                String uname1 = loginDataBaseAdapter.getUserName();
                editusername.setText(uname1);
          */      //*********  String check=checkBoxChange.getText();


        // check if any of the fields are vaccant
        if (Name.equals("") || phone.equals("") || currency.equals(""))

        {
            Toast.makeText(getApplicationContext(), "Please enter the details", Toast.LENGTH_LONG).show();
            return;
        }

/*
        if (((CheckBox) v).

                isChecked()

                )

        {

            //****     Toast.makeText(checkBoxChange.this, "Selected", Toast.LENGTH_SHORT).show();
            editTextPassword.setFocusable(true);
            //checkbox.setChecked(false);

        }
*/

        // check if both password matches
        if (!password.equals(confirmPassword))

        {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
            return;
        } else

        {


            // Save the Data in Database


         //   Toast.makeText(getApplicationContext(), "Your account sucessfully updated222 ", Toast.LENGTH_LONG).show();
            loginDataBaseAdapter.updateProf(Name, phone, currency, uname1, password);
            Toast.makeText(getApplicationContext(), "Your account sucessfully updated ", Toast.LENGTH_LONG).show();
        }
    }




    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}
