package tech.iwish.ONhome.adaptors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tech.iwish.ONhome.R;

public class CetegoryAdaptor extends RecyclerView.Adapter<CetegoryAdaptor.MyView> {

    private ArrayList<String> sno;

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


        }
    }

    public CetegoryAdaptor() {
        this.context = applicationContext;
        this.sno = sno;



    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.myordersingle, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

        holder.order_number.setText("Order no. "+sno.get(position));



    }

    @Override
    public int getItemCount() {
        return sno.size();
    }
}

