package com.example.saurabh.tic_tac_to;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class gamepage extends AppCompatActivity implements View.OnClickListener{

    private Button[][] btn = new Button[3][3];
    private boolean turn1 = true;
    private int roundcount;
    private int point1;
    private int point2;

    private TextView tv1;
    private TextView tv2;

    Button reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamepage);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                String btnid = "btn_"+i+j;
                int resID = getResources().getIdentifier(btnid,"id",getPackageName());
                btn[i][j] = findViewById(resID);
                btn[i][j].setOnClickListener(this);
            }
        }

        reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetgame();
            }
        });


    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")){
            return;
        }
        if (turn1){
            ((Button) v).setText("x");
        }else {
            ((Button) v).setText("o");
        }
        roundcount++;

        if (checkforWin()){
            if (turn1){
                player1Wins();
            }else {
                player2Wins();
            }
        }else if (roundcount == 9){
            draw();
        }else {
            turn1 = !turn1;
        }
    }

    private boolean checkforWin(){
        String[][] field = new  String[3][3];
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                field[i][j] = btn[i][j].getText().toString();
            }
        }
        for (int i=0;i<3;i++){
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")){
                return true;
            }
        }

        for (int i=0;i<3;i++){
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")){
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")){
            return true;
        }
        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")){
            return true;
        }
        return false;
    }

    private void player1Wins(){
        point1++;
        Toast.makeText(this,"player 1 wins!",Toast.LENGTH_SHORT).show();
        updatepointstext();
        resetboard();
    }

    private void player2Wins(){
        point2++;
        Toast.makeText(this,"player 2 wins!",Toast.LENGTH_SHORT).show();
        updatepointstext();
        resetboard();
    }

    private void draw(){
        Toast.makeText(this,"Draw!!",Toast.LENGTH_SHORT).show();
        resetboard();
    }

    private void updatepointstext(){
        tv1.setText("Player 1:" + point1);
        tv2.setText("Player 2:" + point2);
    }

    private void resetboard(){
        for (int i = 0;i<3;i++){
            for (int j=0;j<3;j++){
                btn[i][j].setText("");
            }
        }

        roundcount=0;
        turn1=true;
    }

    private void resetgame(){
        point1=0;
        point2=0;
        updatepointstext();
        resetboard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundcount",roundcount);
        outState.putInt("player1points",point1);
        outState.putInt("player2points",point2);
        outState.putBoolean("player1turn",turn1);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundcount = savedInstanceState.getInt("roundcount");
        point1 = savedInstanceState.getInt("player1points");
        point2 = savedInstanceState.getInt("player2points");
        turn1 = savedInstanceState.getBoolean("player1turn");
    }
}
