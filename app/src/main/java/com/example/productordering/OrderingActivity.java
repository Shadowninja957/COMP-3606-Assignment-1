package com.example.productordering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class OrderingActivity extends AppCompatActivity {

    private ArrayList<Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering);

        if (savedInstanceState != null) {
            Bundle bundle = savedInstanceState.getBundle("Orders");
            for (String key : bundle.keySet()) {
                orders.add((Order) bundle.getSerializable(key));
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Bundle bundle = new Bundle();
        for (int i = 0; i<orders.size(); i++) {
            bundle.putSerializable(CompleteOrderActivity.EXTRA_ORDERS+i, orders.get(i));
        }
        savedInstanceState.putBundle("Orders", bundle);
    }

    public void onAddToCart(View view){

        // add flavour
        RadioGroup flavour = (RadioGroup) findViewById(R.id.radio_group_flavours);
        int id = flavour.getCheckedRadioButtonId();
        if (id == -1){
            Context context = getApplicationContext();
            CharSequence text = "Flavour not selected";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }


        // Toppings
        boolean checked;
        ArrayList<String> toppings = new ArrayList<>();
        CheckBox checkBox;

        checkBox = (CheckBox) findViewById(R.id.toppings_chocolate_sprinkles);
        checked = checkBox.isChecked();
                if (checked){
                    toppings.add(checkBox.getText().toString());
                }

        checkBox = (CheckBox) findViewById(R.id.toppings_gummy_bears);
        checked = checkBox.isChecked();
                if (checked){
                    toppings.add(checkBox.getText().toString());
                }

        checkBox = (CheckBox) findViewById(R.id.toppings_oreos);
        checked = checkBox.isChecked();
        if (checked){
            toppings.add(checkBox.getText().toString());
        }

        checkBox = (CheckBox) findViewById(R.id.toppings_sprinkles);
        checked = checkBox.isChecked();
        if (checked){
            toppings.add(checkBox.getText().toString());
        }

        checkBox = (CheckBox) findViewById(R.id.toppings_whipped_cream);
        checked = checkBox.isChecked();
        if (checked){
            toppings.add(checkBox.getText().toString());
        }

        // Syrup
        Switch switch_syrup = (Switch) findViewById(R.id.syrup);
        boolean syrup_yes = switch_syrup.isChecked();
        String syrup = "No";
        if (syrup_yes){
            syrup = "Yes";
        }


        // Scoops
        RadioGroup scoop = (RadioGroup) findViewById(R.id.radio_group_scoops);
        int scoop_id = scoop.getCheckedRadioButtonId();
        if (scoop_id == -1){
            Context context = getApplicationContext();
            CharSequence text = "scoops not selected";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if (id != -1){

            RadioButton ice_cream_flavour = findViewById(id);
            RadioButton ice_cream_scoop = findViewById(scoop_id);
            String ice_flavour = ice_cream_flavour.getText().toString();
            String ice_scoop = ice_cream_scoop.getText().toString();

            Order order = new Order(ice_flavour, toppings, ice_scoop, syrup);
            orders.add(order);

            Context context = getApplicationContext();
            CharSequence text = "Order Added";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }

    }

    public void onCompleteOrder(View view){

        Intent intent = new Intent(this, CompleteOrderActivity.class);
        Bundle bundle = new Bundle();
        Context context = getApplicationContext();

        for (int i = 0; i<orders.size(); i++) {
            bundle.putSerializable(CompleteOrderActivity.EXTRA_ORDERS+i, orders.get(i));
        }

        // Serialization
        try
        {
            FileOutputStream file = context.openFileOutput(CompleteOrderActivity.filename, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(file);

            for (Order order : orders) {
                out.writeObject(order);
            }

            out.close();
            file.close();

          /*  CharSequence text = "Object has been serialized";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show(); */

        }

        catch(IOException ex)
        {
            CharSequence text = "IOException is caught";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        intent.putExtras(bundle);
        startActivity(intent);

    }


}