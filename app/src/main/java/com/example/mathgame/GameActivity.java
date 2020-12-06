package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button backButton;
    private Button clear, check, minus, but1, but2, but3, but4, but5, but6, but7, but8, but9, but0;

    private TextView answer, question, scoreText, result;

    private Bundle getTransition;

    private int diff, score, operand1, operand2, operand3, operator1, operator2, answerNum;

    private String displayInput = "", answerText;
    private final int ADD_OPERATOR = 0, SUBTRACT_OPERATOR = 1,
            MULTIPLY_OPERATOR = 2, DIVIDE_OPERATOR = 3;
    private String[] operators = { "+", "-", "x", "/" };

    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();

        getTransition = getIntent().getExtras();
        if (getTransition != null){
            int level = getTransition.getInt("diff", -1);
            if (level >= 0){
                diff = level;
            }
        }

        score = 0;

        random = new Random();

        backButton = findViewById(R.id.backToMain);

        clear = findViewById(R.id.clearAnswerField);
        check = findViewById(R.id.checkAnswer);
        minus = findViewById(R.id.minus);

        but0 = findViewById(R.id.but0);
        but1 = findViewById(R.id.but1);
        but2 = findViewById(R.id.but2);
        but3 = findViewById(R.id.but3);
        but4 = findViewById(R.id.but4);
        but5 = findViewById(R.id.but5);
        but6 = findViewById(R.id.but6);
        but7 = findViewById(R.id.but7);
        but8 = findViewById(R.id.but8);
        but9 = findViewById(R.id.but9);

        answer = findViewById(R.id.answerField);
        question = findViewById(R.id.expression);
        scoreText = findViewById(R.id.score);
        result = findViewById(R.id.result);

        backButton.setOnClickListener(v -> {
            startActivity(new Intent(GameActivity.this, MainActivity.class));
            finish();
        });

        clear.setOnClickListener(this);
        check.setOnClickListener(this);
        minus.setOnClickListener(this);

        but0.setOnClickListener(this);
        but1.setOnClickListener(this);
        but2.setOnClickListener(this);
        but3.setOnClickListener(this);
        but4.setOnClickListener(this);
        but5.setOnClickListener(this);
        but6.setOnClickListener(this);
        but7.setOnClickListener(this);
        but8.setOnClickListener(this);
        but9.setOnClickListener(this);

        settingQuestion();
    }

    @Override
    public void onClick(View view) {

        Button but = (Button) view;

        String data = but.getText().toString();

        switch (data){
            case "C":
                answer.setText("");
                displayInput = "";
                break;
            case "Check":
                CheckAnswer();
                break;
            default:
                InputValues(data);
        }
    }

    private void InputValues(String input){
        switch (input){
            case "0":
                displayInput += "0";
                break;
            case "1":
                displayInput += "1";
                break;
            case "2":
                displayInput += "2";
                break;
            case "3":
                displayInput += "3";
                break;
            case "4":
                displayInput += "4";
                break;
            case "5":
                displayInput += "5";
                break;
            case "6":
                displayInput += "6";
                break;
            case "7":
                displayInput += "7";
                break;
            case "8":
                displayInput += "8";
                break;
            case "9":
                displayInput += "9";
                break;
            case "-":
                displayInput += "-";
                break;
        }
        answer.setText(displayInput);
    }

    private void CheckAnswer(){
        answerText = answer.getText().toString();

        if (answerText.equals(String.valueOf(answerNum))) {
            ++score;
            scoreText.setText("Score: " + score);
            result.setText("Right");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            result.setText("Wrong");
            score = 0;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            scoreText.setText("Score: " + score);
        }
        result.setText("");
        answer.setText("");
        displayInput = "";
        settingQuestion();
    }

    private void settingQuestion(){
        operator1 = random.nextInt(operators.length);
        operator2 = random.nextInt(operators.length);
        if (diff == 1){
            operand1 = random.nextInt(10);
            operand2 = random.nextInt(10);
            operand3 = random.nextInt(10);
        } else if (diff == 2){
            operand1 = random.nextInt(50);
            operand2 = random.nextInt(50);
            operand3 = random.nextInt(50);
        } else if (diff == 3){
            operand1 = random.nextInt(100);
            operand2 = random.nextInt(100);
            operand3 = random.nextInt(100);
        }


        if (operator1 == ADD_OPERATOR && operator2 == ADD_OPERATOR){//ADD - first
            answerNum = operand1 + operand2 + operand3;
        } else if (operator1 == ADD_OPERATOR && operator2 == SUBTRACT_OPERATOR){
            answerNum = operand1 + operand2 - operand3;
        } else if (operator1 == ADD_OPERATOR && operator2 == MULTIPLY_OPERATOR){
            answerNum = operand1 + operand2 * operand3;
        } else if (operator1 == ADD_OPERATOR && operator2 == DIVIDE_OPERATOR){
            if (operand3 == 0)
                answerNum = operand1;
            else answerNum = operand1 + operand2 / operand3;
        } else if (operator1 == SUBTRACT_OPERATOR && operator2 == ADD_OPERATOR){//SUBTRACT - first
            answerNum = operand1 - operand2 + operand3;
        } else if (operator1 == SUBTRACT_OPERATOR && operator2 == SUBTRACT_OPERATOR){
            answerNum = operand1 - operand2 - operand3;
        } else if (operator1 == SUBTRACT_OPERATOR && operator2 == MULTIPLY_OPERATOR){
            answerNum = operand1 - operand2 * operand3;
        } else if (operator1 == SUBTRACT_OPERATOR && operator2 == DIVIDE_OPERATOR){
            if (operand3 == 0)
                answerNum = operand1;
            else answerNum = operand1 - operand2 / operand3;
        } else if (operator1 == MULTIPLY_OPERATOR && operator2 == ADD_OPERATOR){//MULTIPLY - first
            answerNum = operand1 * operand2 + operand3;
        } else if (operator1 == MULTIPLY_OPERATOR && operator2 == SUBTRACT_OPERATOR){
            answerNum = operand1 * operand2 - operand3;
        } else if (operator1 == MULTIPLY_OPERATOR && operator2 == MULTIPLY_OPERATOR){
            answerNum = operand1 * operand2 * operand3;
        } else if (operator1 == MULTIPLY_OPERATOR && operator2 == DIVIDE_OPERATOR){
            if (operand3 == 0)
                answerNum = 0;
            else answerNum = operand1 * operand2 / operand3;
        } else if (operator1 == DIVIDE_OPERATOR && operator2 == ADD_OPERATOR){//DIVIDE - first
            if (operand2 == 0 || operand3 == 0)
                answerNum = operand3;
            else answerNum = operand1 / operand2 + operand3;
        } else if (operator1 == DIVIDE_OPERATOR && operator2 == SUBTRACT_OPERATOR){
            if (operand2 == 0 || operand3 == 0)
                answerNum = -operand3;
            else answerNum = operand1 / operand2 - operand3;
        } else if (operator1 == DIVIDE_OPERATOR && operator2 == MULTIPLY_OPERATOR){
            if (operand2 == 0 || operand3 == 0)
                answerNum = 0;
            else answerNum = operand1 / operand2 * operand3;
        } else if (operator1 == DIVIDE_OPERATOR && operator2 == DIVIDE_OPERATOR){
            if (operand2 == 0 || operand3 == 0)
                answerNum = 0;
            else answerNum = operand1 / operand2 / operand3;
        }

        question.setText(operand1 + " " + operators[operator1] + " " + operand2 + " " + operators[operator2] + " " + operand3);
    }
}