package tech.iwish.ONhome.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.iwish.ONhome.R;
import tech.iwish.ONhome.adaptors.BestSellAdaptor;
import tech.iwish.ONhome.adaptors.CartAdaptor;
import tech.iwish.ONhome.adaptors.CetegoryAdaptor;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview =  inflater.inflate(R.layout.fragment_category, container, false);

        // get the reference of RecyclerView
        RecyclerView recyclerView = rootview.findViewById(R.id.recyclerView);
        // set a GridLayoutManager with 3 number of columns , horizontal gravity and false value for reverseLayout to show the items from start to end
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setAdapter(new CetegoryAdaptor(getActivity()));



        return rootview;
    }

}
