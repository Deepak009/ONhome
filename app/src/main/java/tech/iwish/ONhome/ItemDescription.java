package tech.iwish.ONhome;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



public class ItemDescription extends AppCompatActivity {
TextView addtocart;
    String stringImageUri;
    int imagePosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (getIntent() != null) {
            getIntent().getStringExtra("item_id");
            getIntent().getStringExtra("price");
            getIntent().getStringExtra("save_price");
            getIntent().getStringExtra("description");
            getIntent().getStringExtra("off_price");

            Log.e("IDee",getIntent().getStringExtra("item_id"));

        }

        addtocart = (TextView) findViewById(R.id.text_action_bottom1);

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeActivity.Cart_Count.add(1);

                HomeActivity.Cartview.setText(String.valueOf(HomeActivity.Cart_Count.size()));


                Log.e("Cart_Size", String.valueOf(HomeActivity.Cart_Count.size()));
                Toast.makeText(ItemDescription.this,"Item added to cart.",Toast.LENGTH_SHORT).show();

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
