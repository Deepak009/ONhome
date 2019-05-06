package tech.iwish.ONhome.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import tech.iwish.ONhome.R;
import tech.iwish.ONhome.adaptors.ImageAdaptors;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    ImageAdaptors imageAdaptors;

    ArrayList<String> sliders_urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = (ViewPager)findViewById(R.id.viewPager);
        imageAdaptors = new ImageAdaptors(MainActivity.this);
        viewPager.setAdapter(imageAdaptors);
    }


}
