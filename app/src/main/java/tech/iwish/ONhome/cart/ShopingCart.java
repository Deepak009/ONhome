package tech.iwish.ONhome.cart;

import java.util.ArrayList;

public class ShopingCart {
    public static ArrayList<Integer> itemid_a = new ArrayList<>();
    public static ArrayList<Integer> price_a = new ArrayList<>();
    public static ArrayList<String> save_price_a = new ArrayList<>();
    public static ArrayList<String> description_a = new ArrayList<>();
    public static ArrayList<String> off_price_a = new ArrayList<>();
    public static ArrayList<String> img_url_a = new ArrayList<>();
    public static ArrayList<Integer> QTY = new ArrayList<>();
    public static ArrayList<String> NAME = new ArrayList<>();

    public boolean additem(int itemid, int price, String save_price, String description, String off_price, String img_url, int qty, String product_name){
        itemid_a.add(itemid);
        price_a.add(price);
        save_price_a.add(save_price);
        description_a.add(description);
        off_price_a.add(off_price);
        img_url_a.add(img_url);
        QTY.add(qty);
        NAME.add(product_name);
        return true;
    }


}
