package tech.iwish.ONhome;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import tech.iwish.ONhome.cart.ShopingCart;

public class ConfirmOrder extends AppCompatActivity {
Button CashOnDelivery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        CashOnDelivery = (Button) findViewById(R.id.button7);
        CashOnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopingCart shopingCart = new ShopingCart();




            }
        });
    }
}
