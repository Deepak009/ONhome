package tech.iwish.ONhome;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import tech.iwish.ONhome.Connection.ConnectionServer;
import tech.iwish.ONhome.UserManager.UserSessionManager;
import tech.iwish.ONhome.cart.ShopingCart;

import static java.lang.String.join;
import static tech.iwish.ONhome.cart.ShopingCart.itemid_a;
import static tech.iwish.ONhome.helper.Constants.OrderResive;

public class ConfirmOrder extends AppCompatActivity {
Button CashOnDelivery;
    UserSessionManager session;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        builder = new AlertDialog.Builder(this);
        CashOnDelivery = (Button) findViewById(R.id.button7);
        CashOnDelivery.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                session = new UserSessionManager(getApplicationContext());


                if(session.checkLogin())
                    finish();
                    //getting cart data
                    String Item_Ids = order_ids(itemid_a);
                    String item_QTY = order_qty(ShopingCart.QTY);
                    String item_price = order_price(ShopingCart.price_a);

                    // get user data from session
                    HashMap<String, String> user = session.getUserDetails();
                    String email = user.get(UserSessionManager.KEY_EMAIL);
                    String ID = user.get(UserSessionManager.KEY_USER_ID);
                    Log.e("Item_Ids",Item_Ids);
                    Log.e("item_QTY",item_QTY);
                    Log.e("email",email);
                    Log.e("ID",ID);
                    Log.e("item_price",item_price);
                    ConnectionServer connectionServer = new ConnectionServer();
                    connectionServer.set_url(OrderResive);
                    connectionServer.requestedMethod("POST");
                    connectionServer.buildParameter("Item_Ids",Item_Ids);
                    connectionServer.buildParameter("item_QTY",item_QTY);
                    connectionServer.buildParameter("email",email);
                    connectionServer.buildParameter("ID",ID);
                    connectionServer.buildParameter("item_price",item_price);
                    connectionServer.buildParameter("paymethod","COD");

                    connectionServer.execute(new ConnectionServer.AsyncResponse() {
                            @Override
                            public void processFinish(String output) {
                                Log.e("Server Response",output);
                                try {
                                    JSONArray cart_sucess = new JSONArray(output);
                                    Log.e("Lenth", String.valueOf(cart_sucess.length()));
                                    for(int i=0; i<cart_sucess.length(); i++){
                                        JSONObject productObject = cart_sucess.getJSONObject(i);

                                        String status = productObject.getString("status");
                                        if (status.equals("success")){
                                           Intent intent = new Intent(ConfirmOrder.this, Order_Recieved.class);
                                           startActivity(intent);
                                        }
                                        if (status.equals("failed")){
                                            Toast.makeText(ConfirmOrder.this, "Failed", Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        });

            }
        });
    }
    public String order_price(ArrayList<Integer> price_a){
        StringBuilder sbString = new StringBuilder("");
        //iterate through ArrayList
        for(Integer language : price_a){
            //append ArrayList element followed by comma
            sbString.append(language).append(",");
        }
        //convert StringBuffer to String
        String strList = sbString.toString();

        //remove last comma from String if you want
        if( strList.length() > 0 )
            strList = strList.substring(0, strList.length() - 1);

        /*System.out.println(strList);
        Log.e("ItemList",strList);*/
        return strList;
    }
    public String order_ids(ArrayList<Integer> itemid_a){
        StringBuilder sbString = new StringBuilder("");

        //iterate through ArrayList
        for(Integer language : itemid_a){
            //append ArrayList element followed by comma
            sbString.append(language).append(",");
        }
        //convert StringBuffer to String
        String strList = sbString.toString();

        //remove last comma from String if you want
        if( strList.length() > 0 )
            strList = strList.substring(0, strList.length() - 1);

        /*System.out.println(strList);
        Log.e("ItemList",strList);*/
        return strList;
    }
    public String order_qty(ArrayList<Integer> itemQTY){

        StringBuilder sbString = new StringBuilder("");

        //iterate through ArrayList
        for(Integer language : itemQTY){
            //append ArrayList element followed by comma
            sbString.append(language).append(",");
        }
        //convert StringBuffer to String
        String strList = sbString.toString();

        //remove last comma from String if you want
        if( strList.length() > 0 )
            strList = strList.substring(0, strList.length() - 1);

        /*System.out.println(strList);
        Log.e("ItemList",strList);
        */
        return strList;
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
