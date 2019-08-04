package com.example.nekit.wantedvinyl;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public abstract class Place {

    private static final ArrayList<Place> places = new ArrayList<>();

    private final String name, URL;
    private final LatLng location;

    public Place(String name, String URL, LatLng location){
        this.URL = URL;
        this.name = name;
        this.location = location;
        places.add(this);
    }

    public abstract QueryResponse checkIfInStock(String item);

    public String getName() {
        return name;
    }

    public String getURL() {
        return URL;
    }

    public LatLng getLocation() {
        return location;
    }

    public static ArrayList<Place> getRegistered(){
        return places;
    }
}
