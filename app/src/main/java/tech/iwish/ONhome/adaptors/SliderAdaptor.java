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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tech.iwish.ONhome.R;

import static tech.iwish.ONhome.helper.Constants.BaseUrl;

public class SliderAdaptor extends RecyclerView.Adapter<SliderAdaptor.MyView> {

    ArrayList<String> Slider_images;
    ArrayList<String> Slider_image_id;

    Activity activity;

    public SliderAdaptor(FragmentActivity activity, ArrayList<String> slider_images, ArrayList<String> slider_image_id) {
        this.activity = activity;
        this.Slider_images = slider_images;
        this.Slider_image_id = slider_image_id;

    }


    public class MyView extends RecyclerView.ViewHolder {

        ImageView imageView;


        public MyView(View view) {
            super(view);

            imageView = view.findViewById(R.id.imageView11);


        }
    }


    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_single_item, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

        Log.e("URSLss",BaseUrl+Slider_images.get(position));

        Picasso.with(activity).load(BaseUrl+Slider_images.get(position)).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return Slider_images.size();
    }
}

