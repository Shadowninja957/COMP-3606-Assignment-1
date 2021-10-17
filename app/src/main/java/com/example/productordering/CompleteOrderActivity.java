package com.example.productordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class CompleteOrderActivity extends AppCompatActivity {

    public static final String EXTRA_ORDERS = "Orders";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_order);

        //Get the orders of the customer
        Intent intent = getIntent();
        ArrayList<Order> orders = new ArrayList<>();
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();

        for (String key : bundle.keySet()) {
            orders.add((Order) bundle.getSerializable(key));
        }

        TextView textView = (TextView) findViewById(R.id.completed_order);
        StringBuilder list_orders = new StringBuilder();

        for (Order order : orders) {
            list_orders.append(order.toString());
            list_orders.append("\n");
        }

        textView.setText(list_orders.toString());
    }

}