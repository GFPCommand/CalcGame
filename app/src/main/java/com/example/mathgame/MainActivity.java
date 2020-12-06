package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startGame, gameRules, thirdButton, backBut;
    private Intent transition;

    public static int different;
    private int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();

        transition = new Intent(this, GameActivity.class);

        check = 0;

        startGame  = findViewById(R.id.startGameBut);
        gameRules  = findViewById(R.id.gameRulesBut);

        thirdButton = findViewById(R.id.ThirdButton);
        backBut = findViewById(R.id.backBut);

        startGame.setOnClickListener(v -> {
            if (check == 0) {
                check = 1;
                startGame.setText("Easy");
                gameRules.setText("Medium");
                thirdButton.setText("Hard");

                thirdButton.setVisibility(View.VISIBLE);
                backBut.setVisibility(View.VISIBLE);
            } else if (check == 1){
                different = 1;
                transition.putExtra("diff", different);
                startActivity(transition);
                finish();
            }
        });

        gameRules.setOnClickListener(v -> {
            if (check == 0) {
                startActivity(new Intent(MainActivity.this, GameRulesActivity.class));
                finish();
            } else if (check == 1){
                different = 2;
                transition.putExtra("diff", different);
                startActivity(transition);
                finish();
            }
        });

        thirdButton.setOnClickListener(v -> {
            if (check == 1){
                different = 3;
                transition.putExtra("diff", different);
                startActivity(transition);
                finish();
            }
        });

        backBut.setOnClickListener(v -> {
            if (check == 1){
                check = 0;

                startGame.setText("Start Game");
                gameRules.setText("Game Rules");

                thirdButton.setVisibility(View.GONE);
                backBut.setVisibility(View.GONE);
            }
        });
    }
}