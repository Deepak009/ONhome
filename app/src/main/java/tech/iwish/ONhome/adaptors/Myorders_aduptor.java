package tech.iwish.ONhome.adaptors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tech.iwish.ONhome.ItemDescription;
import tech.iwish.ONhome.R;

import static tech.iwish.ONhome.helper.Constants.BaseUrl;

public class Myorders_aduptor extends RecyclerView.Adapter<Myorders_aduptor.MyView> {

    private ArrayList<String> sno;
    private ArrayList<String> customer_sno;
    private ArrayList<String> name;
    private ArrayList<String> email;
    private ArrayList<String> mobile;
    private ArrayList<String> shipping_add;
    private ArrayList<String> city;
    private ArrayList<String> pincode;
    private ArrayList<String> product;
    private ArrayList<String> p_rate;
    private ArrayList<String> p_weight;
    private ArrayList<String> quantity;
    private ArrayList<String> amount;
    private ArrayList<String> order_date;
    private ArrayList<String> status;
    private ArrayList<String> payment_status;
    private ArrayList<String> payment_method;
    Context context;

    public class MyView extends RecyclerView.ViewHolder {
        public TextView order_number;
        public TextView total_no_products;
        public TextView TotalAmmount;
        public TextView Tax;
        public TextView GrandTotal;
        public TextView PayableAmmount;
        public TextView PaymentStatus;
        public TextView Order_type;
        public TextView Order_date;
        public TextView RecievedDate;
        public TextView RecievedBy;
        public TextView DeliveryAddress;
        public TextView Delivery_Status;

        public MyView(View view) {
            super(view);

            order_number = view.findViewById(R.id.textView51);
            total_no_products= view.findViewById(R.id.textView38);
            TotalAmmount= view.findViewById(R.id.textView41);
            Tax= view.findViewById(R.id.textView43);
            GrandTotal= view.findViewById(R.id.textView45);
            PayableAmmount= view.findViewById(R.id.textView47);
            PaymentStatus= view.findViewById(R.id.textView53);
            Order_type= view.findViewById(R.id.textView55);
            Order_date= view.findViewById(R.id.textView57);
            RecievedDate= view.findViewById(R.id.textView59);
            RecievedBy= view.findViewById(R.id.textView61);
            DeliveryAddress= view.findViewById(R.id.textView64);
            Delivery_Status= view.findViewById(R.id.textView66);

        }
    }

    public Myorders_aduptor(Context applicationContext, ArrayList<String> sno, ArrayList<String> customer_sno, ArrayList<String> name, ArrayList<String> email, ArrayList<String> mobile, ArrayList<String> shipping_add, ArrayList<String> city, ArrayList<String> pincode, ArrayList<String> product, ArrayList<String> p_rate, ArrayList<String> p_weight, ArrayList<String> quantity, ArrayList<String> amount, ArrayList<String> order_date, ArrayList<String> status, ArrayList<String> payment_status, ArrayList<String> payment_method) {
        this.context = applicationContext;
        this.sno = sno;
        this.customer_sno= customer_sno;
        this.name= name;
        this.email= email;
        this.mobile= mobile;
        this.shipping_add= shipping_add;
        this.city= city;
        this.pincode= pincode;
        this.product= product;
        this.p_rate= p_rate;
        this.p_weight= p_weight;
        this.quantity= quantity;
        this.amount= amount;
        this.order_date= order_date;
        this.status= status;
        this.payment_status= payment_status;
        this.payment_method= payment_method;


    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.myordersingle, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

        holder.order_number.setText("Order no. "+sno.get(position));
        holder.total_no_products.setText(product.get(position));
        holder.TotalAmmount.setText(amount.get(position));
        holder.Tax.setText("NA");
        holder.GrandTotal.setText(amount.get(position));
        holder.PayableAmmount.setText(amount.get(position));
        holder.PaymentStatus.setText(payment_status.get(position));
        holder.Order_type.setText(payment_method.get(position));
        holder.Order_date.setText(order_date.get(position));
        holder.RecievedDate.setText("NA");
        holder.RecievedBy.setText("NA");
        holder.DeliveryAddress.setText(shipping_add.get(position));
        holder.Delivery_Status.setText(status.get(position));



    }

    @Override
    public int getItemCount() {
        return sno.size();
    }
}

