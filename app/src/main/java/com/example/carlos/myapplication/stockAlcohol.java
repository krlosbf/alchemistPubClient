package com.example.carlos.myapplication;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class stockAlcohol extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener{

    private List<Alcohol> alcoholList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AlcoholAdapter aAdapter;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_alcohol);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        coordinatorLayout = findViewById(R.id.coordinator_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        aAdapter = new AlcoholAdapter(alcoholList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Separador entre filas

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // Evento click en fila

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Alcohol alcohol = alcoholList.get(position);
                Toast.makeText(getApplicationContext(), alcohol.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        // adding item touch helper
        // only ItemTouchHelper.LEFT added to detect Right to Left swipe
        // if you want both Right -> Left and Left -> Right
        // add pass ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT as param
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(aAdapter);
        prepareData();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof AlcoholAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String title = alcoholList.get(viewHolder.getAdapterPosition()).getName();

            // backup of removed Movie for undo purpose
            final Alcohol deletedAlcohol = alcoholList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the Alcohol from recycler view
            aAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, title + " removed from stock!", Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    aAdapter.restoreItem(deletedAlcohol, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
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
