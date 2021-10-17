package com.example.productordering;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering);
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

        }

    }
}