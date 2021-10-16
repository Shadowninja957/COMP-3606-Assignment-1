package com.example.productordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CompleteOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_order);

    }
    public void checkedButton(View view){

    }
    public void checkButton(View view){
        openFinalActivity();
    }
    public void openFinalActivity(){
        Intent intent = new Intent(this, FinalOrderActivity.class);
        startActivity(intent);
    }
}