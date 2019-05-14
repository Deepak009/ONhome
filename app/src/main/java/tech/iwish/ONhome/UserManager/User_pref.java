package tech.iwish.ONhome.UserManager;


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import static android.content.Context.MODE_PRIVATE;

public class User_pref extends AppCompatActivity {

    SharedPreferences.Editor editor = getSharedPreferences("UserData", MODE_PRIVATE).edit();

    public boolean checkuser(){

        return true;
    }
    public boolean login(){

        return true;

    }

    public boolean logout(){

        return true;

    }
}
