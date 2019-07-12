package com.example.hitam_toastmasters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView membersCard, momCard, ahCounterCard, timerCard, grammarianCard, infoCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        membersCard = findViewById(R.id.membersCard);
        momCard = findViewById(R.id.momCard);
        ahCounterCard = findViewById(R.id.ahCounterCard);
        timerCard = findViewById(R.id.timerCard);
        grammarianCard = findViewById(R.id.grammarianCard);
        infoCard = findViewById(R.id.infoCard);

        membersCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MembersList.class));
            }
        });

        momCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MeetingsList.class));
            }
        });

        ahCounterCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AhCounterPage.class));
            }
        });

        timerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TimerPage.class));
            }
        });

        grammarianCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Grammarian.class));
            }
        });

        infoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InfoPage.class));
            }
        });
    }
}