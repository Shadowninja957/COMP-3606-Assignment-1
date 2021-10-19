package com.example.productordering;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CompleteOrderActivity extends AppCompatActivity {

    public static final String EXTRA_ORDERS = "Orders";
    public static final String filename = "order_file.dat";
    public static final String PAST_ORDERS = "Past_Orders";

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

        Context context = getApplicationContext();
        TextView textView = (TextView) findViewById(R.id.completed_order);
        StringBuilder list_orders = new StringBuilder();


            // Serialization
            try
            {

                FileOutputStream file = context.openFileOutput(filename, Context.MODE_PRIVATE);
                ObjectOutputStream out = new ObjectOutputStream(file);

                for (Order order : orders) {
                    list_orders.append(order.toString());
                    list_orders.append("\n");

                    out.writeObject(order);
                }

                out.close();
                file.close();

                CharSequence text = "Object has been serialized";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                textView.setText(list_orders.toString());
            }

            catch(IOException ex)
            {
                CharSequence text = "IOException is caught";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

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

    public void onDisplayOrder(View view){

        ArrayList<Order> orders = new ArrayList<>();
        Order object1 =  null;

        Context context = getApplicationContext();
        // Deserialization
        try
        {
            // Reading the object from a file
            FileInputStream file = context.openFileInput(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object

           while (file.available()!=0) {

               object1 = (Order) in.readObject();
               if (object1!=null) {
                   orders.add(object1);
               }
               else{
                   break;
               }
            }

            in.close();
            file.close();


            StringBuilder list_orders = new StringBuilder();

            for (Order order : orders) {
                list_orders.append(order.toString());
                list_orders.append("\n");
            }

            Intent intent = new Intent(this, DisplayOrderActivity.class);
            intent.putExtra(PAST_ORDERS, list_orders.toString());

            CharSequence text = "Object has been deserialized";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            startActivity(intent);

        }

        catch(IOException ex)
        {
            CharSequence text = "IOException is caught";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        catch(ClassNotFoundException ex)
        {
            CharSequence text = "ClassNotFoundException is caught";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

}