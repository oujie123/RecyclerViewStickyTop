package com.example.stickytop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Star> starList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new StarDecoration(this));
        recyclerView.setAdapter(new StarAdapter(starList));
    }

    private void init() {
        starList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 20; j++) {
                if (i % 3 == 0) {
                    starList.add(new Star("GS" + j, "GS系列"));
                } else if (i % 3 == 1){
                    starList.add(new Star("GM" + j, "GM系列"));
                } else {
                    starList.add(new Star("GA" + j, "GA系列"));
                }
            }
        }
    }
}
