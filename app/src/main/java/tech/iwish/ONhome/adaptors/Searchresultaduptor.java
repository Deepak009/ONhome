package tech.iwish.ONhome.adaptors;

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
import tech.iwish.ONhome.ProductSearchActivity;
import tech.iwish.ONhome.R;

import static tech.iwish.ONhome.helper.Constants.BaseUrl;

public class Searchresultaduptor extends RecyclerView.Adapter<Searchresultaduptor.MyView> {
    private ArrayList<String> item_id;
    private ArrayList<String> price;
    private ArrayList<String> save_price;
    private ArrayList<String> description;
    private ArrayList<String> off_price;
    private ArrayList<String> img_urls;
    private ArrayList<String> product_name;

    Context context;

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

    public Searchresultaduptor(ProductSearchActivity productSearchActivity,
                               ArrayList<String> itemid_a,
                               ArrayList<String> price_a,
                               ArrayList<String> save_price_a,
                               ArrayList<String> description_a,
                               ArrayList<String> off_price_a,
                               ArrayList<String> img_url_a,
                               ArrayList<String> QTY,
                               ArrayList<String> NAME) {

        this.context = productSearchActivity;
        this.item_id = itemid_a;
        this.price = price_a;
        this.save_price = save_price_a;
        this.description = description_a;
        this.off_price = off_price_a;
        this.img_urls = img_url_a;
        this.product_name = NAME;


    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleitem, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {


        holder.item_id.setText(item_id.get(position));
        holder.price.setText(price.get(position));
        holder.save_price.setText(save_price.get(position));
        holder.description.setText(description.get(position));
        holder.Off_price.setText(off_price.get(position));
        holder.item_name.setText(product_name.get(position));

        Log.e("URLSS",BaseUrl+ProductSearchActivity.img_url_a.get(position));

        Picasso.with(context)
                .load(BaseUrl+img_urls.get(position))
                .into(holder.item_img_view);

        holder.item_img_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ItemDescription.class);
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
                Intent intent = new Intent(context,ItemDescription.class);

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




    }

    @Override
    public int getItemCount() {
        return item_id.size();
    }
}

