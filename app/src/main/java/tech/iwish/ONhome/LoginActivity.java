package tech.iwish.ONhome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tech.iwish.ONhome.UserManager.UserSessionManager;
import tech.iwish.ONhome.activity.MainActivity;

public class LoginActivity extends AppCompatActivity {
TextView username;
TextView password;
Button login_btn;
    // User Session Manager Class
    UserSessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = (TextView) findViewById(R.id.editText2);
        password = (TextView) findViewById(R.id.editText3);

        final String user_id = username.getText().toString();
        final String pass = password.getText().toString();

        login_btn = (Button) findViewById(R.id.button6);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session = new UserSessionManager(getApplicationContext());

                        // Creating user login session
                        // For testing i am stroing name, email as follow
                        // Use user real data
                        session.createUserLoginSession("root", "dpkdhariwal@gmail.com");

                        // Staring MainActivity
                        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(i);
                        finish();


            }

        });


    }
}
