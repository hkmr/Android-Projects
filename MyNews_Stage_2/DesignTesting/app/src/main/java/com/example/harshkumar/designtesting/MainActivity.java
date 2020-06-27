package com.example.harshkumar.designtesting;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectableViewHolder.OnItemSelectedListener{

    RecyclerView recyclerView;
    SelectableAdapter adapter;
    List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) this.findViewById(R.id.selection_list);
        recyclerView.setLayoutManager(layoutManager);
        List<Item> selectableItems = generateItems();
        adapter = new SelectableAdapter(this,selectableItems,true);
        recyclerView.setAdapter(adapter);
    }

    public List<Item> generateItems(){

        List<Item> selectableItems = new ArrayList<>();
        selectableItems.add(new Item("cem","karaca"));
        selectableItems.add(new Item("sezen","aksu"));
        selectableItems.add(new Item("baris","manco"));
        selectableItems.add(new Item("fourthName","fourthSurName"));
        selectableItems.add(new Item("FifthName","FifthSurName"));
        selectableItems.add(new Item("SixthName","SixthSurName"));
        selectableItems.add(new Item("SeventhName","SeventhSurName"));
        selectableItems.add(new Item("EightName","EightSurName"));
        selectableItems.add(new Item("NinthName","NineSurName"));
        selectableItems.add(new Item("TenthName","TenthSurName"));

        return selectableItems;
    }

    @Override
    public void onItemSelected(SelectableItem selectableItem) {

        List<Item> selectedItems = adapter.getSelectedItems();

        StringBuilder names = new StringBuilder();
        for(Item item : selectedItems){
            names.append(item.getName()+",");
        }


        Snackbar.make(recyclerView,"Selected item is "+names.toString()+
                " Totally  selectem item count is "+selectedItems.size(),Snackbar.LENGTH_LONG).show();
    }
}

