package com.example.mp_termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class StoreHall extends AppCompatActivity {

    ImageView clientImg;
    TextView clientOrder;
    Button moveKitchen;

    //Kitechen에서 Intent 받았는지 확인용 변수
    int getIntent=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_store_hall);

        clientImg=(ImageView)findViewById(R.id.client);
        clientOrder=(TextView)findViewById(R.id.Order);
        moveKitchen=(Button)findViewById(R.id.Make);

        Intent secondIntent = getIntent();
        final Boolean waterOn = secondIntent.getBooleanExtra("is_waterOn", false);
        final Boolean milkOn = secondIntent.getBooleanExtra("is_milkOn", false);
        final Boolean coffeeOn = secondIntent.getBooleanExtra("is_coffeeOn", false);
        final Boolean iceOn = secondIntent.getBooleanExtra("is_iceOn", false);

        if(getIntent!=1) {
            Random random = new Random();
            int time= random.nextInt(10000);
            showClient(time, clientImg, clientOrder, moveKitchen);
        }

        moveKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fisrtIntent = new Intent(getApplicationContext(), StoreKitchen.class);
                startActivity(fisrtIntent);
            }
        });

    }

    private void showClient(Integer time, ImageView clientImg, TextView clientOrder, Button moveKitchen) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Random random=new Random();

                int order = random.nextInt(2);
                if(order==0) clientOrder.setText(R.string.order0);
                else if (order == 1) clientOrder.setText(R.string.order1);

                clientImg.setVisibility(View.VISIBLE);
                clientOrder.setVisibility(View.VISIBLE);
            }
        }, time);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                moveKitchen.setVisibility(View.VISIBLE);
            }
        }, time+1000);

    }
}