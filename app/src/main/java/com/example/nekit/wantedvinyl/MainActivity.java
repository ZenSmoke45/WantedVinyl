package com.example.nekit.wantedvinyl;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends FragmentActivity {

    private static MainActivity instance;
    private Map map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance = this;
        map = new Map();

        Intent searchIntent = new Intent(this, SearchActivity.class);
        startActivity(searchIntent);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(map);

        new SoundBarrier();
    }


    public static MainActivity getInstance() {
        return instance;
    }

    public Map getMap() {
        return map;
    }
}
