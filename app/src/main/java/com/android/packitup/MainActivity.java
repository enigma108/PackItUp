package com.android.packitup;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_ITEMS_FRAGMENT = "items_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        ItemsFragment mFragment = (ItemsFragment) fm.findFragmentByTag(TAG_ITEMS_FRAGMENT);

        if(mFragment == null) {
            ItemsFragment itemsFragment = new ItemsFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_main, itemsFragment, TAG_ITEMS_FRAGMENT).commit();
        }
    }
}
