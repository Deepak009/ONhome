package tech.iwish.ONhome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static tech.iwish.ONhome.helper.Constants.BaseUrl;
import static tech.iwish.ONhome.helper.Constants.GET_ITEM_DETAIL;


public class ItemDescription extends AppCompatActivity {
    TextView addtocart;

    //Array List for single Item Detial End
    TextView tv_item_name;
    TextView tv_item_id;
    TextView tv_price;
    TextView tv_save_price;
    TextView tv_description;
    TextView tv_Off_price;
    TextView tv_item_img_url;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent() != null) {
            getIntent().getStringExtra("item_id");

            Log.e("IDee",getIntent().getStringExtra("item_id"));

        }
        imageView = (ImageView) findViewById(R.id.item_image_view);
        tv_item_name = (TextView) findViewById(R.id.item_name_view);
        tv_price = (TextView) findViewById(R.id.item_price_view);
        tv_description = (TextView) findViewById(R.id.descriptionitem);



        GetItemDetail();




        addtocart = (TextView) findViewById(R.id.text_action_bottom1);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeActivity.Cart_Count.add(1);
                HomeActivity.Cartview.setText(String.valueOf(HomeActivity.Cart_Count.size()));


                Log.e("Cart_Size", String.valueOf(HomeActivity.Cart_Count.size()));
                Toast.makeText(ItemDescription.this,"Item added to cart.",Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void GetItemDetail() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_ITEM_DETAIL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray products = new JSONArray(response);
                            Log.e("Lenth", String.valueOf(products.length()));
                            for(int i=0; i<products.length(); i++){

                                JSONObject productObject = products.getJSONObject(i);

                                tv_price.setText("Rs. "+productObject.getString("price"));
                                tv_item_name.setText(productObject.getString("name"));
                                tv_description.setText(productObject.getString("description"));
                                Log.e("Path",BaseUrl+productObject.getString("img"));
                                Picasso.with(ItemDescription.this).load(BaseUrl+productObject.getString("img")).into(imageView);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e("ReqOK",response);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast .makeText(ItemDescription.this, error.getMessage(), Toast.LENGTH_SHORT);

            }
        }){
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("item_id", getIntent().getStringExtra("item_id"));
                return MyData;
            }
        };

        Volley.newRequestQueue(ItemDescription.this).add(stringRequest);

    }

    public  boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();

        if(id == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
