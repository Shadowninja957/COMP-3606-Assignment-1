package com.example.productordering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.HashSet;

public class OrderingActivity extends AppCompatActivity {

    HashSet<String> myList;
    RadioGroup radioGroup;
    RadioGroup radioGroup_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering);
        if (savedInstanceState != null) {
            myList = new HashSet<String>(savedInstanceState.getStringArrayList("myList"));

        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {


    }

    public void onRadioButtonClicked(View view) {
         RadioGroup radioGroup = ((RadioGroup) findViewById(R.id.radio_group_flavours));
         int id = radioGroup.getCheckedRadioButtonId();
         switch (id){
             case R.id.radio_chocolate:


         }
    }

    int getCheckedRadioButtonID(){
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group_flavours);
        int id = radioGroup.getCheckedRadioButtonId();
        if (id == -1)
        {

        }
        else{
            RadioButton radioButton = findViewById(id);
        }

    }
}