package tech.iwish.ONhome.cart;

import android.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopingCart {

    public static ArrayList<Integer> Cart_list = new ArrayList<>();

    public boolean additem(Integer item){
        Cart_list.add(item);
        return true;
    }


}
