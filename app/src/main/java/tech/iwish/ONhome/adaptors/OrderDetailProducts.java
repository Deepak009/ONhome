package tech.iwish.ONhome.adaptors;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import tech.iwish.ONhome.CartActivity;
import tech.iwish.ONhome.OrderDetail;
import tech.iwish.ONhome.R;
import tech.iwish.ONhome.cart.ShopingCart;
import tech.iwish.ONhome.helper.Constants;

public class OrderDetailProducts extends RecyclerView.Adapter<OrderDetailProducts.MyView> {
Activity activity;


    public class MyView extends RecyclerView.ViewHolder {
        public TextView product_name;
        public TextView TotalPrice;
        public TextView qty;
        public TextView description;
        public TextView off;
        public ImageView item_img_view;

        public ImageButton delete;
        public TextView quantity;


        public MyView(View view) {
            super(view);
                product_name = view.findViewById(R.id.simpleTextView);
                TotalPrice = view.findViewById(R.id.totalprice);
                qty = view.findViewById(R.id.quantity);



        }
    }

    public OrderDetailProducts(OrderDetail activity) {
        this.activity = activity;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_summary_single, parent, false);


        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

        holder.product_name.setText(ShopingCart.NAME.get(position));
        holder.qty.setText("Qty."+ShopingCart.QTY.get(position));
        int quantity = ShopingCart.QTY.get(position);
        int TPrice = ShopingCart.price_a.get(position);
        int TotalPrice = quantity*TPrice;
        holder.TotalPrice.setText("Rs."+String.valueOf(TotalPrice));

    }

    @Override
    public int getItemCount() {
        int count = ShopingCart.NAME.size();
        return count;
    }
}

