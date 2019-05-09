package tech.iwish.ONhome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import tech.iwish.ONhome.adaptors.BestSellAdaptor;
import tech.iwish.ONhome.adaptors.CartAdaptor;

public class CartActivity extends AppCompatActivity {
RecyclerView cart_recyclerView;
LinearLayoutManager HorizontalLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cart_recyclerView = (RecyclerView) findViewById(R.id.cart_items);
        HorizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cart_recyclerView.setLayoutManager(HorizontalLayout);
        cart_recyclerView.setAdapter(new CartAdaptor());










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
