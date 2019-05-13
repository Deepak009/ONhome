package tech.iwish.ONhome.adaptors;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tech.iwish.ONhome.ItemDescription;
import tech.iwish.ONhome.R;
import tech.iwish.ONhome.cart.ShopingCart;

public class CartAdaptor extends RecyclerView.Adapter<CartAdaptor.MyView> {


    public class MyView extends RecyclerView.ViewHolder {
        public TextView item_id;
        public TextView price;
        public TextView save_price;
        public TextView description;
        public TextView Off_price;
        public ImageView item_img_view;

        public MyView(View view) {
            super(view);
            /*Off_price = view.findViewById(R.id.textView4);
            item_img_view = view.findViewById(R.id.imageView3);
            description = view.findViewById(R.id.textView8);
            price = view.findViewById(R.id.textView10);
            Off_price = view.findViewById(R.id.textView11);*/
        }
    }

    public CartAdaptor() {


    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);


        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
            /*ShopingCart Cart = new ShopingCart();

            holder.price.setText("Rs. "+String.valueOf(Cart.price_a.get(position)));
            holder.description.setText(String.valueOf(Cart.description_a.get(position)));*/

    }

    @Override
    public int getItemCount() {
        int count = 10;
        return count;
    }
}

