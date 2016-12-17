package com.example.dolphin.kichuna;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Toolbar toolbar;
    Toolbar toolbar;
    TabLayout tablayout;
    ViewPager viewpager;
    ViewPagerAdapter viewpageradapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.tToolbar);
        setSupportActionBar(toolbar);
        tablayout=(TabLayout)findViewById(R.id.tablayout);
        viewpager=(ViewPager)findViewById(R.id.viewPager);
        viewpageradapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewpageradapter.addFragments(new General(),"General");
        viewpageradapter.addFragments(new Contacts(),"Contacts");
        viewpager.setAdapter(viewpageradapter);
        tablayout.setupWithViewPager(viewpager);

    }
}
