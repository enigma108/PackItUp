package com.android.packitup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ItemsFragment itemsFragment = new ItemsFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.frame_main, itemsFragment, null).commit();
    }
}
