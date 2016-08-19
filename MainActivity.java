package com.example.kevin.scarnesdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.OverScroller;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static int usrOverallScore = 0;
    private static int usrTurnScore = 0;
    private static int compOverallScore = 0;
    private static int compTurnScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set default image
        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.dice1);
        //set score to default values
        TextView scoreBoard = (TextView) findViewById(R.id.scores);
        scoreBoard.setText(String.format("Your score: 0 Computer Score: 0"));

    }
    public void rollDice(View view){
        TextView scoreBoard = (TextView) findViewById(R.id.scores);
        int diceValue;
        diceValue = rollNewDice();
        if(diceValue == 1){
            usrTurnScore = 0;
            scoreBoard.setText(Html.fromHtml(String.format("You Rolled a One!",usrOverallScore,compOverallScore)));
            computerTurn();
        }
        else{
            usrTurnScore+=diceValue;
        }
        scoreBoard.setText(String.format("Your score: "+usrOverallScore+ " Computer Score: " + compOverallScore+ "\n Your Turn Score is:"+ usrTurnScore));
    }

    public int rollNewDice() {
        Random rn = new Random();
        int diceValue = rn.nextInt(6) + 1;
        ImageView image = (ImageView) findViewById(R.id.imageView);
        TextView scoreBoard = (TextView) findViewById(R.id.scores);
        switch (diceValue) {
            case 1:
                image.setImageResource(R.drawable.dice1);
                break;
            case 2:
                image.setImageResource(R.drawable.dice2);
                break;
            case 3:
                image.setImageResource(R.drawable.dice3);
                break;
            case 4:
                image.setImageResource(R.drawable.dice4);
                break;
            case 5:
                image.setImageResource(R.drawable.dice5);
                break;
            case 6:
                image.setImageResource(R.drawable.dice6);
                break;
            default:
        }
       return diceValue;
    }


    public void reset(View view) {
        usrOverallScore = 0;
        usrTurnScore = 0;
        compOverallScore = 0;
        compTurnScore = 0;
        TextView scoreBoard = (TextView) findViewById(R.id.scores);
        scoreBoard.setText(String.format("Your score: 0 Computer Score: 0",usrOverallScore,compOverallScore));
    }
    public void holdDice(View view){
        TextView scoreBoard = (TextView) findViewById(R.id.scores);
        usrOverallScore+=usrTurnScore;
        usrTurnScore = 0;
        scoreBoard.setText(String.format("Your score: "+usrOverallScore+ " Computer Score: " + compOverallScore));
        computerTurn();
    }
    public void computerTurn(){
        TextView scoreBoard = (TextView) findViewById(R.id.scores);
        Button roll = (Button) findViewById(R.id.button);
        Button hold = (Button) findViewById(R.id.button2);
        roll.setEnabled(false);
        hold.setEnabled(false);
        int diceValue;
        diceValue = rollNewDice();
        while(compTurnScore <20 && diceValue!=1) {
            compTurnScore += diceValue;
            scoreBoard.setText(String.format("Your score: " + usrOverallScore + " Computer Score: " + compOverallScore + "\n Computer Turn Score is:" + compTurnScore));
            diceValue = rollNewDice();
        }
        if(diceValue!=1){
            compTurnScore=0;
        }
        else{
            compOverallScore+= compTurnScore;
        }
        scoreBoard.setText(String.format("Your score: "+usrOverallScore+ " Computer Score: " + compOverallScore));
        roll.setEnabled(true);
        hold.setEnabled(true);
    }
}

