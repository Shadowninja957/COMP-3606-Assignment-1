package com.example.productordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        Intent intent = getIntent();
        String past_Orders = intent.getStringExtra(CompleteOrderActivity.PAST_ORDERS);
        TextView textView = (TextView) findViewById(R.id.display_order);
        textView.setText(past_Orders);

    }

    public void onHome(View view){

        Context context = getApplicationContext();
        CharSequence text = "All Past Orders";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

}