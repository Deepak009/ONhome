package tech.iwish.ONhome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tech.iwish.ONhome.Connection.ConnectionServer;
import tech.iwish.ONhome.UserManager.UserSessionManager;
import tech.iwish.ONhome.activity.MainActivity;

import static tech.iwish.ONhome.helper.Constants.Check;

public class LoginActivity extends AppCompatActivity {
EditText username;
EditText password;
Button login_btn;
    // User Session Manager Class
    UserSessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.user_id_text);
        password = (EditText) findViewById(R.id.passwordedit);
        login_btn = (Button) findViewById(R.id.button6);




        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                String pass = password.getText().toString();



                if(isValidMail(email)){
                    if(isValidPassword(pass)){
                        Toast.makeText(LoginActivity.this, "Hellow", Toast.LENGTH_SHORT).show();

                        session = new UserSessionManager(getApplicationContext());

                        ConnectionServer connectionServer = new ConnectionServer();
                        connectionServer.set_url(Check);
                        connectionServer.requestedMethod("POST");
                        connectionServer.buildParameter("email",email);
                        connectionServer.buildParameter("pass",pass);
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
                                            Toast.makeText(LoginActivity.this, "Session Created", Toast.LENGTH_SHORT).show();
                                        }
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                    else{
                        password.setError("Not Valid Password");

                    }
                }





                       /* session.createUserLoginSession("root", "dpkdhariwal@gmail.com");

                        // Staring MainActivity
                        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(i);
                        finish();
*/

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
            username.setError("Not Valid Email");
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
}
