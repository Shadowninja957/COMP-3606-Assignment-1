package com.example.productordering;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class CompleteOrderActivity extends AppCompatActivity {

    public static final String EXTRA_ORDERS = "Orders";
    public static final String filename = "order_file.ser";

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

            // Serialization
            try
            {

                FileOutputStream file = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(file);

                for (Order order : orders) {
                    list_orders.append(order.toString());
                    list_orders.append("\n");

                    out.writeObject(orders);
                }

                out.close();
                file.close();

                Context context = getApplicationContext();
                CharSequence text = "Object has been serialized";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }

            catch(IOException ex)
            {
                Context context = getApplicationContext();
                CharSequence text = "IOException is caught";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }


        textView.setText(list_orders.toString());
    }


    public void onSendOrder(View view){



        TextView textView = (TextView) findViewById(R.id.completed_order);
        String text = textView.getText().toString();

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);


    }

}