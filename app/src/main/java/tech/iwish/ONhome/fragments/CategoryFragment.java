package tech.iwish.ONhome.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Logger;

import tech.iwish.ONhome.Connection.ConnectionServer;
import tech.iwish.ONhome.R;
import tech.iwish.ONhome.adaptors.BestSellAdaptor;
import tech.iwish.ONhome.adaptors.CartAdaptor;
import tech.iwish.ONhome.adaptors.CetegoryAdaptor;
import tech.iwish.ONhome.adaptors.Myorders_aduptor;
import tech.iwish.ONhome.adaptors.SliderAdaptor;

import static tech.iwish.ONhome.helper.Constants.GetCategories;
import static tech.iwish.ONhome.helper.Constants.getproductlist;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    ArrayList<String> cat_id;
    ArrayList<String> cat_name;
    ArrayList<String> imgs;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview =  inflater.inflate(R.layout.fragment_category, container, false);

        GetCetegories(rootview);
        // get the reference of RecyclerView
        RecyclerView recyclerView = rootview.findViewById(R.id.recyclerView);
        // set a GridLayoutManager with 3 number of columns , horizontal gravity and false value for reverseLayout to show the items from start to end
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        //recyclerView.setAdapter(new CetegoryAdaptor(getActivity(),cat_id,cat_name,imgs));
        //recyclerView.setAdapter(new SliderAdaptor(getActivity()));
        return rootview;
    }
    private void GetCetegories(View rootview) {
        //Array List for single Item Detial Start


        //Array List for single Item Detial End
        cat_id = new ArrayList<>();
        cat_name = new ArrayList<>();
        imgs = new ArrayList<>();

        ConnectionServer connectionServer = new ConnectionServer();
        connectionServer.set_url(GetCategories);
        connectionServer.requestedMethod("POST");
        //connectionServer.buildParameter("username",username);
        //connectionServer.buildParameter("password",password);
        connectionServer.execute(new ConnectionServer.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                Log.e("Serverr00",output);
                try {
                    JSONArray products = new JSONArray(output);
                    Log.e("Lenth", String.valueOf(products.length()));
                    for(int i=0; i<products.length(); i++){

                        JSONObject productObject = products.getJSONObject(i);

                        cat_id.add(productObject.getString("id"));
                        cat_name.add(productObject.getString("name"));
                        imgs.add(productObject.getString("imgs"));
                        Log.e("String","1");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }


}
