package com.example.carlos.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class stockAlcohol extends AppCompatActivity {

    private List<Alcohol> alcoholList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AlcoholAdapter aAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_alcohol);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        aAdapter = new AlcoholAdapter(alcoholList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        /*recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = alcoholList.get(position);
                Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "Long click", Toast.LENGTH_SHORT).show();
            }
        }));*/

        recyclerView.setAdapter(aAdapter);

        prepareData();
    }

    private void prepareData(){
        Alcohol alcohol = new Alcohol("Vodka",1000,15.32);
        alcoholList.add(alcohol);

        alcohol = new Alcohol("JB",1000,7.66);
        alcoholList.add(alcohol);

        alcohol = new Alcohol("Rum",500,9.50);
        alcoholList.add(alcohol);

        alcohol = new Alcohol("Coke",1000,2.05);
        alcoholList.add(alcohol);

        alcohol = new Alcohol("Fanta",1000,2.17);
        alcoholList.add(alcohol);
    }
}
