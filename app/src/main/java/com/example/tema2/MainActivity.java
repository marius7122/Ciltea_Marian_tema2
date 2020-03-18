package com.example.tema2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.my_recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // TODO: Get dataset
        List<Review> dataset = new ArrayList<Review>();
        dataset.add( new Review("Ciltea Marian", "3") );
        dataset.add( new Review("Aluculesei Anca", "5") );
        dataset.add( new Review("Raspberry Pi", "4") );
        dataset.add( new Review("Ariana Grande", "2") );
        dataset.add( new Review("Nea Jan", "5") );
        dataset.add( new Review("Ciltea Marian", "3") );
        dataset.add( new Review("Aluculesei Anca", "5") );
        dataset.add( new Review("Raspberry Pi", "4") );
        dataset.add( new Review("Ariana Grande", "2") );
        dataset.add( new Review("Nea Jan", "5") );

        mRecyclerView.setAdapter(new MyAdapter(dataset));
    }
}
