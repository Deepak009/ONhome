package tech.iwish.ONhome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tech.iwish.ONhome.Connection.ConnectionServer;
import tech.iwish.ONhome.UserManager.UserSessionManager;

import static tech.iwish.ONhome.helper.Constants.Check;
import static tech.iwish.ONhome.helper.Constants.SignupUrl;

public class SignUpActivity extends AppCompatActivity {
EditText username, useremail, usermobile, Password, CPassword,Address,Messege;
Button Sign_btn;
UserSessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Sign_btn = (Button) findViewById(R.id.button15);

        Sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = (EditText) findViewById(R.id.editText);
                useremail= (EditText) findViewById(R.id.editText2);
                usermobile= (EditText) findViewById(R.id.editText5);
                Password= (EditText) findViewById(R.id.editText6);
                CPassword= (EditText) findViewById(R.id.editText7);
                Address= (EditText) findViewById(R.id.editText4);
                Messege= (EditText) findViewById(R.id.editText8);

                if(isValidMail(useremail.getText().toString())){
                    if(isValidPassword(Password.getText().toString())){

                        if(Password.getText().toString().equals(CPassword.getText().toString())){

                            Toast.makeText(SignUpActivity.this, "Hellow", Toast.LENGTH_SHORT).show();

                                session = new UserSessionManager(getApplicationContext());


                            ConnectionServer connectionServer = new ConnectionServer();
                            connectionServer.set_url(SignupUrl);
                            connectionServer.requestedMethod("POST");
                            connectionServer.buildParameter("email",useremail.getText().toString());
                            connectionServer.buildParameter("pass",Password.getText().toString());
                            connectionServer.buildParameter("username",username.getText().toString());
                            connectionServer.buildParameter("usermobile",usermobile.getText().toString());
                            connectionServer.buildParameter("Address",Address.getText().toString());
                            connectionServer.buildParameter("Messege",Messege.getText().toString());

                            connectionServer.execute(new ConnectionServer.AsyncResponse() {
                                @Override
                                public void processFinish(String output) {

                                    Log.e("Server Response",output);
                                    try {
                                        JSONArray products = new JSONArray(output);
                                        Log.e("Lenth", String.valueOf(products.length()));

                                        for(int i=0; i<products.length(); i++){
                                            JSONObject productObject = products.getJSONObject(i);

                                            if (productObject.getString("status").equals("success")){


                                                        ConnectionServer connectionServer = new ConnectionServer();
                                                        connectionServer.set_url(Check);
                                                        connectionServer.requestedMethod("POST");
                                                        connectionServer.buildParameter("email",useremail.getText().toString());
                                                        connectionServer.buildParameter("pass",Password.getText().toString());
                                                        connectionServer.execute(new ConnectionServer.AsyncResponse() {
                                                            @Override
                                                            public void processFinish(String output) {

                                                                Log.e("Server Response",output);
                                                                try {
                                                                    JSONArray products = new JSONArray(output);
                                                                    Log.e("Lenth", String.valueOf(products.length()));

                                                                    String id;
                                                                    String username;
                                                                    String name;
                                                                    String email;
                                                                    String pass;

                                                                    for(int i=0; i<products.length(); i++){
                                                                        JSONObject productObject = products.getJSONObject(i);

                                                                        id = productObject.getString("id");
                                                                        username = productObject.getString("username");
                                                                        name= productObject.getString("name");
                                                                        email= productObject.getString("email");
                                                                        pass= productObject.getString("password");

                                                                        if(session.createUserLoginSession(id, username,name, email, pass)==true){
                                                                            Toast.makeText(SignUpActivity.this, "Wellcom Mr."+username, Toast.LENGTH_SHORT).show();
                                                                            Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                                                                            startActivity(intent);
                                                                        }
                                                                    }


                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                }

                                                            }
                                                        });



                                            }




                                        }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });

                        }


                    }
                    else{
                        Password.setError("Not Valid Password");

                    }
                }


            }
        });

    }
    private boolean isValidMail(String email) {
        boolean check;
        Pattern p;
        Matcher m;

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        p = Pattern.compile(EMAIL_STRING);

        m = p.matcher(email);
        check = m.matches();

        if(!check) {
            useremail.setError("Not Valid Email");
        }
        return check;
    }
    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
    public  boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();

        if(id == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
