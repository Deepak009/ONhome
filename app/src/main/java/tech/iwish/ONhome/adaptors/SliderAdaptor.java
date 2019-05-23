package tech.iwish.ONhome.adaptors;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.iwish.ONhome.R;

public class SliderAdaptor extends RecyclerView.Adapter<SliderAdaptor.MyView> {



    Context context;

    public class MyView extends RecyclerView.ViewHolder {


        public MyView(View view) {
            super(view);



        }
    }

    public SliderAdaptor(FragmentActivity activity) {



    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_single_item, parent, false);

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

