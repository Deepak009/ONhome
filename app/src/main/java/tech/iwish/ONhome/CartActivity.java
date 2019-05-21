package tech.iwish.ONhome;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import tech.iwish.ONhome.UserManager.UserSessionManager;
import tech.iwish.ONhome.adaptors.BestSellAdaptor;
import tech.iwish.ONhome.adaptors.CartAdaptor;

public class CartActivity extends AppCompatActivity {
RecyclerView cart_recyclerView;
LinearLayoutManager HorizontalLayout;

    // User Session Manager Class
    UserSessionManager session;
TextView checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cart_recyclerView = (RecyclerView) findViewById(R.id.cart_items);
        HorizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cart_recyclerView.setLayoutManager(HorizontalLayout);
        cart_recyclerView.setAdapter(new CartAdaptor(CartActivity.this));


        checkout = (TextView) findViewById(R.id.text_action_bottom1);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // Session class instance
                    session = new UserSessionManager(getApplicationContext());

                if(session.isUserLoggedIn()==true){
                    Intent intent = new Intent(CartActivity.this, OrderDetail.class);
                    startActivity(intent);
                }
                else if(session.isUserLoggedIn()==false){
                    Intent intent = new Intent(CartActivity.this, LoginActivity.class);
                    startActivity(intent);
                }


            }
        });

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
