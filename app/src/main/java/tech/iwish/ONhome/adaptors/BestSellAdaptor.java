package tech.iwish.ONhome.adaptors;

import android.app.Activity;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import tech.iwish.ONhome.ItemDescription;
import tech.iwish.ONhome.R;

import static tech.iwish.ONhome.helper.Constants.BaseUrl;

public class BestSellAdaptor extends RecyclerView.Adapter<BestSellAdaptor.MyView> {

    private ArrayList<String> item_id;
    private ArrayList<String> price;
    private ArrayList<String> save_price;
    private ArrayList<String> description;
    private ArrayList<String> off_price;
    private ArrayList<String> img_urls;
    private ArrayList<String> product_name;
    Activity activity;

    public class MyView extends RecyclerView.ViewHolder {
        public TextView item_name;
        public TextView item_id;
        public TextView price;
        public TextView save_price;
        public TextView description;
        public TextView Off_price;
        public ImageView item_img_view;
        public Button addtocart;

        public MyView(View view) {
            super(view);

            price = (TextView) view.findViewById(R.id.textView3);
            save_price = (TextView) view.findViewById(R.id.textView5);
            description = (TextView) view.findViewById(R.id.textView6);
            Off_price = (TextView) view.findViewById(R.id.textView4);
            item_id = (TextView) view.findViewById(R.id.item_id);
            item_name = (TextView) view.findViewById(R.id.textView14);
            item_img_view = (ImageView) view.findViewById(R.id.imageView3);
            addtocart = (Button) view.findViewById(R.id.button);

        }
    }

    public BestSellAdaptor(FragmentActivity activity,
                           ArrayList<String> item_id,
                           ArrayList<String> price,
                           ArrayList<String> save_price,
                           ArrayList<String> description,
                           ArrayList<String> off_price, ArrayList<String> item_img_url, ArrayList<String> item_name) {
            this.activity = activity;
        this.item_id = item_id;
        this.price = price;
        this.save_price = save_price;
        this.description = description;
        this.off_price = off_price;
        this.img_urls = item_img_url;
        this.product_name = item_name;


    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleitem, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

        // Log.e("name", String.valueOf(horizontalProductScrollModelList.get(position)));
            holder.item_id.setText(item_id.get(position));
            holder.price.setText(price.get(position));
            holder.save_price.setText(save_price.get(position));
            holder.description.setText(description.get(position));
            holder.Off_price.setText(off_price.get(position)+" % OFF");
            holder.description.setText(description.get(position));
            holder.item_name.setText(product_name.get(position));

            Log.e("URLSS",BaseUrl+img_urls.get(position));
            Picasso.with(this.activity)
                    .load(BaseUrl+img_urls.get(position))
                    .into(holder.item_img_view);

            holder.item_img_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(activity,ItemDescription.class);

                    intent.putExtra("item_id",item_id.get(position));
                    intent.putExtra("price",price.get(position));
                    intent.putExtra("save_price",save_price.get(position));
                    intent.putExtra("description",description.get(position));
                    intent.putExtra("off_price",off_price.get(position));
                    intent.putExtra("img_urls",img_urls.get(position));
                    intent.putExtra("product_name",product_name.get(position));


                    holder.item_img_view.getContext().startActivity(intent);
                    //Toast.makeText(activity, "Item"+position, Toast.LENGTH_SHORT).show();

                }
            });
            holder.addtocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity,ItemDescription.class);

                    intent.putExtra("item_id",item_id.get(position));
                    intent.putExtra("price",price.get(position));
                    intent.putExtra("save_price",save_price.get(position));
                    intent.putExtra("description",description.get(position));
                    intent.putExtra("off_price",off_price.get(position));
                    intent.putExtra("img_urls",img_urls.get(position));
                    intent.putExtra("product_name",product_name.get(position));

                    holder.item_img_view.getContext().startActivity(intent);
                    //Toast.makeText(activity, "Item"+position, Toast.LENGTH_SHORT).show();
                }
            });

        //holder.item_img.setImageResource(R.drawable.slide4);
        //Log.e("TestURL", Constants.BaseUrl + category_url.get(position));
        /*Picasso.with(activity).load(SyncStateContract.Constants.BaseUrl + category_url.get(position))
                .into(holder.imageView);*/
    }

    @Override
    public int getItemCount() {
        return item_id.size();
    }
}

