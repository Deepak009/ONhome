package tech.iwish.ONhome.adaptors;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tech.iwish.ONhome.R;

import static tech.iwish.ONhome.helper.Constants.BaseUrl;

public class CetegoryAdaptor extends RecyclerView.Adapter<CetegoryAdaptor.MyView> {

    ArrayList<String> cat_id;
    ArrayList<String> cat_name;
    ArrayList<String> imgs;

    Activity activity;

    public CetegoryAdaptor(FragmentActivity activity, ArrayList<String> cat_id, ArrayList<String> cat_name, ArrayList<String> imgs) {
        this.activity = activity;
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.imgs = imgs;
    }

    public class MyView extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView category_name;

        public MyView(View view) {
            super(view);

            imageView = view.findViewById(R.id.imageView9);
            category_name = view.findViewById(R.id.textView80);


        }
    }



    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_single_item, parent, false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
        Log.e("flag","55");
        holder.category_name.setText(cat_name.get(position));
        Picasso.with(activity).load(BaseUrl+imgs.get(position)).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return cat_id.size();
    }
}

