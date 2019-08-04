package com.example.nekit.wantedvinyl;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.TextView;

public class SearchActivity extends Activity {
    private static SearchActivity instance;

    private SearchView searchBar;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_main);

        instance = this;
        searchBar = findViewById(R.id.location);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new SearchTask().execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public static SearchActivity getInstance() {
        return instance;
    }
}
