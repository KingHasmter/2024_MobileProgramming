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

    Button btnDone;
    ToggleButton water, milk, ice, coffee;
    String beverage="";

    // boolean data for check order completion with result
    boolean waterOn, milkOn, iceOn, coffeeOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_store_kitchen);

        Context context=getApplicationContext();


        btnDone=(Button)findViewById(R.id.done_button);
        water=(ToggleButton)findViewById(R.id.water_toggle);
        milk=(ToggleButton)findViewById(R.id.milk_toggle);
        ice=(ToggleButton)findViewById(R.id.ice_toggle);
        coffee=(ToggleButton)findViewById(R.id.coffee_toggle);




        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                waterOn=water.isChecked();
                milkOn= milk.isChecked();
                iceOn=ice.isChecked();
                coffeeOn=coffee.isChecked();

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

                Intent intent = new Intent(context, StoreHall.class);
                intent.putExtra("is_waterOn", waterOn);
                intent.putExtra("is_coffeeOn", coffeeOn);
                intent.putExtra("is_iceOn", iceOn);
                intent.putExtra("is_milkOn", milkOn);
                intent.putExtra("intent_index", 1);
                startActivity(intent);
                finish();
            }
        });
    }

}