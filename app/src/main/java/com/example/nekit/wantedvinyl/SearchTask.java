package com.example.nekit.wantedvinyl;

import android.os.AsyncTask;

public class SearchTask extends AsyncTask<String, Integer, String> {

    private static final Map map = MainActivity.getInstance().getMap();

    @Override
    protected String doInBackground(String... query) {
        map.clear();
        QueryResponse response;
        for(Place place: Place.getRegistered()){
            response = place.checkIfInStock(query[0]);
            if(!response.isSuccessful()) continue;
            map.addMarker(place.getName(), place.getLocation());
        }
        return null;
    }
}
