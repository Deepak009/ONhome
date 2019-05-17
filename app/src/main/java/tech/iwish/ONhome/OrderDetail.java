package tech.iwish.ONhome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import tech.iwish.ONhome.adaptors.CartAdaptor;
import tech.iwish.ONhome.adaptors.OrderDetailProducts;

public class OrderDetail extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager HorizontalLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.order_items_list);
        HorizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(new OrderDetailProducts(OrderDetail.this));


    }
}
