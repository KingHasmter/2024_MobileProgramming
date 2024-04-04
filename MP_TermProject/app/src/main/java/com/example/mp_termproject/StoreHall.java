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
    //손님 이미지
    ImageView clientImg;
    //손님 주문 대사
    TextView clientOrder;
    //눌렀을 때 kitchen.class로 가는 버튼
    Button moveKitchen;

    //Kitchen에서 Intent 받았는지 확인용 변수
    int getIntent=0;
    //무작위로 생성되는 손님의 주문
    int order=-1;
    //무작위로 생성되는 손님의 방문까지 걸리는 시간
    int time;
    //손님이 주문했던 내용
    int past_order;
    //제조음료의 재료 포함 여부
    Boolean waterOn, milkOn, coffeeOn, iceOn;
    //firstIntent는 kitchen에서 데이터 받아오는 인텐트, secondIntent는 kitchen으로 데이터 보내고 화면 전환하는 인텐트
    Intent firstIntent, secondIntent;
    int score_percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_store_hall);

        clientImg=(ImageView)findViewById(R.id.client);
        clientOrder=(TextView)findViewById(R.id.Order);
        moveKitchen=(Button)findViewById(R.id.Make);


        Intent firstIntent = getIntent();
        getIntent = firstIntent.getIntExtra("intent_index", 0);
        past_order = firstIntent.getIntExtra("order_index", -1);

        if(getIntent==1) {
            if(past_order==0) clientOrder.setText(R.string.order0);
            else if(past_order==1) clientOrder.setText(R.string.order1);
            showClient(past_order, 0, getIntent, clientImg, clientOrder, moveKitchen);

            waterOn = firstIntent.getBooleanExtra("is_waterOn", false);
            milkOn = firstIntent.getBooleanExtra("is_milkOn", false);
            coffeeOn = firstIntent.getBooleanExtra("is_coffeeOn", false);
            iceOn = firstIntent.getBooleanExtra("is_iceOn", false);

            boolean answer_ice = true;
            boolean answer_water = true;
            boolean answer_coffee = true;
            boolean answer_milk = true;

            // 주문 확인하는 코드.
            if(past_order == 0 ){
                answer_ice = true;
                answer_water = true;
                answer_coffee = true;
                answer_milk = false;
            }
            else if(past_order == 1 ){
                answer_ice = false;
                answer_water = true;
                answer_coffee = true;
                answer_milk = false;
            }
            else if (past_order == 2) {
                answer_ice = true;
                answer_water = false;
                answer_coffee = true;
                answer_milk = true;
            }
            else if (past_order == 3) {
                answer_ice = false;
                answer_water = false;
                answer_coffee = true;
                answer_milk = true;
            }

            // 비교하는 코드.
            score_percentage = 100;
            if (answer_ice != iceOn) score_percentage -= 25;
            if (answer_water != waterOn) score_percentage -= 25;
            if (answer_coffee != coffeeOn) score_percentage -= 25;
            if (answer_milk != milkOn) score_percentage -= 25;

            if(score_percentage==100) clientOrder.setText("Perfect!");
            else if(score_percentage==75) clientOrder.setText("Good, have a good one.");
            else if(score_percentage==50) clientOrder.setText("Thanks.");
            else if(score_percentage==25) clientOrder.setText("It goes something wrong...");
            else clientOrder.setText("What the hell is it?!");
        }

        if(getIntent!=1) {
            Random random = new Random();
            time = random.nextInt(10000);
            if(getIntent==1)
                time=0;

            order = random.nextInt(2);
            if(order==0) clientOrder.setText(R.string.order0);
            else if (order == 1) clientOrder.setText(R.string.order1);

            showClient(order, time, getIntent, clientImg, clientOrder, moveKitchen);
        }

        moveKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(getApplicationContext(), StoreKitchen.class);
                secondIntent.putExtra("order_idx", order);
                startActivity(secondIntent);
            }
        });



        System.out.println("testline");

    }

    private void showClient(Integer order, Integer time, Integer getIntent, ImageView clientImg, TextView clientOrder, Button moveKitchen) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Random random=new Random();

                clientImg.setVisibility(View.VISIBLE);
                clientOrder.setVisibility(View.VISIBLE);
            }
        }, time);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(getIntent!=1) moveKitchen.setVisibility(View.VISIBLE);
            }
        }, time+1000);

    }
}