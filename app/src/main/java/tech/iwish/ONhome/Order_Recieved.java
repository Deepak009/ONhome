package tech.iwish.ONhome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import tech.iwish.ONhome.UserManager.UserSessionManager;

public class Order_Recieved extends AppCompatActivity {
Button order_recieved;
UserSessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__recieved);

        order_recieved = (Button) findViewById(R.id.button9);
        order_recieved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session = new UserSessionManager(Order_Recieved.this);
                if(session.isUserLoggedIn()){
                    Intent intent = new Intent(Order_Recieved.this, Myorders.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Order_Recieved.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
