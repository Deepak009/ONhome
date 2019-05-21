package tech.iwish.ONhome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tech.iwish.ONhome.Connection.ConnectionServer;
import tech.iwish.ONhome.UserManager.UserSessionManager;
import tech.iwish.ONhome.adaptors.BestSellAdaptor;
import tech.iwish.ONhome.adaptors.Myorders_aduptor;

import static tech.iwish.ONhome.UserManager.UserSessionManager.KEY_USER_ID;
import static tech.iwish.ONhome.helper.Constants.GetFoodsBreakfast;
import static tech.iwish.ONhome.helper.Constants.Myorders_url;

public class Myorders extends AppCompatActivity {
RecyclerView Myorder_recyclerView;
    LinearLayoutManager HorizontalLayout;
    ArrayList<String> sno;
    ArrayList<String> customer_sno;
    ArrayList<String> name;
    ArrayList<String> email;
    ArrayList<String> mobile;
    ArrayList<String> shipping_add;
    ArrayList<String> city;
    ArrayList<String> pincode;
    ArrayList<String> product;
    ArrayList<String> p_rate;
    ArrayList<String> p_weight;
    ArrayList<String> quantity;
    ArrayList<String> amount;
    ArrayList<String> order_date;
    ArrayList<String> status;
    ArrayList<String> payment_status;
    ArrayList<String> payment_method;




    UserSessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);

        getmyorders();
        HorizontalLayout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        Myorder_recyclerView = (RecyclerView) findViewById(R.id.myorders_recycle);
        Myorder_recyclerView.setLayoutManager(HorizontalLayout);
        Myorder_recyclerView.setAdapter(new Myorders_aduptor(getApplicationContext(),sno,customer_sno,name,email,mobile,shipping_add,city,pincode,product,p_rate,p_weight,quantity,amount,order_date,status,payment_status,payment_method));




    }

    private void getmyorders() {
        //Array List for single Item Detial End
        sno = new ArrayList<>();
        customer_sno= new ArrayList<>();
        name= new ArrayList<>();
        email= new ArrayList<>();
        mobile= new ArrayList<>();
        shipping_add= new ArrayList<>();
        city= new ArrayList<>();
        pincode= new ArrayList<>();
        product= new ArrayList<>();
        p_rate= new ArrayList<>();
        p_weight= new ArrayList<>();
        quantity= new ArrayList<>();
        amount= new ArrayList<>();
        order_date= new ArrayList<>();
        status= new ArrayList<>();
        payment_status= new ArrayList<>();
        payment_method= new ArrayList<>();

        session = new UserSessionManager(getApplicationContext());
        String user = session.getUserDetails().get(KEY_USER_ID);
        Log.e("USERSSS",user);
        ConnectionServer connectionServer = new ConnectionServer();
        connectionServer.set_url(Myorders_url);
        connectionServer.requestedMethod("POST");
        connectionServer.buildParameter("Userid",user);
        //connectionServer.buildParameter("password",password);
        connectionServer.execute(new ConnectionServer.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                Log.e("Serve25",output);
                try {
                    JSONArray products = new JSONArray(output);
                    Log.e("Lenth", String.valueOf(products.length()));
                    for(int i=0; i<products.length(); i++){

                        JSONObject productObject = products.getJSONObject(i);


                        sno.add(productObject.getString("sno"));
                        customer_sno.add(productObject.getString("customer_sno"));
                        name.add(productObject.getString("name"));
                        email.add(productObject.getString("email"));
                        mobile.add(productObject.getString("mobile"));
                        shipping_add.add(productObject.getString("shipping_add"));
                        city.add(productObject.getString("city"));
                        pincode.add(productObject.getString("pincode"));
                        product.add(productObject.getString("product"));
                        p_rate.add(productObject.getString("p_rate"));
                        p_weight.add(productObject.getString("p_weight"));
                        quantity.add(productObject.getString("quantity"));
                        amount.add(productObject.getString("amount"));
                        order_date.add(productObject.getString("order_date"));
                        status.add(productObject.getString("status"));
                        payment_status.add(productObject.getString("payment_status"));
                        payment_method.add(productObject.getString("payment_method"));

                    }
                    Log.e("OK02","21");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}
