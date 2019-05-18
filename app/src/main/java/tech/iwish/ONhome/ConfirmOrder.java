package tech.iwish.ONhome;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

import tech.iwish.ONhome.UserManager.UserSessionManager;
import tech.iwish.ONhome.cart.ShopingCart;

import static java.lang.String.join;

public class ConfirmOrder extends AppCompatActivity {
Button CashOnDelivery;
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        CashOnDelivery = (Button) findViewById(R.id.button7);
        CashOnDelivery.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                session = new UserSessionManager(getApplicationContext());

                String Item_Ids = order_ids(ShopingCart.itemid_a);
                String item_QTY = order_qty(ShopingCart.QTY);

                if(session.checkLogin())
                    finish();
                    // get user data from session
                    HashMap<String, String> user = session.getUserDetails();

                    String email = user.get(UserSessionManager.KEY_EMAIL);
                    String ID = user.get(UserSessionManager.KEY_USER_ID);

                    Log.e("Item_Ids",Item_Ids);
                    Log.e("item_QTY",item_QTY);
                    Log.e("email",email);
                    Log.e("ID",ID);









            }
        });
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
}
