package com.example.mp_termproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class StoreKitchen extends AppCompatActivity {

    //Button water, milk, ice, coffee;
    Button btnDone;
    ToggleButton water, milk, ice, coffee;
    String beverage="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_store_kitchen);

        Context context=getApplicationContext();

        /*water=(Button)findViewById(R.id.ingredient_water);
        milk=(Button)findViewById(R.id.ingredient_milk);
        ice=(Button)findViewById(R.id.ingredient_ice);
        coffee=(Button)findViewById(R.id.ingredient_coffee);*/
        /*boolean water_checked=false, milk_checked=false, ice_checked=false, coffee_checked=false;

        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                water.setBackgroundColor(ContextCompat.getColor(context, R.color.gray));
                if(water_checked)
                    water_checked=false;
                else
                    water_checked=true;
            }
        });
        milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                milk.setBackgroundColor(ContextCompat.getColor(context, R.color.gray));
                if(!milk_checked)
                    milk_checked=1;
                else
                    milk_checked=0;
            }
        });
        ice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ice.setBackgroundColor(ContextCompat.getColor(context, R.color.gray));
                if(!ice_checked)
                    ice_checked=1;
                else
                    ice_checked=0;
            }
        });
        coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coffee.setBackgroundColor(ContextCompat.getColor(context, R.color.gray));
                if(!coffee_checked)
                    coffee_checked=1;
                else
                    coffee_checked=0;
            }
        });*/

        btnDone=(Button)findViewById(R.id.done_button);
        water=(ToggleButton)findViewById(R.id.water_toggle);
        milk=(ToggleButton)findViewById(R.id.milk_toggle);
        ice=(ToggleButton)findViewById(R.id.ice_toggle);
        coffee=(ToggleButton)findViewById(R.id.coffee_toggle);

        boolean waterOn=water.isChecked(), milkOn= milk.isChecked(), iceOn=ice.isChecked(), coffeeOn=coffee.isChecked();

        if(coffeeOn){
            if (iceOn){
                if (waterOn)//coffee+ice+water
                    beverage="ICED_AMERICANO";
                else if (milkOn)//coffee+ice+milk
                    beverage="ICED_LATTE";
            }
            else if (waterOn)//coffee+water
                beverage="HOT_AMERICANO";
            else if (milkOn)//coffee+milk
                beverage="HOT_LATTE";
        }

        else if (iceOn) {
            if(waterOn)//ice+water
                beverage="ICED_WATER";
            else if (milkOn)//ice+milk
                beverage="ICED_MILK";
        }

        else if (waterOn)//water
            beverage="HOT_WATER";
        else if (milkOn)//milk
            beverage="HOT_MILK";


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoreHall.class);
                intent.putExtra("Beverage", beverage);

                startActivity(intent);
                finish();
            }
        });
    }

}