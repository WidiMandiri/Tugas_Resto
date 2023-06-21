package com.widi.tugasresto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

// NIM : 10120064
// NAMA : Widi Malikul Mulky
// KELAS : IF-2
public class IntroActivity extends AppCompatActivity implements View.OnClickListener, TabLayout.OnTabSelectedListener {

    private ViewPager screenPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private TabLayout tabIndicator;
    private Button btnNext;
    private Button btnGetStarted;
    private Animation btnAnim;
    private TextView tvSkip;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        setContentView(R.layout.activity_intro);
        getSupportActionBar().hide();

        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);
        tvSkip = findViewById(R.id.tv_skip);

        final List<ScreenItem> mList = getScreenItems();

        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        tabIndicator.setupWithViewPager(screenPager);
        tabIndicator.addOnTabSelectedListener(this);

        btnNext.setOnClickListener(this);
        btnGetStarted.setOnClickListener(this);
        tvSkip.setOnClickListener(this);
    }

    private List<ScreenItem> getScreenItems() {
        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem(getString(R.string.title1), getString(R.string.desc1), R.drawable.img1));
        mList.add(new ScreenItem(getString(R.string.title2), getString(R.string.desc2), R.drawable.img2));
        mList.add(new ScreenItem(getString(R.string.title3), getString(R.string.desc3), R.drawable.img3));
        return mList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                handleNextButtonClick();
                break;
            case R.id.btn_get_started:
            case R.id.tv_skip:
                startMainActivity();
                break;
        }
    }

    private void handleNextButtonClick() {
        int position = screenPager.getCurrentItem();
        if (position < introViewPagerAdapter.getCount() - 1) {
            screenPager.setCurrentItem(position + 1);
        }
        if (position == introViewPagerAdapter.getCount() - 2) {
            loadLastScreen();
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition() == introViewPagerAdapter.getCount() - 1) {
            loadLastScreen();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    private void startMainActivity() {
        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainActivity);
        finish();
    }

    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        btnGetStarted.startAnimation(btnAnim);
    }
}
