package com.example.productordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DisplayOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_order);

        Order object1 =  null;

        // Deserialization
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(CompleteOrderActivity.filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            object1 = (Order) in.readObject();

            in.close();
            file.close();

            TextView textView = (TextView) findViewById(R.id.completed_order);
            StringBuilder list_orders = new StringBuilder();


         /*   list_orders.append(object1.toString());
            list_orders.append("\n");

            textView.setText(list_orders); */

            Context context = getApplicationContext();
            CharSequence text = "Object has been deserialized";
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

        catch(ClassNotFoundException ex)
        {
            Context context = getApplicationContext();
            CharSequence text = "ClassNotFoundException is caught";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }


}