package com.erporate.visitjogja.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.erporate.visitjogja.Adapter.IntroViewPagerAdapter;
import com.erporate.visitjogja.MainActivity;
import com.erporate.visitjogja.Model.Intro;
import com.erporate.visitjogja.Model.ScreenItem;
import com.erporate.visitjogja.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SliderActivity extends AppCompatActivity {


    private ViewPager screenPager;
    private TabLayout tabIndicator;
    private Button btnNext;
    private int position = 0 ;
    private Button btnGetStarted;
    private Animation btnAnim ;
    private TextView tvSkip;
    private Intro intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        intro = new Intro(this);


        if (intro.getrestorePrefData()) {

            Intent pilihfoto = new Intent(getApplicationContext(),SplashActivity.class );
            startActivity(pilihfoto);
            finish();

        }


        // ini views
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        tvSkip = findViewById(R.id.tv_skip);

        // fill list screen


        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Visit Jogja","Visit Jogja merupakan aplikasi yang berisi tentang lokasi pariwisata yang ada di Jogja",R.drawable.tugu));
        mList.add(new ScreenItem("Lokasi Bersejarah","Jogja memiliki banyak lokasi bersejarah yang menarik untuk dikunjungi",R.drawable.candi));
        mList.add(new ScreenItem("Map","Aplikasi Visit Jogja juga memberikan informasi lokasi pariwisata",R.drawable.lokasi));

        // setup viewpager
        screenPager =findViewById(R.id.screen_viewpager);
        IntroViewPagerAdapter introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // setup tablayout with viewpager

        tabIndicator.setupWithViewPager(screenPager);

        // next button click Listner

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                    position++;
                    screenPager.setCurrentItem(position);


                }

                if (position == mList.size()-1) { // when we rech to the last screen

                    // TODO : show the GETSTARTED Button and hide the indicator and the next button

                    loaddLastScreen();


                }

            }
        });





        // tablayout add change listener


        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size()-1) {

                    loaddLastScreen();

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        // Get Started button click listener

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open main activity

                Intent pilihfoto = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(pilihfoto);
                // also we need to save a boolean value to storage so next time when the user run the app
                // we could know that he is already checked the intro screen activity
                // i'm going to use shared preferences to that process
                intro.setrestorePrefData(true);
                finish();


            }
        });





        // skip button click listener

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenPager.setCurrentItem(mList.size());
            }
        });

    }





    // show the GETSTARTED Button and hide the indicator and the next button
    private void loaddLastScreen() {

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        // TODO : ADD an animation the getstarted button
        // setup animation
        btnGetStarted.setAnimation(btnAnim);



    }


}
