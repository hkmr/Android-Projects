package com.example.android.coffeorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int items;
    private double itemPrice;
    boolean isWipped;
    double totalPrice;
    String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.items = 1;
        this.itemPrice = 10.0;

    }

    public void setQuantity(int num){
        TextView tv = (TextView) findViewById(R.id.item_quantity);
        tv.setText(num);
    }

    public void setViews(){
        TextView itemQuan = (TextView) findViewById(R.id.item_quantity);
        itemQuan.setText(String.valueOf(this.items));
    }

    public void decrement(View view){

        if(this.items > 1 ){
            this.items--;
        }
        setViews();
    }

    public void increment(View view){

        this.items++;
        setViews();
    }

    public void setUserName(){
        EditText name = (EditText) findViewById(R.id.user_name);
        userName = name.getText().toString();
    }

    public void submitOrder(View view){

        totalPrice = this.items * this.itemPrice;

        CheckBox cream_check = (CheckBox) findViewById(R.id.cream_checkbox);
        isWipped = cream_check.isChecked();

        if(isWipped){
            totalPrice += 2* items;
        }

        TextView priceView = (TextView) findViewById(R.id.total_price);
        priceView.setText(String.valueOf(totalPrice));

        orderSummary();

    }

    public void orderSummary(){

        String summary = "Coffe order:" +
                "Name = "+ userName +
                "Cream Wipped = " + isWipped +
                "Number of coffee = " + items +
                "order total = " + totalPrice ;

        TextView orderSum = (TextView) findViewById(R.id.order_summary);
        orderSum.setText(summary);

        orderSum.setVisibility(View.VISIBLE);

    }
}
