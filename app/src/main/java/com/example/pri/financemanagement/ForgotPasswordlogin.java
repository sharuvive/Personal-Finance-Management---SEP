package com.example.pri.financemanagement;
//Jathevan
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPasswordlogin extends Activity {


   private EditText emailsend;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.forgotpassword_layout);






          emailsend = (EditText)findViewById(R.id.forgot_email);
        Button mailsend = (Button)findViewById(R.id.send_email);
        mailsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendmail();
                emailsend.setText("");

            }
        });




        TextView backtologin = (TextView) findViewById(R.id.backToMain);
        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    protected void sendmail()
    {

        LoginDataBaseAdapter loginDataBaseAdapter;

        loginDataBaseAdapter = new LoginDataBaseAdapter(ForgotPasswordlogin.this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();


        String mail = emailsend.getText().toString();

        String storedPassword = loginDataBaseAdapter.getSinlgeEntry(mail);
        String sub ="Your password";




        String[] EmailSend = {emailsend.getText().toString()};   //get the user email address
        Intent email = new Intent(Intent.ACTION_SEND,Uri.parse("mailto:"));


        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, EmailSend);// assign the user email address
        email.putExtra(Intent.EXTRA_SUBJECT, sub );
        email.putExtra(Intent.EXTRA_TEXT   , storedPassword); // user's old password

        try {

            // the user can choose the email client

            startActivity(Intent.createChooser(email, "Choose an email client from..."));

        } catch (android.content.ActivityNotFoundException ex) {

            Toast.makeText(ForgotPasswordlogin.this, "No email client installed.",

                    Toast.LENGTH_LONG).show();

        }

    }

}

