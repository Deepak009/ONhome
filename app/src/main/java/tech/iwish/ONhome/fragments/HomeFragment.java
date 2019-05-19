package tech.iwish.ONhome.fragments;


import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import tech.iwish.ONhome.Connection.ConnectionServer;
import tech.iwish.ONhome.HomeActivity;
import tech.iwish.ONhome.R;
import tech.iwish.ONhome.adaptors.BestSellAdaptor;

import static tech.iwish.ONhome.helper.Constants.Check;
import static tech.iwish.ONhome.helper.Constants.GET_Grocery_itmes;
import static tech.iwish.ONhome.helper.Constants.GetBeverages_url;
import static tech.iwish.ONhome.helper.Constants.GetBiscuitsnecks;
import static tech.iwish.ONhome.helper.Constants.getproductlist;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    int[] sampleImages = {R.drawable.slide4, R.drawable.slide4, R.drawable.slide4, R.drawable.slide4, R.drawable.slide4};
    CarouselView carouselView;

    LinearLayoutManager HorizontalLayout ;



    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    RecyclerView BestSelling_Recycler;
    RecyclerView Grocery_Recycler;
    RecyclerView Beverages_Recycler;
    RecyclerView SnackBiscuits_Recycler;
    RecyclerView PersonalCare_Recycler;
    RecyclerView Soap_Recycler;
    RecyclerView Patanjali_Products_Recycler;
    RecyclerView Foods_Breakfast_Recycler;





    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);
        carouselView = (CarouselView) rootview.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);


        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(), "Clicked item: "+ position, Toast.LENGTH_SHORT).show();
            }
        });


        Getitemdetail(rootview);
        GetGroceryItem(rootview);
        GetBeverages(rootview);
        GetSnackBiscuits(rootview);

        return rootview;
    }

    private void Getitemdetail(View rootview) {
        //Array List for single Item Detial Start
        final ArrayList<String> item_name;
        final ArrayList<String> item_id;
        final ArrayList<String> price;
        final ArrayList<String> save_price;
        final ArrayList<String> description;
        final ArrayList<String> Off_price;
        final ArrayList<String> item_img_url;

        //Array List for single Item Detial End
        item_name = new ArrayList<>();
        item_id = new ArrayList<>();
        price = new ArrayList<>();
        save_price = new ArrayList<>();
        description = new ArrayList<>();
        Off_price = new ArrayList<>();
        item_img_url = new ArrayList<>();

        ConnectionServer connectionServer = new ConnectionServer();
        connectionServer.set_url(getproductlist);
        connectionServer.requestedMethod("POST");
        //connectionServer.buildParameter("username",username);
        //connectionServer.buildParameter("password",password);
        connectionServer.execute(new ConnectionServer.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                Log.e("Serverr",output);
                try {
                    JSONArray products = new JSONArray(output);
                    Log.e("Lenth", String.valueOf(products.length()));
                    for(int i=0; i<products.length(); i++){

                        JSONObject productObject = products.getJSONObject(i);

                        double  dis,discount_amount,markedprice,s;
                        markedprice= Double.parseDouble(productObject.getString("price"));
                        dis=Double.parseDouble(productObject.getString("discount"));  // 25 mean 25%
                        s=100-dis;
                        discount_amount= (s*markedprice)/100;

                        price.add(productObject.getString("price"));
                        item_id.add(productObject.getString("id"));
                        item_name.add(productObject.getString("name"));
                        save_price.add(String.valueOf(discount_amount));
                        description.add(productObject.getString("description"));
                        Off_price.add(productObject.getString("discount"));
                        item_img_url.add(productObject.getString("img_url"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        RecyclerViewLayoutManager = new LinearLayoutManager(getActivity());
        HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        BestSelling_Recycler = (RecyclerView)rootview.findViewById(R.id.bestsellingitem);
        BestSelling_Recycler.setLayoutManager(HorizontalLayout);
        BestSelling_Recycler.setAdapter(new BestSellAdaptor(getActivity(),item_id,price,save_price,description,Off_price,item_img_url,item_name));

    }
    private void GetGroceryItem(View rootview) {
        //Array List for single Item Detial Start
        final ArrayList<String> item_name;
        final ArrayList<String> item_id;
        final ArrayList<String> price;
        final ArrayList<String> save_price;
        final ArrayList<String> description;
        final ArrayList<String> Off_price;
        final ArrayList<String> item_img_url;

        //Array List for single Item Detial End
        item_name = new ArrayList<>();
        item_id = new ArrayList<>();
        price = new ArrayList<>();
        save_price = new ArrayList<>();
        description = new ArrayList<>();
        Off_price = new ArrayList<>();
        item_img_url = new ArrayList<>();

        ConnectionServer connectionServer = new ConnectionServer();
        connectionServer.set_url(GET_Grocery_itmes);
        connectionServer.requestedMethod("POST");
        //connectionServer.buildParameter("username",username);
        //connectionServer.buildParameter("password",password);
        connectionServer.execute(new ConnectionServer.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                Log.e("Serverr",output);
                try {
                    JSONArray products = new JSONArray(output);
                    Log.e("Lenth", String.valueOf(products.length()));
                    for(int i=0; i<products.length(); i++){

                        JSONObject productObject = products.getJSONObject(i);

                        double  dis,discount_amount,markedprice,s;
                        markedprice= Double.parseDouble(productObject.getString("price"));
                        dis=Double.parseDouble(productObject.getString("discount"));  // 25 mean 25%
                        s=100-dis;
                        discount_amount= (s*markedprice)/100;

                        price.add(productObject.getString("price"));
                        item_id.add(productObject.getString("id"));
                        item_name.add(productObject.getString("name"));
                        save_price.add(String.valueOf(discount_amount));
                        description.add(productObject.getString("description"));
                        Off_price.add(productObject.getString("discount"));
                        item_img_url.add(productObject.getString("img_url"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        RecyclerViewLayoutManager = new LinearLayoutManager(getActivity());
        HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        BestSelling_Recycler = (RecyclerView)rootview.findViewById(R.id.grocerysellingitem);
        BestSelling_Recycler.setLayoutManager(HorizontalLayout);
        BestSelling_Recycler.setAdapter(new BestSellAdaptor(getActivity(),item_id,price,save_price,description,Off_price,item_img_url,item_name));

    }
    private void GetBeverages(View rootview) {
        //Array List for single Item Detial Start
        final ArrayList<String> item_name;
        final ArrayList<String> item_id;
        final ArrayList<String> price;
        final ArrayList<String> save_price;
        final ArrayList<String> description;
        final ArrayList<String> Off_price;
        final ArrayList<String> item_img_url;

        //Array List for single Item Detial End
        item_name = new ArrayList<>();
        item_id = new ArrayList<>();
        price = new ArrayList<>();
        save_price = new ArrayList<>();
        description = new ArrayList<>();
        Off_price = new ArrayList<>();
        item_img_url = new ArrayList<>();

        ConnectionServer connectionServer = new ConnectionServer();
        connectionServer.set_url(GetBeverages_url);
        connectionServer.requestedMethod("POST");
        //connectionServer.buildParameter("username",username);
        //connectionServer.buildParameter("password",password);
        connectionServer.execute(new ConnectionServer.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                Log.e("Serverr",output);
                try {
                    JSONArray products = new JSONArray(output);
                    Log.e("Lenth", String.valueOf(products.length()));
                    for(int i=0; i<products.length(); i++){

                        JSONObject productObject = products.getJSONObject(i);

                        double  dis,discount_amount,markedprice,s;
                        markedprice= Double.parseDouble(productObject.getString("price"));
                        dis=Double.parseDouble(productObject.getString("discount"));  // 25 mean 25%
                        s=100-dis;
                        discount_amount= (s*markedprice)/100;

                        price.add(productObject.getString("price"));
                        item_id.add(productObject.getString("id"));
                        item_name.add(productObject.getString("name"));
                        save_price.add(String.valueOf(discount_amount));
                        description.add(productObject.getString("description"));
                        Off_price.add(productObject.getString("discount"));
                        item_img_url.add(productObject.getString("img_url"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        RecyclerViewLayoutManager = new LinearLayoutManager(getActivity());
        HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        BestSelling_Recycler = (RecyclerView)rootview.findViewById(R.id.beverages_sellingitem);
        BestSelling_Recycler.setLayoutManager(HorizontalLayout);
        BestSelling_Recycler.setAdapter(new BestSellAdaptor(getActivity(),item_id,price,save_price,description,Off_price,item_img_url,item_name));

    }
    private void GetSnackBiscuits(View rootview) {
        //Array List for single Item Detial Start
        final ArrayList<String> item_name;
        final ArrayList<String> item_id;
        final ArrayList<String> price;
        final ArrayList<String> save_price;
        final ArrayList<String> description;
        final ArrayList<String> Off_price;
        final ArrayList<String> item_img_url;

        //Array List for single Item Detial End
        item_name = new ArrayList<>();
        item_id = new ArrayList<>();
        price = new ArrayList<>();
        save_price = new ArrayList<>();
        description = new ArrayList<>();
        Off_price = new ArrayList<>();
        item_img_url = new ArrayList<>();

        ConnectionServer connectionServer = new ConnectionServer();
        connectionServer.set_url(GetBiscuitsnecks);
        connectionServer.requestedMethod("POST");
        //connectionServer.buildParameter("username",username);
        //connectionServer.buildParameter("password",password);
        connectionServer.execute(new ConnectionServer.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                Log.e("Serverr",output);
                try {
                    JSONArray products = new JSONArray(output);
                    Log.e("Lenth", String.valueOf(products.length()));
                    for(int i=0; i<products.length(); i++){

                        JSONObject productObject = products.getJSONObject(i);

                        double  dis,discount_amount,markedprice,s;
                        markedprice= Double.parseDouble(productObject.getString("price"));
                        dis=Double.parseDouble(productObject.getString("discount"));  // 25 mean 25%
                        s=100-dis;
                        discount_amount= (s*markedprice)/100;

                        price.add(productObject.getString("price"));
                        item_id.add(productObject.getString("id"));
                        item_name.add(productObject.getString("name"));
                        save_price.add(String.valueOf(discount_amount));
                        description.add(productObject.getString("description"));
                        Off_price.add(productObject.getString("discount"));
                        item_img_url.add(productObject.getString("img_url"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        RecyclerViewLayoutManager = new LinearLayoutManager(getActivity());
        HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        BestSelling_Recycler = (RecyclerView)rootview.findViewById(R.id.biscuitandsnecks_sellingitem);
        BestSelling_Recycler.setLayoutManager(HorizontalLayout);
        BestSelling_Recycler.setAdapter(new BestSellAdaptor(getActivity(),item_id,price,save_price,description,Off_price,item_img_url,item_name));

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {

          /*  Picasso.with(HomeActivity.this)s
                    .load("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxQTEhUTExMWFhUXFyAbGBgYGR8eHxsgHx4fHh8fICAfISggHh4lHh0fITEhJSktLi4uHR8zODMtNygtLisBCgoKDg0OGxAQGy0lICUtLy0vLS0tLS0tLS8vLy0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLf/AABEIAMIBAwMBEQACEQEDEQH/xAAcAAACAwEBAQEAAAAAAAAAAAAFBgMEBwIBCAD/xABLEAACAQIEAwUFBQUEBwYHAAABAhEDIQAEEjEFBkETIlFhcQcygZGhFCNCscFSYnLR8CQzsuEVU3OCkqLxFiU0Q7PCNURUZIOT0v/EABsBAAIDAQEBAAAAAAAAAAAAAAMEAQIFBgAH/8QAOhEAAQQBAwIDBgUDBAICAwAAAQACAxEEEiExBUETUXEiYYGRobEUMsHR8CNC4QZSYvEVM3KiFpKy/9oADAMBAAIRAxEAPwDPOBDM0x2bUVeiQwemW06pjvEodWoECDeNtjhWTIh80drZAKARmp25omgtTsKDDSVl6r6d9AZtOlPJQJ6k4X/Fwh1gWUUxzObpJ2VPLcApUB2wqMzU++LKBIuLXPTxGKnNE58LTzso8J0P9QHjdHKHHazqt9EjZFn85OMt+NG1x2v1Wm3Mke0EqKvmq0MWqVYESZYASYHlckD44sxrNg0DdUMhJolNPBeE8QWFgBRutR7ee0kH0wo/LgYfze9Bk0UouceW6SUauZ/u2pqGIT3SWMCNhMzcR6YLjZhlcxoH5iR6V3XofadpCW+IZCtlgvbJUOoSrDSo9Ddr+WNcMxLo6ifLYKgkncdtI+agSurD3AP4if004qZoWfljHxJKv4EzuZPkArOayejs2pKAGphmJUND6mBjVMCAP6vjx6gW1pAHw+yhuHqJ1EmlWOezSsCKsHyC3+m/ni//AJCQitR+aKMCL/aFI71HUl6rN4ifr+l8JyZDnut3zTEeOyMeyAFQzQJIAYybR/LEsNCyrO81suby+YTLpRytHWy0wutnVVXRpsZMnUNQkCxF4mQr/piCKXJfmTHgmh63uuezg57Cwd1zkKdastejmoNMDQAIsWGrcCDoUpcEjUzD8OHf9TZePC2NzG2+7Hurv8V7pxlx3hwdxwlT2wqQMqVmxqC3+5/LGH/p91tkvzH6rXxt3EpGyrsRJ+BmcbT6B2T4UnaTYSIHzxWq3U2onWayCPxr63YYbwt5WA+YWd1R2nElP/E/ZbUMoHTU9aojkaYVio2EbXMRYbXMbzjYmyGNkIA7rHgwnaGgk1Q454XNXh2XEFWmqCDrPeMiBJkkbgG83A+Ihl9uyYZhDUC4bD3qty8pc1IVGmqxkrJuZ3nG1lENDdzwFwsf9Sd7GMB3P3VWtxJqbvT7EVCGY+4WuT7oEifhOMjqGHhzSNfMHEkDg1t81sdIx+oPieICxrQ4j2ubVlM7XenqqqlJSDZaXaSI7wI/Q7wcKt6f0xhDhAfmtI4PUXEtM7b/AOIv7pZ43xlC1BmIbsqwJhDS0yoswi506Wt0AGxxeeLHOM90LdO4998omNjT4+Sxszy8kHsBXHlyr3OCirlw5KkIQZ3N7QPofhjDJNWFux7FIVEi0yQDe8SMauO3UwWu8j3hbXND7LSqVKmmh6uXdqdRlKnSRoU91FK9pErq6BpxpOjicQQD8aXBhmY4vb4zARq45Px+H0XmRWp3afZL2iaSalMqASsjUxYrJqT3gAbAAX2LL4LHnwhsfpf7JHGjnkhEmXJpojbkmvSxv2tLXKVJqHEUpuRMlDBtdTH1jHKdfxnfh3kdqK7jqTmz9PL2+4/VMfE6iPls4F60cwVOnSpAGgxckwerESZIAGMoskb+Hc4H8ze9lc3NG9gF/wDG9+L3H8HxWF5ZSSZOOqeaCCLJpW0qgAYEQVGy02lyhUekKyvqkTpUAGBuQGIY+PujpbCMuNJEOLSD8wgWG2hjrllpmv2lcU6ZC1A4AkkgDRpmQBJPp0w1iQY8jf6nPakq3Pkm3jHHNoRxGg9SjT01ARXzJoqFF9HVhNziNEcUzjHw0Ej17IzMh74C6XzWn5Lh6UqumiirCiG7u5sAQAHiBExBg3JxjP6bmTfncN/emWZLA2i1JntC4nX+1UcsUTT21NmVQe+F7yre8TNh1wfBwmQskJNkAj0J5KUbPI+Qithx701jMZnUkuEkk6QoiTMj4TN5wl+DwvD1Ofe2/cpV/UJg2jTex2spf9o2oZYsapfUQCCwsZH4YE2tYYawTC6YNiugO4TvRZC+Z7y6wG/DdWuE8x1VTssxTXMUgoADDveG5F/j88S54tackNmxsuuJcmJmE7fIOdzNFj7pj3VO4PkbecYMyUOQxM5h0vSagenVKVBURlIUhuhjw6bj13wzksOloPkr4sjSXEeaIHIgmCYnY9P8sZ3jEbhPbL9VyrDe8bEY82Rp4Vt1xy/w4VM5RUmfvAdt9PeP5YvkzFmO4jyS8xppK3BamkQNzjKj6g3Fw/CgJEljcbgjlY5aXOs8KJtRub4x5fxGQ4yPDifMogobJC9rCfdUD++w+YH/APONjoRNPHoU3iH2ikVMpKggR6RjaMlOo7rTG4U+ZzqGglLs0Uq0moBDk+BPUX22sLYs0OsoQa3VqtQ8JpmrmaSKupiwgbTHe622GNDpzanZfANrL6268OQDuK+ey1fIcDKr/aSnS+tWXVckjUotPQz6CMaDI9z7NpeTLb4YAdVAKjxThq6WNFV+72RQravFY31Gdyb4l0RJ2FfBEg6hAxpDpAeb93yVjlLM1KaH7ojvGbG9zth3qGfg+J4b5mhw5F8bLhMQZUb/ABYoyQ7vXZWKPDzmalSoVCHWekkSBIvMAi8iCZ3i2F8nQ5rDG4OFbEeq6PoWW4Nm8RtW/j4BXavAqrWauSDMjSgMdAO7MAfU/DCtP81rtkhbuGpD9qPCVopSI95qkbAWCmBbpc/M4u9hbivJ8whuk8TLjd7nfop87VpjI3AA7FT5A2vb1xg1ewWiOUE4Nkaa5U1cwj/egGiQOn7WokADpffG5hMLWbrp25D5DGyBwpo9r9q5TKvP6qiU+zappA1M5ubX22v8NvXB/ZspA/6eL3OcXAX2CEVuL161QuqOs7IsAAeHui0HeCcevfYJuPpmJBHpOk+8i/1/ZR5zi7CtTalRWm6kQJJO4gEkwZ8Y8cVyCDG5ruCN0xFht8FzZH2CPh8lomf4UhpMCCJpVFImRDglum89fIY+ev6g95ijfVBwII8r22XHGZxJF819OF8z5ei7Dugnxx3DnNB3VJJhHyVbp5WtFkYjyWfrgZczzCVPUIQaLl9HZbjdOT2WWzbv4mkw/wDUIgYoZ2vBb3+CV8UHi/klsCllsmz1yA2plqU4VlLAwAf2hYCBhOToczW+J4lUL27fVBizGQt0gb3us65HrLVzFN2q2p62A2WnLAK0EW944jqOuKE6Bua9T3/RPPdHGyMO7u3W3Uc89OmWamsgXKwpI8fDr5DGXF1yZzaLbHGpNPxmF1RlZdVzDNxZa2lXNRiED30adFxexJbfe1tyC3HvhmPuTufOyUnj7Mc9w3v9aTzxThVVMu8OobVCWmRNlvJBj12xmDHeHBxaCCa280fMw4s1/wDT9lxFkn+d0gc+JGWogliWrDXq3Fto2Hp5jGlgMezIcHtqhshdFxpIY5A/zC7+0AjwwvoNrcsL3K8SqUiGpVCpG0Gx8v8Ari4bvao8BwooxwXmahXFWhn6d6lXXriwJRF9UPd32xp5hcGx9/ZCzIWnU4t81X4/wN6BHYaqyMCQIuoEXtuLi4wgGNdvafZkHgoAvEzsUII38ceOOPNHbMSocvmiKyMG0d4d6Yi++C6PYIVXuJu1vPD6tN4dHDg7FSCD8cJdJhhjmDZXW/sOwtZEuquFPnGAUyIETMeHpjT6uLx3R6dINe125VIgS4Us39ooLZKm+qf7QYbyPaRv5RjE6azTkPA4AFLVaNMpFVsPnQtZ9kEefe7vhjYlLPLdONBq15m6MGZ9TuR6YmN97KrhW6v8l5lF4pl+xZ2VmKnWoG6MDYEiMaeG069/I/ZYfWJCzFJH+5o+bgFtNLJOxbUqWjRAIBsJO562HW3njKZm9RnZ/TJB3vYefvCSf0/EJGpt0phSqggDSFB8SZ+ojEX1UkAk/wD1/ZEEGOG7D7oLwd0al3tcljJDkTc3/wAsbHUP9PNlyHSl5t1bUNtvRc/0/rYZE2Mt472u8mjsrHLGFFT8ZsbKN7kmRIxosx240TIX70Ex0/LOQZnRireDZ9AKUXF2ahRbM18yyShJCIJFwRALAsVkCMVdkRxkDR3WsceV1gvND5Jb9qWT0Zag5I71YkROxQwLiZud/jfEzO8TGkHlX3VIToyo2dva/RA+B5ntsrUo1GUD3Ax/eBj5HHONFOW+7ZWuU3y1Ogy5l1NRG0jUmvuraBYiLeUYZqV0jSASwg3RIorXyIs2SOP8LYGnsQN0W4Xl8lma1QJQkLpli3ZIVjvPAB0d4RAgd71w909sjYT4+xs132vYfJBzsjqeDDHRsk0e5H7o43CsoVqKKR1R72pGdSO7IJNgDEabE7+GGi4XyFkjqecHB7roduAe6R8nwzs+JJQYltNYXPWO8J88ZXWXeFiSOb5LspMrxenGZoq2/daIGYtWnVF9MkQRDbR02sbjrjlMsMOPG9o/2/ouOcGhopfPHLxDuaRN2U6f4o/lOOunbsHfylndTNMDh2O/op8lxeuiBO53ZHeidz448+FhNoP4WF/tVytz5w4GOw7RFFGoHF6dpnbY2Mxf1wlnwRwtErRVJbJhttt2KUed+D1tBenUU0+yWk6aO8zAkq2ojvNJa4i1tsVxusiX+lvv8qCYmxWtjbtuPuveS+VaNFRRqVO+1MNp7OXltRMBQS0BVsbC+HCDku3bYHftaJKxm2taBlqqqq0koVWgQO5pEf75Bj4QMZ0fS55oiJqFk7X+26uZgzZlpTXlx14qtXMHuHU6X6lgUVZuSI2HhgUsT8eHwjwKsjyrn5+arjhznS6+9UE55p6ezrKyCNULcGep3kT8MZEOW2GcPY0kDsfP30Kv3p0RucOd0me0/hiPlO0TQAlRSEBH4nALefTbGriSzS5BmdVEVXl5VfKZxRQMdb+aTMoRG9/THng2jNXNRwvQT5YkAuXuy5oP3mIG8T8hh7qAAc0f8QksKyHepRXIcx16BUUysKICMo2JkidxMDbwwk0bJl8TSUSrHK51idTZeudw11JgbG36emI49FQam+iU+PcIr0HhxKk91xsfjhqJ7SFfWStW5KZ14bRNOkajaTChgu9QiZYgWBJjrGBYMLJeoe1/bvf2Wfl37QHdS5BM3TrUUruCGWoWUMWBVAgB2AEsyiPCcdP1WfFbhyvLAQB3Hn2WRjY0rHNt5KC+1igF4cmkaQMwDA8xUt6Sccf0p7ZWmSqJu/TtS34nuMu57LKsjnIEY0pIrNrQa/2aV/L00fvPqKx+FoP1BH0wEuLDQUVak5IoRxLLGbCoY9NLY1cJ9yge4/YrD65tiE/8mf8A9BbZUzZOYZzmtFBQqrTAAkg97USpkG0EEbHwOGRC+xp48lQSMaDdX71ZynF6dSoQFJIJAbSYgTuTHUEfLxwYwuAsoLns4ab2SrwjhdCoo1KzMX7x1mw1TAA2kW38+mNbJfI11g9vJcj0/Kj0tY6O/faYOG1VUuASB2phVtMAfTyxg9TzTC9jNJcS29vVbHRGNcJi3Zus18gjDqrRqQGLgssx13IwoZpy32Wb+9bg27pB9rDl8rTLAgrUBIN9OpGEfn8sV6c/JIyWz8UK8vggvoZMJHvH2We8v1R7hnvMNukTf5wMJu/MuhDQQq+TQ95YMhmG19z0xuYQD2FoXX9OdeKwn+brROFZCkMuAWdaLIpdlKHU8d7WDLjQZAAUgRO+GZ4A7a/Pb+bLlsvMyzmuaIrIPsm9q92/fv8AJXVpJQFQDUrARSqmSrj3qUaQzGDdiOgA6iJGNEwNfdg8jv7/APCxm5mdmukha2i0ntQ39dktHWvE1qVEZddfUNSkd1mgb+RjCHWsfxcGTT/t+y7YFj+mljSDTa2PcBaBbtKizcxvp/ZJgAd6Ik965Oo7Y4x5aemtPcEcD9VyZB0g1tv5r5qyZ0VFfqrA/I4655Lm0h5LGvY4FNPEuD03qs2nc+JGEY8hwaAuZhzdDA09lr/M2SrigxGZaq4dAtPSoGosANvWcDaXyODXyaqcAW1/2ug8OJ4I0ngnn/C441WarVq0WqUg9IggOAQNaLpMBO8ZLxf4YfyIA8gN2UY0IcQ5wJCV0UZTilN1Jcmg+tjqknuzEm029BFsK5BMGPJ4Z32F/FG6o9scMbqqncLS8jn2ZdQAdfwnqwi1/HfpjMx+s5W4dHYHPY/5QHRxuALTykTm+r23EuH6p7Msx0GwkBt/lGCOyvGxZZj/ANDZChlfFklvGxTfT0GGamhJkFoEKRuJ+nzxjOnOs1ENBNWb/erTZkc1u7/qgntEoAcPqPbT2lKPTtEFv62nG4zEHsSsqgCNuOD9VbDkJk55BWf0HXTYjCzg691ojYKpVzKA32wUMcRsoAvZVeI5+WJkk29bgH9ZxpZEZMlHyH2SWMdLSPeVQXjInvLPmMCOL/tKa1HyUn+lgbj5HFPw9cqQ154afkrWU5gqqpSC9M7obj/I+YxJgHnSNFhyyHdpHwW2cjaRkqBAKgpaTtJJjAMN0cUz3y7cC+2/uWPlxlkjmeRRisrm3Tw/LGb1JvUci2O3aN6Aq/L1KGzQEn+1XLseGtG4qIQPjH64c6TA+CEeLtzt3CcwonT5Ghg3r9FiYylQ7x88aviM7Lfb0TJrevmrGXy9WQAQfACT6WxQuYeAmI+jyMOqRwoI/wAmcNdeJZYv+0TAET3GuI8/yw3iO0ygVWx+yx+v9Nb+DdNYIDm2B56h/wBrb/8AR9NkAYAzebGes3EdfDD/AIrrsFc26JlkOF+qnqU6YWYWFE+XjPrIF/LFQ42quGkEjyKBcqBmpRaASNthJ640c6g+1y3R9crdNbDnb9VBTz9SmzqgITUZYKpv4CSCTA2APTAZxGC1z22a/gTXR8bJmbIIJAxoceRZJ/6RDK1azDU7uF6QBPxG4+h3EYF4rOzFpDpuXel+R8gB/Pglfn3OCrw+udTwsH7xIIMjTGoC7eIFh1xUSa430K2Rm4joMiPW4u9a29KWR8H4jobxDAqfQ/rjGljsLoxKAE5cr0KdRzUzD6F73e81CxbrvsJ28savTrbES3zXQNnkbhMbCLPl7rN/ZaDkuZ8uMsFINQhYVEptfYbxpm+84YdE/VaxJumZDpy4bC+SRt+v0QviXMoVg2Xo6QFjW4a8RAlfU7nqPHEiLbcpiDpGoFszz6Aj57qoeJ9sQzo/aT3HlFAEyFM3PqYuTvizmAsLTwU23EMLdDHDT3G5PqmvJZIue1GkB6i1AI7w7gQg7g2kSPyxyGRH/QdjNb/cK9+4Kw5ZdHsG9gW+7m7XzNmGCV3UqGioRF+jEdMbeg6fglZ5LYU958hajA/11xihruy4rS87hbNxlbBlR7OrsQQtl6lmMWsb+GGoorytbWkA82KXZh1Rmz2UAph1zEyHaohYraBpQqFbrCgXtv0xpZLWuGlt9t0tC5w3chTcKQ8SRGEomTOrWZJLVJM+JJMk+mIjga6N0ZFj2fpavlhjoGPf/uPpwE0ZVqY7iKQB+yjAAwOsRMRiv4V4JBoNPHCXZPHVM+xSDz9TQcRyCiAH1qQQZEyJiNpax8QcZ+Rhsx8ZzW8Xf1H7Kf8A3SEk/wBpCdnz1LSQGMXG0aRJmQbz8MLy9TxWARsbqrjbb6o0rDF7L9v2Sxz3xalVyDUaVy7U1RpESKikav2bjF4+pid3hBhb60mekuZJkADgC/gs8Tl+rY9pT2k9/b5C/wAMG8L0XZNfhDmNx+CiPCyKgpMytKz3WsOlz0/r1xXQQ6k4w4/hGVjK3rcfov1PJUmrVVdggXbbpC7mxgXtcxbDeVRnIJrYfYJbEAZitfHGHEud295/6VleD5bf7WoBP+rvExe/z/o4BoZ/vTAysgDbH39R+y8o5TJgsHrufd0sqGL+8IibdPQ4gCO9yrvmzqBZEO9gn5KRKGSsBUqlj1Ai8WgR4+vT1x6ovMqjpOoHfQ0D1Wr8m02+x5YAmOyU3vuJxjtGZLlmKM00b8Li+pOacmQn/cUUejV7T+8bR4DSI/5ZPzGNyJs2vRJdefCT1x6OBaCc5VAuTq61FXSFMHqQ4E/O/wAMYjMpzM8xHdtd/ctDprC7IaGu03e/lssw/wC0FLc5SjvuYvBE7iD/AJ/A7XiX/YurOKWijkkfH/KpV+MUnqo2mlTCiCtNwJFzPrBjzjFXanEHTSNG+KOJ0fjBxPmbRjlniFPMZ8fZyA/Z1CI8dBGwYgC/7XjcdGoGHxb00KKwOr5cf/jzEJQXFzeN+9/Fa3w+mEpqGEEDvEbDyB3Iv/nOGgPcuYllDjZK8zGapsAYJi4OibgxFxIa/wBbYuGm0PxWAH2h8/2VXg3aIGUU7FmIk3Ikwb3wafJxpH6RINQ2I9653AblwBwbF7J3vhQ8KyQqmqxABWoQCQCwPUT4bbQd74tljdp9yc6C50TJWkf38fAIrVybke//AMosIiAYnz+GFN1tAtB4SX7UuGBeHOoZoCmBb8Cl+gG5W/r6Yag/K/0S2U8unjd7z9l8/wBPMxF8IFi0tWy2fkDJNncgqJWCCmzCoum7ayYJP8NptthnGeI2UtKDLbisa97S4njfijwr/GuU61KkrJVZ47rR3Qi7z6dPrhljw40tPD6vFNIQ5tdx3soRw3JL9pbLVqlUWiKZY94XiBOqwGLPb3Ceyshzcb8RE0H1rj6UmDJcnUoPaCuT/sif54q+v7a+a593+oZ3cMAVjIcuUKFUVaa5lGDgByEVe9bTB78GfD44DI0aDxx2UO6tNlNMT2tqj9PivnLMOHqOZ95yRbeWwAWG7rEeRSaucKxGbqgEwNO38C4ThHsBZOMAYga/lr6O4ZWepRpmooLERUiAuoGGtfqMODhaZ5S9xDmBErVxFYd5dLLSLKTpC2Y93dWHmBgOUNFajXomcKMTHYih5mlX4fX7bOsVNRKgygClx+05GoRf8J+sYq+XRAXNBO7R87V80BhYyxuXHb4JrOqBN7wQJtJ638DjFkHU6YSQBe+2/wCqDcd7JV5x4aGzfDVgHTUctHgYJ+E42Mto/DaX9yB+6TdZk2Hb9Qmirk1po5pp3iZ9fP0xjS9NjER8AHVYG/lf2Wh4viOAlOwSN7W17Hh2upGo1k7qix3+vW+DwdKbDKHarcb9EXp8wx5nygWFk2T4jTqsFR21HZeyJJ9IaMNuxntG4+v+Fvj/AFNIf7R9f3XbcSFM6prCNmVF+hFQ3x5kNkUR8z+yseuTva7UzavKx91+4pxamtV1Irlp/CEg2HiCcP5mMDMTt2+yysPreY2ENbxv296gzudPZ0jTSvLISfd/bYXhfAfLCwgjHJH8+KP/AOU6i87X8v8ACppVrmSUrQBJuNpA/Z8SMWEUZ2bSrJ1DPYLlLgFJTaqVD9nW7O3fJcLf96y4h0VDt8ggw5s8sgaZDV+a+jOB1mThuXKI7McsgGgSQTTsfSYE+fhJwTCib4m57fqsrqJL3yaDyTSscIz9bVpzCsrNqKg6bBAmo23GpoE+WH8zwY4zI0+yOee919ln4rZhtKbKW/atmex4c6q2mWRVJ831H6KccdGwSdQa0DZrL/8A2WzD7brJAWMZ2uhy9HWyAnWQwWZ7wG0eWN0Mc0+yD/PimfDjcDqkH1/ZWMrwVPsDZztSSMx2ICr3YKTJtM6iBvGKGRzpxDXIv6r0ToYH69dgA8BdZXlHPUqX2unVakoYqGRyrRtqs06SRETPlF8bTMeP8ScfX7VeX0WDJkAwiYtBGy1FeHPVAYGsx0jbVEkD4TjWbMyNtOr9VwUePlSE6I7bZ3r3p9o5SkdYgNJhh0t0/X445ePHewuc9xNkkbnYFd5H4JGloG2x2Cs06KpJAjBGQta6wN/53RtYA54S5wOrU0VTTGs/aakqPDUdyY8IjzxoS6Tp1HssvB1DxdPOpp/+oRV6ldlJGlLHcmfI2B6dP+uFx4YPmtEiQ+5LXPmVqVKC0zftXKd0g+/QZWiegMn8umHcRrJNY/4/qksl5hLD7z9l8z6D1IHrjOOxW0G2BuFrfsd43USkaCxD1TLRMdwEAedjvhjHiDwSey1sfEjmxTI83pPAK0Xta1alrdGKKdSQAC0AiQJB/ngoa2N9DlUqDHm8NrgHHbmwEv5Cn/3mkkoxjSWA1AkEWixO/vYJP+Va+Sb6a4N38647LSKGXZd6rt6hP0UbYQc4HgLiwl/nEdllmqtUqMFdXIOgAaDr6KLd3cnAsiQ+EQ0Czt377JnFeY3OcN/Zd9RX6r5345wA0exzC/3VQgkfsSRE+R6f5iRQy2Cx3I+qzYZ9YLHchRc35sPm6rKZB03H8C49jNPhC1GIwiFoP83X1Fk8rSNMPRECqNYLEtJYA6iGO+2GdRTRFqHKuNeYSJiqIvFjTSdttz88CkGvZys0aeEHzGaCcWc6CT9jUSD17R4Hh1jAsvIbjY7pHCxbR9/siSNBEV9y79E0ZdlcSGPpO2/9fDFYM2GbZjhfl3+Sq9jmmilXjucP+lMqiKC1GhVqSTbS2lTMXBEbR4fAOfJII26Bft7d7IHdWxjE57w48Dt7ymQtmDFqQHW7fywo78e9gPsj0Rf6A8/ok72pqamQVXCk9uBMbGGvEm312w5EJrHi1dE2NivMlZG8FoNWNvdaU+TeF06NTtC2ltBVWC7M1ogAk2mfLC0MsgeXv2bvyVv9TyoZIhHC0X7gu+E8Yvmlp0suwo92khGliytpJc+BIZthAA8cZORhi47c72jue1HivoEoySaQEg/z3oplshmKgUgGkunUHDwsyQSQO8TsQpOkjz3N1LJxYsmQH2zqqq7UNvL17pfDd/SGq79yQudcj2uYYZcoy0qQ1XALNNzpHeli0zEEtvjSwZS3HY6Xkk/Dc18giCOYzGIcjflDuW+CZpq9EoDSnVpqXF9DyJVlNwGG4wzJkxxtLhuR2CifDl28Q0PW/sj3OWUq08nSp66oQuqFKjKtMDR2gj71gvSx8B5YHBI8ykO8rHKDJE1rbb2K27lFYyOUH/29If8AIMNRFtakhJ+YooyAmYEi0xeD0nwkD5DHpHBza96qFl/t2oNUy9CkCATV1yf3VK/+/GbGDDlukeOWtF/NavTunyZttjIFeaxji9HStCjMlKbaiNpao7flGNWOUPbqCBnYT8OXw3m9r2VnI8YrjLjIah2L11eCokNI2bwMD5euLRMYZ2yVvws+Z1RO9Ctb4pW15JtKVQm2skaZAvY3gnYbbDpfQbgX1DxDKLBvTW9eVrMZluZhaXQ7FoFkj58rRuBJGXpD93C+Qf6pV+lisRl+8/Mqk3C6bsz9q6lj+E6YjYD6/PpgvivADaCExmO+3NJG/orOS4SiMXAO0CTMnck+fT5+OBulc7YplmPEAXAfVBfZ9n1q067juhszUMb/APmMN/OJ+OPZbxGIw87nYIeEP60oH/E//Wv0TTmMuriG2uNyNxB28sAWilX2g1dOWoOhAVMzSuOg1aT8OmNLpW8z2u/2FZ/UGF4aB7/sV8880cKIzmYFPvp2jFWS6kG4gi1pj4YzZJow40VtYfTsuSJuqN115d09eyGgU7MOhJOY1mxMAIywR4kiQOuHsdrvCc4cHj4LVxY5I8aZjvZPkdua+i296SONKuLATF4HhE2tIv54Wa5zTuFiaS14e9u/ZKubRa2cpNSfUFGk1FA3V0sDcSASficNA1GdQW40vjw3skFXvXuIO6cUpwNOpjHUm+ESd+FzwCEcx5bVSMsYV0Yb7agGm9+6T0xEjNcbm1yCmcMEyFh4IcPosrqBKlEPVKqpLawx94liBI33EeQXpAxiU69zuuQlEkUjgLJCpngGQ6hCfHUf54sJ5R/ciNy8mvyn5Jz5J58qZtmprk4WnMODqYLJ0DSAJMbnUBjaGw3XVMg8QE6gPVM+TyjAOQtbVUMszFUNpmTJgRaL+UYiUh5uqXo4mxfmeHfVUky5r5yq6qEIo0wSDqtqdl0xa8YXzsX8TjtYDtqs+gH+UvlNcXta07AXfx/wmVaJ0wYmLm4v0jGe/pcJjdoButj3243RGvdtqSUR/wB7RP3i5cLsSDqZC3yBm/jgc8EoxYI7IcHX/PglWO0TyaRsdP6p2rVTpsDJiBpJi43+eNaVjnM0tNFGN9kB5+yurJwASwdSDNxcSevSd8ByfYZr8v5sjRbmln3G6hymUqOjjtpG1yJkbn95tVgNtsc/FIMzKaHNOmjyf56LX8J7Iy6kkcw8QqilS7MrSRhFQI3ecuSzFiBOkmRGOixYYjP7e+4ryFJGcyeCC0UO/qjmR5mqqczRYs6Cp93LWEi48YkSOm+Fuu4ETswyNAB7+9af+lsczx+12o2gfBsypz9RKzEUnCmppBk6VBUCLjvRPpizo2jGaRyOPijPilf1SRkIv19AnCnxKmqUxRaKyVG0OVc2OuJCnWxKkLYyNUmynAMUnx3Xwo6riZMcIfJxttfdCOds8r8PDVa39pdkZ6K69IaInvse8BbUCbes40I2DxD+ywy9wbwn7krmDMNwvLuTSDgaVR5GpEJQG0wYE+cGOmM/IIjkp0lN8tilp8qEEWQCeVZ4zzHm6QQqwPeh9AFgxhSNS9JHT9cKty3l5DX7dtwkBnjxQKFWveK8PGbCHMOXCAxDqpkxIgKBNoufTfA3Z1OAkBN97HyXX4k78VxMDavzBKxrn/L06ea0UtlWN5vvvA8fDGtgv8SHUBQKX6vK6ZzXvI1VvXZA8o/3tP8A2i/4hjQhFPHqsOYf03eh+y33OZjLNlWprmEP4QARAYXZBa56+OGfwmQM7xqHmfOvRZjZYnYXhkO48jV9t+OVPwXmvL1c2tCjmVqFtUIjMQAoLemwweR0bo9uVlY0GdHlW4Hw99r2Cc8mvdEgbeGE5D7Wy38NpMQ1AfJSZioFRmOyqT8hOKDlMv2YUhezQt9kUnQzu7uNQNgHK/8Atw3kMjcWF3YLKi/Eh7zC0UdPPbak6LVqG5CqOhO354XIj96aacs2CAEG42naUCXWmy60928w48egk+F8MQFocTHd0fsqx/ifEZ4+mtQ4u7+Kx3KqHpi1NLe8XAm46bn0mwOOYcN19cje5psEu91Kxwc1e0GXomnNVu88yLKYBiCVkxva9jjc6LkNBMcnHP7rH69l/htMzGAudtR+hTe/B6NGoQ+nvSsEwYmJMEaQT4TuPQoS5uSZL1c8DbYD9UIZs80YrtR42J+NqrwzJJVWquWapK/sEw1rAECPgb4PD1DKLd6JTE874y05AFHz/ZJXFuOZrK5mtl+wOoEABmO5AMmDBJmZtvg4y5TRc4A9xSysnqEjX1G1pb/bQQ7Mcx1kFNxS+9BPaqwYI17XDBr+R6Ys3Mkc424V2QBndQBILG129ndPvF+HmlRp1nZQgcmoumxeo+osoHehSx7pmATuccpDlnIlcK77enkszM6Yy9cf5u6Ts3R1tqFMNIXvaal4UCTCgTa8Dfx3xqNIaKuvkunxGO8BmurpN/sJoxXzBBMdmJnxLW6+RxrNkD2khYE+K/Hhp9blbBmZMAAEEwZ8MVItZ6UmzFZ+I5mhRYIAtJiwF9oILeAFwsbnFnNk8Eujq9QAvjjdUzWSaYxGaLgb+BTFlcgwB11KjMdzrb0kXthKNko1nXZPuGysBsNXb3oPQ4WVzwfUxqfZCCTF5eASYvEDz8ZwvIcgCIO3f7V9hsB5eqtFExhc4cEj9Uw63CamUa4uoax8h0nBnSPjjD3D2uKvn9LXpKBOjdLvOavVy6JEdo0MpJHdjxHWYPrGAzZzY8fxJm0TYHu8rtUEUsteHtuCfRAqfJOTK6WoahMkF33iP2vPHHnq+UDs/wCg/Za5Y0G1Yp8j5Af/AClP4yfzOIZ1fMc8f1DyPJQ51tpS8G5ZyhprUOWpanUFiVBJPicE6t1HKOXI0yHZx7qmJM6Bg8I6bHZZVU4M1bjeapUFRQmrukQsAKItAF46jHVYzycCLVuTSPh5D25TpS43W579k4fYqFGhl6zrT01CitChmEoW8D1WPOR44XiuVxbG6iPonM3Pe+2yAkX34Qz2kZGmOHKFZ5RlKrUpBSF7O4lUVPegQJ3N/DThibHJqb35WS5xkaQfX/CucuZemmRyy16qIzUlamNNRywIkmA4AAEj1B6b5bsds0sjyaGoj5bLAPRJMl73ECrFHf8AcK1xjL03oqMurdo5ULqJh9Skwl7EECQf2he+LOxWR0W3ynYekYsGTE47U4E89vmheZ5VzDBQMvoZR3yaqXt1vbr9cHdHsNI+y+gYnWI2yP8AGkFE+yAD+yr5bgNJQxqUgz6lDtBgQ0ETBDDoSIEddsIy5EodpBoC9tv591k5kkc0zpWC77/ZeZrJZemhjL0w2qVbTsJHwO9mwzgyOdMzU43bfRYuTG1uO91dj9k6cQFNcmaSvTJWDAYTvJ6yWBx0zGZR6j4ojIBO57V/Oy5x8+M7A8MyCwBQvvtQSl7PM3SyZXM1UJQUd0UFtRAnwtpn54xBkBmQ9rruyPqt4Rl7AR5LT250oAsClYFUR2lRZXbSDZrwd42xoBwN+5LE76VW4hzlk6mVcioQKtCoU1U3EwhJ6QNxvvgQyI7Ivg0jGB5HCE+yJXbh1IkEGWMaiLEkjcGbHrbGjlAAM/8AiFm4jwZ52gdx9k81KbQAoG97gep93fyGFE8q+cyoWm2mAQkCwi1xsALH88XYfaXnbkE+YXzXw5apUVCraBYsYInpI3Asb4zHtYdu66r8dmMvS7t5Jgo8GrJkRnRmQpSppKixDEhW2G4kXk2BjGt0VjDlAAXe3wXO9Uy8iaNvjOsjdOlFii03rGWCLqDIrnZIJDAmQVnUb94+Nubzc18WbLEyqDjXdbeA178VobzXmR67/HhMmYz5ytIuoLVagkU5A09ZI6HrGBt6g9jLc72nfQeZ9/kl48Y5Umh5pre/msq5yyytXStUrk1KhEiV31ADY2Uz1j3Tg+C+eSN7tBIHJ3/X0ROoujjLGNAHav59VSr8GrkwhUjZybx6ECwi8YlksYFv28uyLORqMo3TNy/wM8QNWjmHqqUTumm6kAqwBABEDefG48L3woIgbaPesWbrD8l5a1ulo+vqgnD86lBOxIpOUZlLOqFjDG5kTgksEznktdQ8kzH1BzWgFM/sWDKuZdELtKCJAt3jufPD0FaD6rQ6sAQxrzQ3K0ytUqsADS0yZkOTHkYAO/w3wWgsQCNp2dfwSoOKGnx1qICxWorrJ6FVLCLjcDC8+V4Q0mgLHPvTTsTXjNmF2LHuq08AX97fpa39eeJbPETTXD5rM0m7UOhBULk97SFkkbAkx8z9MLHIiORyNhze3v8AmihrtFDzUlSqh3ZYHmMEkkxpKL3AgG+V4MkB2BSJ7UeJQMsKVWCXYnQwmNPjfrGFMuSGfYU6vkrVJG0nhYZmecM9JjOVokxDkflhpnTsSv8A1t+SgSvrlE8lzPnezpzmap1tcl2JN/GZG/TA24GKZ60DauwW5orAbIQN9W6F8c45mVr1UXM11VajAKKrwAD0E2HljTycWAzOOhvJ7Bc5C53ht3VHIcWem/aklyTL6jOv+InfAJIGubprjj3LUw8x2MdY+NbX8U98mccNfNBUpoIp6mBJ02dfwDTqIsY1XMXGFTG3GHiOOydzOp/jWeC1ne7Jsq9z9xep/ozsi4emzIoJc1KhM9p940kCFiACfXBMebXJVV8v3WXLAWNspt9n/C6jZOlVasUptQpqpW7AqB7si0d4d251GdhheJpc+QA0NR9b7pKQzklpNNIFeYUfMtI5ZKWZ7apXRXK95jKxGnSwuNjcHrheVgLhTia2+IUYLWuyI4y6wTpvnfzKj4jzxUVV1Ug2pZE1dUGZv3emBZWIZCC4nhdhg9Lgnc4Rv/KaO1bfNB8nnmr1a5J0CKbBVBMFmcmSN5Nu9by3wpJE2ONtC+RufID+bJDJYYpXRXdcLtlVKZJBLltKHSIkwZYm6xIsPHDmExplZI49xss6drjC4d6K0DiXDqYyrIU71NZmOsG/X+ox0zM0/idr3NLDmxLxNJqwB9Fk/A8utTLUVZwoZUB1G11gnxNiNvDfpjnpNX4hwAHJ3+K24yAyyeyaMzzCKtfOaaJ0mmlNSsSyhqnj3dRYEC/Q+mNJsgjBcR6+5Ksj1u2NKDmPh0UqFMsYBaikwLdkQQdIFy6jaN8c5izOJ1c7aq8vaWyzn4fdNnsuzSjIU7jupTU+RFNZB8wZx2kpa5rC03suThBjyZtQ7t+ybqmaA6E77Ana0W8xgAaU8XVwhXNOYByWYIbTFJiW8IUt9I6YYxQfGbQvdQ93F7brEM4lLQqU5YTA3PlHhE45cGTWdWy+i4L4ms1SEbhScAroWC9nVgVtTUTUZV7kkBge6e8FvEiD1jHQ9HY7xyS4AaefXZcl1t7HRamN716pn4hnajfe1KtKkzHVKqASCJEy7fCI9cL5OF0czFxcXH/aDe/yQsXK6gyMMLBXmRXyQbh71KlSDmpQCO0KWaYbcMxkTG0W8hhW+lxvt8TiCeL49VebqeXWhjmj0b/KUvHOC0aCU6gK1mrVlUQzTJYXaZJPW4nG+/8A1JC+LwYmezpO2wAAHuWLHhyeMHPfvY3VPh+TBcJVRlZ1ik4EKjJEarbEyZ8bXvHKMp4sH/K63I1svSdvRGeB8f8AshzFcgEt3URzpaoxeO6VHuqAsgGfLB8QBt8cUuMgcTM/ubSaM9RJLVmdXZmchGlQGYssEiT3SL4vIJC46eFvQ47HMBcaK0X2HH/xQ/gP+LDWPuw+q1Ovtpsfx/RakxMiPjg65tYj7RqhXiVasHKlFTQy7aioABOwG8nCMzQ5xa4WF02My8NgutylCtzHnQun7VXmTLCq0nbqGvtgYhi1F2kb+5I5cmkhja29wQjOcTzDnv16rH952P5nB44Ir2aPkEk7JkrYorneLVCaavUKjdtRsfvNIiGuImQ1+6fEYKenxMkcaQRnSmMUV+4TUPZP2lRe8WgE6iIJ90ztaJvtGEsmMCUaWq3i6me1uUnNY3HwxqhCTtlxlgKSsHBDKQqiVEgHckt7xPwxmM8Y5Fiuf1/ZdQZG/wDjmMI20k/HdXKHK1KtWrVndlVKjbaTLSzAXHj9MP5sxZM9o81yWNZjaT5INW5Vp7Cq0k9QMDEx8kYqzwvJvka61aTkuLX2ufLcSBYyPoRD3CRulw2XmuLTsp/aVnq9F24fXKVdBpv2xB1k9mABIgadNoIOwviIIWavEA34RpJ3ObpWhcvZSsmToU1Ltpp0wVVNQl1DR3nAO+wGwnYY55/iZErywGg4/O6XOzRZM2Q9kTj7J8th8SQFNnsg4UFwjqWTWkLIlgQGF9yJkTtOL/hpo/bP+VMfS8qDIZIHj8w4+aLtQ4bFsvSQkQC6qANjsTexBt44NL1BlUyMn3ldi3Iy2WRJXokKpk2oZ6KZU5asSJgMJQsUE7gQTBB64iUh+MS78w3Fe/lBdK8v1E88onnghp1AWYsJAhmg2lSFAiLCST0vgnT2OkmYRxY+6WynOED3e4/ZHM1RejkWft63aXLFjIMg90Bh7u8HxG569S7MY7NMZA0nYevw7rCGNWIJI204AE8+6+Uo8m0QBlVN6k0rW92wMgkSLsDANvWMcwQ50xLTtf6rfaQGbpmyeWpipmadEhitalAiQJr1X38IJMdB620Qxojk1biv0SLCfE2/m6g5/wCIdmEXSNPbAoAAWJVdRN/AyfHxxi4jS0eGCNOnn4+a2GbDX3TD7Lsn2VLMUyS0VzBPh0/ljoMaTXjMPqsOQAZ83o37FOdcMQdJAPiRgiMgfOXdyTnSKmnTqVvxDUAfz6z8cGhm8F2vfYdkOSLxNI96ydOWc3TJcmjSpvV7oNSV/EVEiwKzY2uRjEllZKS6ubXUsnYQA4EkBAeKcDrh2YMjVFeZpvPveBPh9PliI8ljSGI0vguaCNkz5XJ/azTRaa6VRdSsVYAACxCmxv7tj6YSbE6NziDz+qTy5mvZpNOSd7SciMu1OgTS1QHIRIjUDbV+JbeAvjXwmkAk2seQUBsB7glfg9Z+3oAMxiqhUT11CI8D54be1uk7dkNn5hS2HM5tBlXrs/36oT2BSmxEdZKzAsSRttjBZjnhp7hbfil3slp77pR5OoVMxRlmdgHJYqpLQ0BrreYva8A+eHskO8WmD+eiSx8FjY/xF7usVdKnxjh9E1nLvURp93StrW3EzEb4s18jBpofNasPS4pIw7xeU7+yrjSZerUps0NWKKgKkgkat423+uL48rGtOpOdbxXSRggbNtO+a59yqIj1XIVz3XCMANJg6gTNj4TgrMqJztIO65oY9DU0/X7LJefOaMrXzXaUX7VaoXtIVl0lbAd8Xmxm4x6SJxJIT0GdFHG1jmk1ffzQvM0LYQBIO6z5CCSQhtRO8OoBmPTDcLwCCUB4JBAV7P8AFKVSu9WmvZAGm2k2Mg98qFtqJvv4mcPyTNfuEmyNzRRUWc472iBDSQAAgN1339ZvjKZi6HFwcT3pMNbQpLGbEOw88aMf5QpKd04c7ig4HdlRqJAHdCkjx64z45msyKPmFu451YDhfn9k75ugaOTcsEh31qVIJ7ysCD1BthjOjP4lx96woD/RAS6OD1TT+0KU0Kutu+oIAJ6Ez0/LFAvKm+cp1ire6R06nwj47+U+mPALwVb22f8Axat/BT/wDDEP5V5apwTgaZnJ5es/a1CKKaU1BVOqmJ6X6DxhRF98eIAMfTeHO2HHP8tNnLMQLGtq6JPcqrSzNNquXp0wIaqSxVj3giFV3J0jcQN4n0XyJpHs0NFEefwWaM58mY2LsLJ+WyGcwcQFJMvUfuJ2gBMXKil5Xv3fgMJ40bpXyNuzXw5XQSkNAISfQ45T7Wi9KrUqFZaqhlbzuJEdZ2xqnHeGObI0AcDuk9YLrCJUeYlrV6VMoRLqDIUSCyg3G5j0xfGgcyRrr2BCDlO1Qub5grVOLcPP2SpLEk021DUxB7pgXY3mPh0x0AkYJCdI9a3WK7GIiADndtr2Wa8FzLJlgVEMqKVqWsdNl94XETsd9oE45YlokJujf6roGNJFBEuVsgaiduzHtCKbMdAhh2r6rERIVhdY2G8jGg6IZETh3GwQ3SCGbSzggFX/AGiVfu2+7UpTqAmoTYBqTIR3SfIGPHbGdE17J2sdsdPbvuER7PEj/NStci845SnlIqZkKdRMXJ8xtJvt5RjejjayJouufusfXK2d+sE8Ua52TYebMuJOslVUktAvG9t58oxUSA7g7J4QTVYZ9QlPnLm7J18hWopXDu4EKUYSdYY+8No6bWxd0jKJsJe3l4GkjfukLlvL1alRaQdgjGdBY6CRcd0ED5YxciSmEALcinfrBO6I8b4c32inS1KVjvIJUXIF5kgEHcYFjNqIu4PzXsycvOkixSscS5OamUVqYGvUdNGo0ECJlmAsCR3b+IxcyOa3Vd+oWc1oJoMI/wDid/jaz/n/ACKUcxTpopUCgpKltRUktY3IB8h8sauC55it5F32Q3AA7A/HlBKLCnpqIWFRWDLAspBkG+5nDFkmjwiEMAsHdWm4tWrOTVrEahDNomR4EAbfyxBYwDYKBPJ5pm4BxGlRyvZVKjaS7tKau8YXSGWBAMtfywlO175CW7bJ3Gla2ME70UFz2bUuxUrp6b+HpgscJ0i0Y9VcDQATfynT/t2W/wBquEWbml2HVm1jPPuUXOa06+Ty60qtMmnUqljrEIGYFZv1A6TgmFG6ORxcOVwLngtoFZ2KW4JBAkWO/n6Y1SUC9k45N5y1I/uR8rfpjNmFPKsDsqxUSDilkClKG5gDXA+OGWE6d0M0qmbnoDEzgzPehlwBVLMtJnBmCgrXabKVVS2Xt1T9MDgaPGHqvSSvbGWg9infi3E0qIKf2gVXVQAi6ho06pBsASCY64nL/wDc71KHCf6Y9EMbiumg1F0pkNSKjVqkXMEEGJB8cL0ipb4cQHXxkYlQi/todGztQBCKqhH1g+8jU0EEdCrCQfB28BgsPCkpx4fzXR+wUqFLO06YFJKep5psNKhSVJi8gwY/zxpDkNLg1h5uhulshuWXf09NeZ2R2lQbtaajRppEFWQTAKH1Gxabdem+Mv8AFe1qdW9jy+/vQosPM/EMke1tA7kHcj0Sh7TUH2MMGWabrCyJKmkFkDw1AX88N9JIL+Nzz8Ct+ckArNOXM5Tp6y8iwEgE+PhjdyonPADUmxwCLLmElK9O+hwwm11IP6YXj1xPAKs8a2kDunDOe2OswqUTlUuCpbtD1EbacPAj83mhNhJGm0LyvMVNMq1iSKUEAEEMBp3O6xH1xjfhrkp3c7H3JoO0BXuE8caglFmFQ0K1AiA6lgA0wASoEld9QMER4YcDwwPjYNz9+O1/Zeli8STxLAodvgUR5v5gV+HluxdBWzOjvEH3QWM+cqJ6TMHCow3iQP40ivd8F4yNNbrPaObSwVYkEwBPrYX88NeG+7JtDNLYMui1+GCsoBqNkq726spAmNt56YNHEGsDUTxX6Sb7j7FYhnKoYVmDHuKmmNpLCZ+eLxtrSK80B7tyief4hUpkoDIFJXkDYmNyNv8AphWKJrqcdjdJgSFnHkiXB61WrSDOWsIkmGBk2DEyLRA8ziXRsDiAhvlc7cpb4nnszQqkCrVSe8O9eCTBt1gb4bbDG5osBBbK9pJBR72eUjXzgbMAVSaTe+AZgpG+5ub74pLpDdLVUuJNlalwXh1EvalTECfdEflhUqbV2q+VRQtWnRSXgEgCSdhPnjw1HYKFznaK9mwFNRpFrDF2K19koUsqpEy9/Brb4NaGlrh2UNatTohyhqMF1DcTacZzXtj9t/A5X0vqpIxXgc0qvHuT2oo1JWqBlJYq8aX/AISI/X4Y2sqSCFzCx2oP7jt6r5XiyyzFwe2iEv5zlrM0lUvRdJAN/AiR9MeLgEzav8HzfZoKbhh5xbcn9cJTt1Gwix0SAVLWFMFC9RgjMQxprLKAN4PQkjfzxSEEk2OEfIhbE0EOBvyVSvkpqKBLHWVJn3x+Flt1GDB4AKzi/dEuVe1BrUlorUFvffTpvMCRvIv5AjriXuZsUzFieL7R4RI8p061QtVo1KKaB/dMhE94sTIPiIvYD5DOV4Y4tPR9Pa/YPH1QrK8LqpmFVCtREqqNQddpB2JBMAwdIImYw3jvDpGkeYSGTjSMDhXFrvmDiCrmvunmPeItfqLeG2InDjK8nzKBCKjb6KN67PAkxMiTOAIiioIe0T+MfmMSvI77SqWri9Tw+yEn0GXY/mPywZn5VKzs6ioF4HTF9gbXr7Kxk6lZf7t3X+F9P6jFHiM/mAPqERrnVyr4p13p6WBcxpUs62UbAd7YdBtgX9Nr7G37qxc4t3VelwSv/qzHky/zwUzR+aEAVMnAa8TpYDw8cUORGiBh7KenkKw16svVk6SravdC7kiO8I+WIL2GqcFNuCI5gU1oVRl3erJWQRqAJF/wiNvHpgAaS9peKpWvY0mmhxKlSyWWNZFLIgDIT7w1liukiR3CbDxvYYUmxnySkxuR4ckRSFhbyBv3G3khnG+IVKuV7JsuwojMF6TqdTHtFg92JEKdUnYkDGmSQ0JYAFxQPK0moVKtREqxTGikWXdWJB6XIkkEYC4h7Wgkb7lW4JRalx/PU6XZZVop0z2KOtMFtDyzyYIImLj9cEY5vJ5pVc81p7WgNfI1jXqJ2TCm1RVJCNpYK0AzEXAmdsW1NDLvsqbkorTyDUlfT2pNZXV5QnSEPcAtYECB42jCL3+I4WB7JFb83ymYgG/FH+JcG7EGsXLPVVQRpEAgCfLrHnGFcfJD/YaOCV6VlG0s8xcltRyz5tq34limUv3z4hiBHh+WNDGz2yS+FW6C+EtbqU/s3zP9qpAG+h/yX+WDStO6Ba1blnPrVTtUBCsLTvYx+mA6F615zJwejmSErLqXVMBiLxvYjxxYDTuFJRXNUgKJjopH0xUDdSk3LsdIwUKEkpm3pOtSmQHVgVnaZwgxocaPC+k9TP8AQf6I3x/irvQpiuZZVLF9SMGkmI0kgQAPniXYQZIDH37eS+eic7halxrmbK5WhQp5hDU10lKpCGREbMw2w/JkNY4tIOyHHC57dQWQ8yPSNWrVSadHtgoQICVDHpcAR4fXFIXiaTQBzwrPjLG6ihvE1pLSY06slpAmBBBG15Mgzid/EohWfF7AdfKkyObzTinUTtnGoA9khaNKgiY6+E+eBeGC4tAQPw5ADnHlT8uZ1KLVxm6NTvupmpTY+O4br88CzIXuaAw0tDHl8P2eUSzfFaj0x2KVKLarhVKqFjeQsEzaMJMjDCQ5wcK+NrVbPG1woWo6lMk0u5SJaopLGnobcTHegn4eOH8KYGZrSO4SuVYhc5rzwdko8fygVnqrIIqNPmdX+eNKV1zOb7ysCNpDB6BSZTMqyCN+uFiKKurSLD04mNQPxnEhQmD2sOlLM1SH++rU6dIr+xTCIzE/xtCjyV8FZwpWdu6hYnHgCSvJg4OgKKY/DhOW9RRGoxl6a+GBkKyu06SxdfpipC9ao8UqLT0vAsT4enW2KBhcaCsHUhVZ1auHUsg0GWESLNcXj+jhhgcxlHfdWLrU+bqZc00SjVeobltQgL3iAPM9fQjEu1jcilDiOyb81kEahw6nKqhUa4AvqlyGkSJn6giLYB4lT03c0PujQDW15c73V32CpZ7PJWp5WkKZUUxUZwywNVRwwCHZkVREjxG2HZ927JRhorlMskRpGMs2jWuly6dFHwxNnzUVa/GgvQDHiSoQ7iyQs3HoTizLtQXKzmONNmw0oRpVY/EGBnYTFiMVjxWwAae6uZS7lBOZebO1yf2QoR94GBPgsiD4HyEjzw5iYQjlMgKrJMXN0qj7N6mnPIf3H/wk4fl/Klitb5J0dkVRpCMRP1/XC4XgjPEacOpxJClT50EUD6YgBeSfTFsXpetIRiRPiPzwhH+YL6PnkeA+/JVuatWpELSo92LSDHne+OpggZHE197lfLjKS9w8k/e1DNfd5OoVlfstrbtA079JjHOZA1ZTmj3fLutrDoY5ck961OrTp0yGlq1MEyQGvc/Qj1wKMSwyF7ewNfoiPEcsdei9z3DWQlXpFaJYhGZbG8gg+MDpiRK8jUfzclONgjfEyJna11wribZWstMvrpFgXVLR0nzIH549qe6MujOl3vVZ8ZrWhr91a4/xCtVzgfvVQrhZ0hgyj3fw6TAMes4rE/XFcp3IS4jY2wEYGqowNISCs2RVBvGwE7jCMjB3H1VNdbKrleWaz1EZmiGDGJ3mcaGJK3xWgDuEHIfbCL7I5wfkhZNZqpHvMIi2qSd7YNmSO8dw96WgaPDHosqWqBUhRbSD8bycMbltlDKu1Z1aDbVAv5/9cQFC4554HXy1cdvUNUMoC1ZJ1QiMR3iSNIdfmMGadl5LhiOuLbqUd4VnWCQiTAjeJMTbz3+WE5oxqslEbdIvX4iVoiIDuAFjxIkb2OBNjOujwFGtT5PmCmMuJOqqq3BtJ63jEuhJdsNl60P4hxrtSBA0CCSATJnYGI8MWEOnfurjdfsvxAU6mgqrAHSSSOlrW2wN0Zc3Vam6RfKZlKqElVWCNvQHCkocx1WiNpM9bhKVqGTQqomovfQy+nSWb8MaoBjqCB6j2NPTyCD2H6Jl4aWuLBRrfblC1qJUTKCiBUeojk6bkQyoAY2sknD+Y8RR6nGgs+P2ijFPl+oBqqstMWjUReQfPeY+Z8Mc8eosJpgJ9E42Anle5bguolUqKzBZgEG89PIib4h2eWbuaQLVvwxqwUF4lxSll20VJ1RNhPWL+F8bMUfiN1N4Sh2NFQ8wFUpa290kC3nizGEmgqFGcvxmjxGmwonR2dyHQ7EE/hGnobTaRfwK+MhwtR2WNZquXYkkxJgE7CZgeAvjQApURnkSqFztMnbS8/8A62xSQeyoK1nkbNqi1EYqpDzci4IF/pgTWqpKZM3mqZg9qm/7Q/ni9LwX7iPEafYkCrT/AONf549SlK1LOUo/vU/4h/PHqUWFnFetYn8t8IMbuvoObKPBd6K/l8h28FEY2sLk6vO8CMFdkOjGm91xHhtc66T4K9TM0aOWzWWFTSmgOWKsIgbg9f0xR0zXnV3V2gsBA4KWuC0aFfNpksu/Zv2hgsCwldRtcybE9MEdC53tKni1stJb2bI6hcxmqtVVMhVCoAdvM4DKws4KszIePy7IjleSMjS2ohvNmJ/WPpjOe42vGV7juUYymVpJanTRf4VA/LFHPABVSDa+ZK3Ha9GrWWnVKRUdZPgGYxfbG4IGOAJHYKjnm1JwjjmYOYpMcxW7M1FDXa8mInaCZGDxwCwAN0JzyASVtHA+M0WVVFVSzKSoJEmN7b4DLCdRtEY8VssIzrf2qpEAX2sN8FH5UNysLUmvTH7ToPqBvirga2Voi3WNXFi/ROOeqUXZvtgdyxBUK6gd1dCmCpEwsTbbCrDJ/da6rVgCvBLB56gT+qB5/h2UnUlN2pkwFDqWkRMmAsSemLtMhO3HvUTS9PEYLg0u/wCNgfUoIcoyvqpAwJAv5EdPI4v4oqnFLSdMMtPgb7K6anU0oAl10/HTPxxYSss7pQ9EyRvSmSnT0d6nUDxErETABME9YxHie8UoPSpx/aVCM3V0lQgUAQIX92Dba5lvUnHneGSCSvNwJwN2onyTTyozbVs2sinTL06Ggla7gQFO8d4ggQZMDyLMQ17BI5MLoT7QUVbhGap1e2ddC1KmoqquiQTqKqGULYTCgzA2ti0uLqYeDt8VSKUB4vzRCMz2pfKsSFeoU0q9TukBVICqxC3tNrje2IhxneE3XsffyjZOTUr2sPs/ROPK+ZGW4exL0vtCiX0hV0zUFNdbFRLGRZr3xk9R6N4hMjrPkLNe8paLKcXUBQ7nzRrhzZbMHW6anYsoLsbhYPdkAdQJFrxqN8czkQ5OOA1p224H33/nkFoiZsm6p8L4mpd0SaAUaFAFMqxBEEMBrPnJ67YtPA4MBd7V797+XCO3QNx9Uie0XlvMZea9Z1qI5hDF0JOrSPW4G9t8dB0rPgyG+HGKI+vvWdM0gkoRn+JV6tN0YhijCFWOtgfMXsR440xGA60PUCFW4NnSlKpTSwNOoX3kMqnTBEGPEbHFnNt1lVQJ6MEj0+on9cFtUJRbk7Lq2cpK6hlOqQRIPdJ/PHjuFVx2WvcO4BlXVi+XpsQVuVn8Cnc+px4NQ9SuVOW8l3f7JQ/4BidKkOXme5d4eKdspRB8kGJDSp1ICvL+U/8ApqX/AA4toUaylfgfBqwzNBmVGUVUJipTNgwm2qdvLGa6OwQF1UmY1zTa1ytmN9CWH7K2+mEzjSeSw/ECzjjPMOYTiCqKT9nZQCpGvVF52keOGY8YeHfdUMm6GcqcYpUeNmrUJFJar+6OpDKotFizAX/zxoNPsC0I87LcOEc4UMzr7PWAhgs66VJ2IBO8EYxMqbSacUZsZKsV+KpG4I8jOM507ByUyyE8rzh+bVhKmd8WFPbsvPb2Xy1xgxmq3lXb/GfG2Ooi/I30CRd+Yoo1eai6SGbtFgGoW0xUdrxCD/ORvg+PfiN9UOT8pVzlDKVlzozC02ZVLEbXDahH16YjLkYHEBSweyEBar9+5IvJkHxk4AAdIXnKXKZgfaKJaQoqJMbxqEx8MWCqFqvPOeyudqUQpdSF06VpyxLGItubWx6rV7RDkTL5TJCqK6lw0Mpq0gCACFIhvMiY8L4tSncpIhHqiIAqVGVUUhfxGJLGBPy8MJOx2yHUum/85Nh1E1oIAH2U9RaMaKaM1ZSQyu0Cxi0CfjioxBe6g/6nnP8AaFLR4M1WotKnSDOblVqgQBvuDg7cNh8/mqO/1NKBZaFeXk2qX0dk4bVF2WNid9Pl4YqcRl1ZRv8A8ikDQ8xiv570h8zUexzbUiASvcYG4PjcQYmCCIOHenMDX6Qe6yus5X4hzZKq2gqTi3FGApyBqRdKHUDAF1kKi6tM93UTEnGzFixxOdp/uNlYgfqCtcLz70jqCFlIK+8CrCAo1IwPeCwNQINh5kzlYjJm6Xdjd8H5hQH1wpeD8ZBoZwFTpK0yS7aiT2i3YwAbKIsI0g7ySnnN2ACahg1RvcP7f1RnJ12Sk6VENMqtOovmGYQ0i95+BxiSY19kqH6TYKjPH0yxZVVSx7opaSSD0BPuk6jLGbRpFwThabp/itF2KTEUxaVNmeI68saGYiqA76kkhhUDEzZbQTBWbwY6YqOlubJ48Z0nYcdvJF8SxpJQ45WmWR0p00qrG+ooQotaBDeVwd8aek0h0g2Yy5pmvKoGNNzs03HSTscRRsLyXKlS/wDugfIAfpi9KCEY5Jb+20f97/A2JpUeNltfBG7rdZ0/4FwUAICIRt6YsQoBVXiBBUQNhiwYvFyDBsX0KmpEqlTIFiy8OTSkanp6k0EmwJUAT8cZja8lpOkceSjB5dygYAUq9NmAPcqE+90uxvY9OhwTTaoq+b4Nl6LhPtuZpEiRMERb93z649VKFUq8Hp1CSvEaDzsKtFSB9R+WPKFx/wBlMyw0UsxkyvXQsH5AEYXkxYpDZaL81dr3N4K4p8oZtBpakKq+K1oJ/wAOAjp0N2Won4iQd1Zo8Kq0h3ctmljorIw/xE4uMSMcBQZnuNkpY4l7PMpUqvWY52kzNqIbLsVBO+ynrffrhkbNpDO6jyfKmXpOR25rArCU+wNNtcgg6mNxvIjacGxiRINPKpIWBhMhpvdWuFcNq6KpNaglQu/dZwpW5gwNrYvnMuWyl8J8boR4R9lJeY9necqOzo+Xcm501D+qjfARVJpVG5Bz9F0apQGkMCSKlMzBkwNUkx0jHrCikf41xPKB6YanWV6RDCCCAQ15HXbx64LC8NdZVH3WyZchxehmR2rUXNJTUDL7pPaOrKLd6RAuPDFppWONjyV2Nf3SbWzGlmhCKaVGCmJK96RfxEfTGYJCDQXRz4uO8254BofZE+HZGtme0zQpVKnbGzKkA6e7+ExuIt4Ys+Yg1SCzBxq/9jfqjPKGTrZbOLVrZesFhhCgdQQJuI+eLNnHdUfgx0TG9p+KdDx2mrKamumO0LglZOnvWME+IHxxAyY9VWhy4rnMppB+Kx3mvlrNZ3PVszRo1Gyz1TpqhbaVOlmiZtBw3G4N3as+XVel3bZR808qUFpK+VzzZqqzqiURRZWIM7SfHpHXDRynuFJdrQF3wHkuoVdc3VqZFEEh6uXeGZoBAOoDYfXF25Dmt81UgEoTx/JU8mMzlqeYWuD2RWoq6dY942k+6RG+FZJHPcD5JyKZrYnsr81b+hTbwHMB8sGIBJUdMdg2nta6uQuCzGOjyCAe6Z/ZbwjL5n7W1fL0auh0Ve0pq2mzExIMTI2xgdYI8bSOwXT9PaRCCe6Pcf4Nw5KrtUy4shruoQAEIKjMenebr5hTjNDjVJ4juueA8M4Vm2dKeT0mkqEgysBxKxpfyOIIIUB1rJ+aeDVRxGulKk5orUdUIDHSoEkar2kEQepxTxmNduUYRueNgh3EOGgZdKS5aqazPqdxTNheJYi8zMCAPPFpJWHcFV8JzdqXXAMktColRqdROzJ1O8x3lgDYCxO99/TAvFBNWqOa6twtP9n2cpvlqhfcM0H0sPywdrtkEjdNVSnTOXR1364uCV4gIZxrJBEDA+90wzGbQXJbUDBdKHadeLdgMsezXUdSmFkHUxBgAfiO+kDzxjAhadKHifCsrWqUEzWstplFaxLGbEKL6RaSbfXDDCSTp5RXN0x6xwOV3xviqrWp0Gp9orMNVSmCWXSZAgXn0+WAB4dapI3TRqrVTifAKauzprqLBeBYL197qLGRbcXvjxQ0LzmapU67p3UiO8plQfCT5dfEHAS7dWAXmcrgd9KjqYEwwJtabGRO9/LxxcEqEYyXMLaB32PdiTvMefXFTJSsG2u8hxbM1CxpsSBBK90kXI8PKcLSZ2ghtE37lbw/Nc1uOVSVpVHTvxJ02WYtY7zicbqTTKAfOkPJxXSQua3khd8LzjNTLpSoV6QYhKkCXJcju7ixsT5YfyMgWXHYbDdB6fiGOJsbjxalp5HL1ay06uQoBjMnSJFif2RO2PEgED3WnJYQ1uoGwkXPNTo5rs2tSNcIKQnYkXMRYm0Ek7Y9SWJ9qlDwvOLls69REUli9IbgiWBOm2mbWBInFHatJ08q4AsWjfHaIVRAKNIcltCsAGIUd03gzfbaJnAMeAtZ7fJ99pySZpYGhe8OpBck9SezlqheTIbUzqCsd73ovtA6zOBNZJ4oFbeanJmBcd/L7I9yZnly+Uo0KqsAEDdoYIOslhtYWPjvbDvuSSIZ7mTL0ssubZTDmBF7gxG+kf1viNI8l7UoOW+NZbPs5RCNIBkiN/Q3239MCELSdwrE0sg5rNdM7mly/EKiharhMuj1QSzEDSABoOpjg+oN2VeUAzn23LJTL0+zda0JVmW1Lewkgx4gRi0MjZX6GHdec3SLK94rzXxKqOwrZp6ikgkQCPG/dn4YdkxzA8NJBvyQwQ9upOHJns9p8Ry32vM16lNwWUgKgUBZubDoSZwCcU6gpbwmDh/ImVCGnR4ohAt/5ZI+T40ouqyxRhmm64KzpcCKWTWTumPk/ltsilRKWco1BUqayWp3nSFi1TyxnzzmZ5e4J6NmhukFG8zlMy4ILZVgRB1U3uDuPfNsBsLzhIeCPkouHcNr0SzLTygLAAlNayF2mx2xNgqjGSA7kITleWs2tSsxaiRVql7M1p8tN9vHGZkYZkk1g0tbHzBEzSRa6q8IzwiEpH/8hH5jwwIdONbndFGeL/KgPOPKOezdJaYpLY6v71dxtv8AHB8fFMRslByslsopoS1l6VTKk5esuh1ABAIPQHcWO+NJoWa5HMtxV9ASe7g7W2hFy9z/ABZ6igMbDbDLG0guchi17YNSDaeOO8fSmtImmO7U0tcIdaqLiCQYUkX2keGOeLxa11NwlKucNCrUg001HVIkyQVgibgAAkG9/MYLDM8Ouu1Ix8EwlhuyQfkiNbNLSQ1YVdX4Cv8A5oMC+94j6488hiq0Pk2u6H0VLmTPUmy61w9N2VToUvpntBoa0zIUyAR0+XiRSEs95hzNM5nN9mhq93tJAkJGkXO2kRFrmcBI8lZpCl5a7J0dpRap94hrgAESwZo3EgiIkW2xKIACveXcxUqs1M6uyUkyz+o85Ppith2yZhgJNkbfdNnJpWh2gp06hBKhixWxvYSR67fpjKych+I8Stbbao/4RMnHF1YHcVabFoK9TWQDCwAQDEmSfy+WLYUkOXO6UAUAB255SLi5rdKB5PNIS+W7o0VH7pFtOskBYt8Nx6YdytmewO++1q0TiDaI0s1NdNSk6mYIYsAFPWBv8cDjcX5DXXt2+SK6GoifKllXE0b7ZVDEgNX28dLDT+WLT5DtWho7q+PjCw93kfqqXDMpUrZoOb06eY1MANhr72/kOgx1EGAwweI87kbLls3qrceXQdkU5mytaq9GpUDKvZLTcgAmVJLx4+96bYPDgxP1C7Pb9EE9ZJA8v5dfRK/EqmazFWq66iheAqNCqqmESJAsNsc9nwPxptDjuu76VmY80ALmj907clcJRssv201GZXOhWqP3FAAWArRG5+WD4sOpl8lYnWM17MjTCAGV5DlNeY5dytTLCkHqikhlVDSAfVwep8cXdE7VpISbcwCPWNz6JT4pl6/DqbVsvmAUspD0xME2gqIJk+AwGfGdGwvYfgU90zqeNnZAxpo9JN7g7fEH90pZTk7PV6w4giPNU9otRGQXNw0TIuJ2xUDU0K+Q0Mkc1vAK54zyzxCvUytPOLVCmtoVyheNYljZpb3JifHFgwN3byhsfTgSLCZOX/Z/Ty2ZqGoDXpdgWUmi9Pv7QJM6uvocEisOBJ7qcuUSA6G0K4C0Cjw+mmQzFOkoRTScRJNzSuZJnc4iV2p5KDGKYAs69j2SpOzpUCFikoGE6hfVp8QJExi2r2EBgPi+6lqFHgaKGAWldCLIBfx6nAbTdIg1E20hY0gDyPlbHrXqVLMZSqay1Bo02BBYzb/dx61BXPGMlXq0qiI3Zs+zLUMj07tjjxKgBJ+f5fz1CgXGbrHsxqb71jIG/wBMKMdLr34TbjEY6HKZ+C5jtjRq0arvSGpWJZrtpm4Ph+uHbCTF2s357b/vGt6j/CMXYqvUFCtbDjAlHFdValsMNCE4qolS2CUq2jef5UfMZgPRPZ0AxQI8hmgaTBJM6iLEmBaw2xyQNrcLK5UXDeEZqm401WoIAQ8mFp6J3cGCGPU3PSBGKanAqtgJxqZnMGnl0aj26mHqFGBJGkzBGlXMmZtMxeDhoOLgO6hUM9RyS1MsUGntKgU0wQ8yDMgEsrDa3Xpgm21KqKLybw6qO7TuCPdYjz+P+eLaQvLrM8jUWBAeoJ9D06kjUfibwPAYgstTarZTkupTAC1UKjoyfTukWm++BCCnXabjznsZpVOrypnF1aKiQX1wGKiekC+3TA34zj3/AGV5M7WK09q96Ylr5hUg0W16d00HvR5kRfyxht6dmxOf4ZABN+/0QNcZq0u8PpVadR6ldKsOxLLDBSDcEafDxPjh6Vk4LWgEjurwuY5xbx5FG+CNTaqpAZYEIGM2vIA6R5dDtjUjjj2cBRAVp5aZ4e3wSBxlzSzGZZh3WqMEBIlm1TKqLm0icFNFukhBik0X6Jg5JK1aGoopYoTcXESBJidz49MampzY27lYOZDHJJ7TAeeQmvO5CgygNR1AE20z1E72/wCmEZc8441vfpHnafx+n422ljdvdx5pQoZFXNbsk7ACp2YWOtv2dViZ+eAzPbPplDtVi7T7XaLaO3wVjh3K2Y1xUIAIPeBkbGDFsS1zm8ILg13KI/6CqL3VzFIz0db/AFnBhkSeaWOJCf7UE47Qiu1MMmkae7qG8CZG2+AZMr5GlpKZx4WRPDmjdWGy7UzoWo1OPwjYTfpb5Y52XJlhfpsrabGyRuql0aFR9Jasx0NqWS1iARI73gSPiceHUZ+xUHFj8lM2czC3FUmPH/MYlvU5tVWo/BxHshPLfMtXPcP4pUYkaUZEmLfdNJsANz9BjooQXVfJWNOQy67KDldaaV6FRaxHZIVnTYhlAKxEXIBkDpjXkwvZsBYLeo6SU2ZHjNWawd0J1fd91rgiQTfrMWjbC7sMcgJlvUd90qcz8w5nMUaOWomj2lZlIKakMrUkCWPXTJ3wu7Ge27GyebO13BRnMe0F6WlKtBe21sr01Ld3TEEGIM+uKFhRA8FAKftHzLK410lqKFIBWzS8abm9j64oBZRnMoWi3H+Ys1TpZpxVo1qdEmm47MidS9CGIsGB3wbwdrS/ib0hPsmzFdDT1VGNGqajBOgKiJnrM/TFfDNL2sAoVzbX15+u375Hyt+mCsZSG91qCk+HGhLFeVqmDtQiqoq4trCrSfafBeIrRFNKtKrpHdNOoR1JFmgf18+bENcLdMlopwrjGbUO2cyThre4usaRO5XVJk2EfHHmN3OpUcANwpeD8xJXr1l01aSLRnRUXRsTJA9OtiPPBY4nGyEKSZrdip+J8PpVBRoZdxSOWqLU0gQAB4yL2JPwM4sYnckKjciImg4K7w+rRqKVFRGkkKVYaj1lYNon8PnYbYrxsjjdFAGBuQR08dj8+n1xK8umqQJIttHXEL1KHtm1kBe7G/n4C+3n/Q8vLpqsdPn09TiV5c0tf4oa5Fulz08ALePrviu6leplxr1sqyPdgXHnP6Yke9eWLcd4JRzHFKvbV2pQ50ruxhSxdbiFERsb+uKElXam7kektOm4VyypSuzRJLbTFvG+NHWPCbq81nzY75pCGc0fmUzcys4pgoHJBMhDfw85v0jHIdeZrdGDxvzx2W105rdVOr4qlyNScJXdr6qkCWJMqINzf3pHwxq4EBihDSqZsrXynTwEfqAl6bHuwWGmfekfpE/PDaUVbmJWaiRTBNQEFfn4mAIBnfpiTupCy3mjhuYfMV3SkXRqhZSomQbgjFCFNoVxLnGtl2SmcqXIQSZj0tB8MZ2R05sztWqk7DmGMVVr9S9pQ/Fk6y+l/wA4wsejO7PCN+Pb3BTDwjmenXovUAZIkAPAO3r54z5sN8Mobz6JpkzHtvhVfZHSJ4Vn43aqw/5FH647DH/O34LnsrdrqTHwfhzCtp0rGmSAPMY3JpR4d2uYERLqAV8LCV2JAKlo8oFr9OmAg25o86Vy0cpY4YpfMZJghCK8lptsYOGcphaxwJv9EeCRpcKFL3iOQqNnalSmVhmYiRPje4iLeOM10fsp2J/tIJlODVTXSoVYGKdwRusXEbXAMYVaPaWm51tTLxxW/wBH8QFQksaqgkwYtTAEj8sNvaLFeSRarHJFIrl8mt7I8R4eP1x6qbSsUj8XrA5uvP8ArG/xYgUCqleCpbB2lCIUdaqPpgmpUpUNeKWq0mrleuzDvMx9STjKC1E7Uc04MB2F+jHHlKa8oNSDV3vW/wCeIulNA8oRx3J00GpKaK2lrqoB28RhiNx4tZ88bA2wAs24DSXsjYWEi21mxzDXu8d2/ddXAxv4RprspTxWutSiFrVQCbgOwm3W+NtnKyH8rWUYyl9x+mLFDHK6c95v4B+bYlSpl/XHl5R5c+9/EceXlLjy8vmb2jsRxLMQYu238WKFF7Bap7HDNG95o05+uG5P/W1LN/OU95+krASAb9ROF2tBO4V3EgbIFyF/dVfKs0fM4YyhT1Vn5US4r/4jK/xt/wCm2Fu6IOEM54qEIIJG2x8ziHKWopwM/dUh+7iO6hEfs6MBqRW9QDi6hVcxwXLnfL0T601P6Yil5K/GOFUFnTRpD0RR+mIoL1pE4gdDFU7qncLYH1AwaHlDfwqyZyoralqOGgjUGIMeE7xjQ5CScKKp1c0+lxraGPe7x73r4/HFmKrmik6cv1DNMSYtafLDs35LSTBRKKcQ91/9nU/XCbvyI0P5lj2SrMGEMR3l2JHXGcOVsHhPauTwXPEkknM3J33TDLvzj0SoTLyqx7LKf7Nv0xd/BXis0zx/tVb/AGr/AOI4H3UdlYXBmoRUOYFsXUBUCcQpX//Z")
                    .into(imageView);*/

            imageView.setImageResource(sampleImages[position]);
        }
    };

}
