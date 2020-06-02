package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private boolean firstPlayerMove = true;
    private byte[] gameState = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private byte[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {6, 4, 2}};
    private boolean gameStop = false;
    private TextView winner;
    private Button newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        winner = (TextView)findViewById(R.id.textView);
        newGame = (Button)findViewById(R.id.button);

        newGame.setVisibility(View.INVISIBLE);
        winner.setAlpha(0f);
    }

    public void onTableClick(View view) {
        ImageView imageView = (ImageView) view;

        if (gameStop || gameState[Integer.parseInt(imageView.getTag().toString())] != 0) return;

        imageView.setTranslationY(-2000); // hide img of the screen

        if (firstPlayerMove) {
            imageView.setImageResource(R.drawable.tictactoe_x);
            gameState[Integer.parseInt(imageView.getTag().toString())] = 1;
        } else {
            imageView.setImageResource(R.drawable.tictactoe_o);
            gameState[Integer.parseInt(imageView.getTag().toString())] = 2;
        }

        imageView.animate().translationYBy(2000).setDuration(500);


        for (int i = 0; i < 8; i++) {
            byte[] winPos = winningPositions[i]; // 0 1 2

            if (gameState[winPos[0]] == 1 && gameState[winPos[1]] == 1 && gameState[winPos[2]] == 1) {
                winner.setAlpha(1f);
                winner.setText("FIRST PLAYER WIN");
                newGame.setVisibility(View.VISIBLE);
                gameStop = true;
            }
            else if (gameState[winPos[0]] == 2 && gameState[winPos[1]] == 2 && gameState[winPos[2]] == 2) {
                winner.setAlpha(1f);
                winner.setText("SECOND PLAYER WIN");
                newGame.setVisibility(View.VISIBLE);
                gameStop = true;
            }
        }

        firstPlayerMove = !firstPlayerMove;

        boolean flag = false;
        for (byte check: gameState){
            if (check == 0) flag = true;
        }
        if (!flag){
            winner.setAlpha(1f);
            winner.setText("NOBODY WIN");
            newGame.setVisibility(View.VISIBLE);
            gameStop = true;
        }

    }

    public void onButtonClick(View view) {
        newGame.setVisibility(View.INVISIBLE);
        winner.setAlpha(0f);

        androidx.gridlayout.widget.GridLayout layout = findViewById(R.id.gridLayout);

        for (int i = 0; i < layout.getChildCount(); i++){
            ImageView child = (ImageView)layout.getChildAt(i);
            child.setImageDrawable(null);
        }

        for (byte i = 0; i < gameState.length; i++){
            gameState[i] = 0;
        }
        firstPlayerMove = true;
        gameStop = false;
    }
}
