package tech.iwish.ONhome.adaptors;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tech.iwish.ONhome.CartActivity;
import tech.iwish.ONhome.ItemDescription;
import tech.iwish.ONhome.R;
import tech.iwish.ONhome.cart.ShopingCart;

public class CartAdaptor extends RecyclerView.Adapter<CartAdaptor.MyView> {
Activity activity;


    public class MyView extends RecyclerView.ViewHolder {
        public TextView product_name;
        public TextView item_id;
        public TextView price;
        public TextView originalprice;
        public TextView description;
        public TextView off;
        public ImageView item_img_view;

        public ImageButton delete;
        public TextView quantity;


        public MyView(View view) {
            super(view);
                off= view.findViewById(R.id.textView16);
                item_img_view = view.findViewById(R.id.imageView2);
                product_name = view.findViewById(R.id.textView17);
                description = view.findViewById(R.id.textView18);
                price = view.findViewById(R.id.textView20);
                originalprice = view.findViewById(R.id.textView21);

                delete = view.findViewById(R.id.imageButton);
                quantity = view.findViewById(R.id.textView23);




        }
    }

    public CartAdaptor(CartActivity cartActivity) {

        this.activity = cartActivity;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);


        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

    }

    @Override
    public int getItemCount() {
        int count = 10;
        return count;
    }
}

