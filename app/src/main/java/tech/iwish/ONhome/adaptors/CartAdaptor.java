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

        }
    }

    public CartAdaptor() {



    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_single_item, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {


    }

    @Override
    public int getItemCount() {
        return 10;
    }
}

