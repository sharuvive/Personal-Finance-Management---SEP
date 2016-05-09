package com.example.pri.financemanagement;
//Jathevan
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignIn extends Activity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);// call the login interface


        final EditText editTextUserName=(EditText)findViewById(R.id.editTextUserNameToLogin);//get the user name from login interface
        final  EditText editTextPassword=(EditText)findViewById(R.id.editTextPasswordToLogin);// get the password from login interface

        Button btnSignIn=(Button)findViewById(R.id.buttonSignIn); // delcare the singin button


        Button btnForgotPassword = (Button)findViewById(R.id.buttonForgotPassword); // delcare the forgotpassword  button


        //button click action for signin button
        btnSignIn.setOnClickListener(new View.OnClickListener() {

                                         public void onClick(View v) {

                                             LoginDataBaseAdapter loginDataBaseAdapter;



                                             loginDataBaseAdapter = new LoginDataBaseAdapter(SignIn.this);
                                             loginDataBaseAdapter=loginDataBaseAdapter.open();

                                             final String userName = editTextUserName.getText().toString();
                                             final String password = editTextPassword.getText().toString();

                                             // fetch the Password form database for respective user name
                                                 String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

                                                 // check if the Stored password matches with  Password entered by user
                                                 if (password.equals(storedPassword)) {
                                                     Toast.makeText(SignIn.this, "Welcome to the Personal Finance management", Toast.LENGTH_LONG).show();

                                                     /*Intent intentprofile=new Intent(getBaseContext(),Welcome.class);
                                                     intentprofile.putExtra("USERNAME", userName);
                                                     startActivity(intentprofile);*/

                                                     Intent intentprofile=new Intent(getApplicationContext(),Welcome.class);
                                                     intentprofile.putExtra("USERNAME", userName);
                                                     startActivity(intentprofile);


                                                 // *******    setContentView(R.layout.edit);

                                                     /*Button btnuProfile = (Button) findViewById(R.id.buttonUserProfile);

                                                     Button btnLogout = (Button) findViewById(R.id.buttonLogout);

                                                     btnuProfile.setOnClickListener(new View.OnClickListener() {
                                                         public void onClick(View v) {
                                                             // TODO Auto-generated method stub
                                                             String userName = editTextUserName.getText().toString();

                                                             /// Create Intent for Logout  abd Start The Activity
                                                             Intent intentprofile=new Intent(getBaseContext(),updateActivity.class);
                                                             intentprofile.putExtra("USERNAME", userName);
                                                             startActivity(intentprofile);
                                                         }
                                                     });


                                                     btnLogout.setOnClickListener(new View.OnClickListener() {
                                                         public void onClick(View v) {
                                                             // TODO Auto-generated method stub

                                                             /// Create Intent for Logout  abd Start The Activity
                                                             Intent intentLogout=new Intent(getApplicationContext(),Logout.class);
                                                             startActivity(intentLogout);
                                                         }
                                                     });



                                                     Button btnUserProfile = (Button) findViewById(R.id.buttonUserProfile);

                  btnUserProfile.setOnClickListener(new View.OnClickListener() {
                       public void onClick(View v) {
                           // TODO Auto-generated method stub

                           /// Create Intent for updateActivity  abd Start The Activity
                           Intent intentUpdate=new Intent(getApplicationContext(),updateActivity.class);
                           startActivity(intentUpdate);
                       }
                   });  //go to user profile interface*/


                                                 } else {
                                                     Toast.makeText(SignIn.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                                                 }


                                         }
                                     }

        );

        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ForgotPasswordlogin.class);
                startActivity(i);
            }
        });


    }



    }

