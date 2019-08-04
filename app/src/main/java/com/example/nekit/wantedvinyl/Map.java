package com.example.nekit.wantedvinyl;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Handler;
import android.os.Looper;

public class Map implements OnMapReadyCallback{

    private static final MainActivity app = MainActivity.getInstance();
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private static final double MSK_LATITUDE = 55.75222;
    private static final double MSK_LONGITUDE = 37.61556;

    private GoogleMap handle;

    private boolean checkMap(){
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(app);
        if (result!=ConnectionResult.SUCCESS){
            if (googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(app, result,0).show();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        handle = googleMap;
        LatLng Moscow = new LatLng(MSK_LATITUDE, MSK_LONGITUDE);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(Moscow).zoom(10).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        handle.animateCamera(cameraUpdate);
    }

    public void addMarker(final String title, final LatLng location){
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                handle.addMarker(new MarkerOptions().position(location).title(title));
            }
        };
        mainHandler.post(myRunnable);
    }

    public void moveCamera(final double latitude, final double longtitude, final float zoom){
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                handle.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longtitude), zoom));
            }
        };
        mainHandler.post(myRunnable);
    }

    public void clear(){
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                handle.clear();
            }
        };
        mainHandler.post(myRunnable);
    }
}
