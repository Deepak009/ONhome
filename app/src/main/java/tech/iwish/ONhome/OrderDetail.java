package tech.iwish.ONhome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tech.iwish.ONhome.adaptors.CartAdaptor;
import tech.iwish.ONhome.adaptors.OrderDetailProducts;

public class OrderDetail extends AppCompatActivity {
TextView Confirm_btn;
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

        Confirm_btn = (TextView) findViewById(R.id.textView32);

        Confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetail.this, ConfirmOrder.class);
                startActivity(intent);
            }
        });


    }
}
