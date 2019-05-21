package tech.iwish.ONhome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.view.Gravity;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;
import java.util.HashMap;

import tech.iwish.ONhome.UserManager.UserSessionManager;
import tech.iwish.ONhome.fragments.CategoryFragment;
import tech.iwish.ONhome.fragments.HomeFragment;
import tech.iwish.fonticons.FontIcon;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FontIcon fontIcon;
    int[] sampleImages = {R.drawable.slide4, R.drawable.slide4, R.drawable.slide4, R.drawable.slide4, R.drawable.slide4};
    CarouselView carouselView;

    public static ArrayList<Integer> Cart_Count = new ArrayList<>();


    static TextView Cartview;

    RelativeLayout Cart_Bucket;
    // User Session Manager Class
    UserSessionManager session;
    Button category_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Cartview = (TextView) findViewById(R.id.cart_count_textview);
        Cart_Bucket = (RelativeLayout) findViewById(R.id.cart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().hide();

        Cart_Bucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });
        category_btn = (Button) findViewById(R.id.category);
        category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Hwllo", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, new CategoryFragment())
                        .commit();
            }
        });

        fontIcon = (FontIcon) findViewById(R.id.bars);
        fontIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        session = new UserSessionManager(getApplicationContext());
        //session.checkLogin();
        HashMap<String, String> user_data =  session.getUserDetails();
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.header_bar_user_name);
        navUsername.setText(user_data.get("email"));


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new HomeFragment())
                        .commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.Categories) {
            Toast.makeText(this, "Hello111", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.MyOrders) {
            if(session.isUserLoggedIn()==true){
                Toast.makeText(HomeActivity.this, "Hellow My Friend", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, Myorders.class);
                startActivity(intent);
            }
            else if(session.isUserLoggedIn()==false){
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);            }

        }
        else if(id ==R.id.MyAccount){
            if(session.isUserLoggedIn()==true){
                Toast.makeText(HomeActivity.this, "Hellow Mr.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, MyAccount.class);
                startActivity(intent);
            }
            else if(session.isUserLoggedIn()==false){
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        }
        else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
