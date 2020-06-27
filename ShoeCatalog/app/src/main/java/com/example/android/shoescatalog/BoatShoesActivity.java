package com.example.android.shoescatalog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class BoatShoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boat_shoes);

        ArrayList<Product> products = new ArrayList<>();

//        Adding all products to the list
        products.add(new Product("Puma Fettle Mesh Running Shoe For Men","Blue",4.2F,
                5676,1649,2999,45,
                "No Cost EMI",R.drawable.cover, false));
        products.add(new Product("Puma Fettle Mesh Running Shoe For Men","Blue",4.2F,
                5676,1649,2999,45,
                "No Cost EMI",R.drawable.cover, true));
        products.add(new Product("Puma Fettle Mesh Running Shoe For Men","Blue",4.2F,
                5676,1649,2999,45,
                "No Cost EMI",R.drawable.cover, true));
        products.add(new Product("Puma Fettle Mesh Running Shoe For Men","Blue",4.2F,
                5676,1649,2999,45,
                "No Cost EMI",R.drawable.cover, false));
        products.add(new Product("Puma Fettle Mesh Running Shoe For Men","Blue",4.2F,
                5676,1649,2999,45,
                "No Cost EMI",R.drawable.cover, false));
        products.add(new Product("Puma Fettle Mesh Running Shoe For Men","Blue",4.2F,
                5676,1649,2999,45,
                "No Cost EMI",R.drawable.cover, false));
        products.add(new Product("Puma Fettle Mesh Running Shoe For Men","Blue",4.2F,
                5676,1649,2999,45,
                "No Cost EMI",R.drawable.cover, false));
        products.add(new Product("Puma Fettle Mesh Running Shoe For Men","Blue",4.2F,
                5676,1649,2999,45,
                "No Cost EMI",R.drawable.cover, false));

//        creating ProductAdapter instance
        ProductAdapter adapter = new ProductAdapter(this,products);

        ListView listView = (ListView) findViewById(R.id.boat_shoe_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BoatShoesActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
