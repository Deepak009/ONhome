package tech.iwish.ONhome.helper;
/**
 * Created by iwish on 5/23/2017.
 * http://192.168.1.222/grocery_website/admin/android_process/check_user.php
 */
public class Constants {

    public static String STARTUP_SCREEN_SF = "startup_screen_sf";
    //public static String URL = "http://192.168.1.202/";
    // public static String URL = "http://192.168.219.2/";
    public static String URL = "http://192.168.43.109/";
    public static String BaseUrl = URL+"grocery_website/";
    public static String SUBFOLDER = "grocery_website/android_process/";

    //File Naming
    public static String Check = URL+SUBFOLDER+"check_user.php";
    public static String Getslides = URL+SUBFOLDER+"getslides.php";
    public static String OrderResive = URL+SUBFOLDER+"OrderResive.php";

    public static String getproductlist = URL+SUBFOLDER+"product_list.php";
    public static String GET_ITEM_DETAIL = URL+SUBFOLDER+"item_detail.php";
    public static String GET_Grocery_itmes = URL+SUBFOLDER+"get_grocery_products.php";
    public static String GetBeverages_url = URL+SUBFOLDER+"GetBeverages_items.php";
    public static String GetBiscuitsnecks = URL+SUBFOLDER+"GetBiscuitsnecks.php";


    public static String GetFoodsBreakfast = URL+SUBFOLDER+"GetFoodsBreakfast.php";
    public static String GetHouseholdNeeds_items = URL+SUBFOLDER+"GetHouseholdNeeds_items.php";
    public static String GetPersonalcare_items = URL+SUBFOLDER+"GetPersonalcare_items.php";
    public static String GetSoap = URL+SUBFOLDER+"GetSoap.php";
    public static String GetPatanjaliProducts_items = URL+SUBFOLDER+"GetPatanjaliProducts_items.php";


    public static String Myorders_url = URL+SUBFOLDER+"Myorders.php";

    public static String SignupUrl = URL+SUBFOLDER+"usersignup.php";

    public static String SearchItem = URL+SUBFOLDER+"SearchItems.php";
    public static String GetCategories = URL+SUBFOLDER+"GetCategories.php";






       /* //Connection Server Start Here
        //Log.e("test URl",LOGINCHECK);]
        ConnectionServer connectionServer = new ConnectionServer();
                    connectionServer.set_url(Check);
                    connectionServer.requestedMethod("POST");
        //connectionServer.buildParameter("username",username);
        //connectionServer.buildParameter("password",password);
                    connectionServer.execute(new ConnectionServer.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                Log.e("Server Response",output);
                JSONObject data= null;

            }
        });*/

}
