package com.example.erikgarcia.otm.SignupLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.erikgarcia.otm.MainActivity;
import com.example.erikgarcia.otm.R;

public class SignupActivity extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    EditText passwordVerify;
    Button submitButton;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.textColor));

        final Intent i = new Intent(this, MainActivity.class);

        submitButton = (Button)findViewById(R.id.submit_button);
        password = (EditText)findViewById(R.id.password);
        passwordVerify = (EditText)findViewById(R.id.verify_password);
        email = (EditText)findViewById(R.id.email);
        firstName = (EditText)findViewById(R.id.first_name);
        lastName = (EditText)findViewById(R.id.last_name);
        username = (EditText)findViewById(R.id.username);

        final String[] noChar = getResources().getStringArray(R.array.breakChars);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fName = firstName.getText().toString(),
                        lName = lastName.getText().toString(),
                        emailStr = email.getText().toString(),
                        pass = password.getText().toString(),
                        verifyPass = passwordVerify.getText().toString(),
                        user = username.getText().toString();

                if (pass.equals("") && verifyPass.equals("")) {
                    Toast.makeText(SignupActivity.this, "Please Enter a Valid Password", Toast.LENGTH_LONG).show();
                }
                if (!pass.equals(verifyPass)) {
                    Toast.makeText(SignupActivity.this, "Passwords Do Not Match, Please Enter Matching Passwords", Toast.LENGTH_LONG).show();
                    password.setText(null);
                    passwordVerify.setText(null);
                }

                if (!emailStr.contains("@") || !emailStr.contains(".com")){
                    Toast.makeText(SignupActivity.this, "Please Enter a Valid Email", Toast.LENGTH_LONG).show();
                    email.setText(null);
                }

                if (fName.matches("[a-zA-Z]")){
                    Toast.makeText(SignupActivity.this, "Please Enter a Valid First Name", Toast.LENGTH_LONG).show();
                }

                if (lName.matches("[a-zA-Z]")){
                    Toast.makeText(SignupActivity.this, "Please Enter a Valid Last Name", Toast.LENGTH_LONG).show();
                }

                boolean found = false;
                for (int i=0; i<noChar.length; i++){
                    if(user.contains(noChar[i])){
                        found = true;
                        break;
                    }
                }
                if(found){
                    Toast.makeText(SignupActivity.this, "Username Invalid", Toast.LENGTH_LONG).show();
                }

                if(pass.equals(verifyPass)
                        && (emailStr.contains("@") || emailStr.contains(".com"))
                        && !fName.matches("[a-zA-Z]") && !lName.matches("[a-zA-Z]")
                        && !found){
                    Toast.makeText(SignupActivity.this, "User Successfully created", Toast.LENGTH_LONG).show();
                    //Handle Saving User Data Here

                    startActivity(i);
                }
            }
        });
    }

    //TODO check that the password, username, and email are valid (i.e at least 8 char or whatever)

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
