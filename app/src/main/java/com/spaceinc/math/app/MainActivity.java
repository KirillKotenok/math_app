package com.spaceinc.math.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.spaceinc.app.R;
import com.spaceinc.math.app.fragment.GameFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private GameFragment gameFragment;
    private SharedPreferences sharedPreferences;
    private FrameLayout frameLayout;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.main_container);
        frameLayout.setOnClickListener(this);
        gameFragment = new GameFragment();
        bundle = new Bundle();
        sharedPreferences = getSharedPreferences(MainActivity.class.getCanonicalName(), MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        loadFragment(gameFragment);
    }

    public void loadFragment(Fragment fragment) {
        fragment.setArguments(bundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }

}