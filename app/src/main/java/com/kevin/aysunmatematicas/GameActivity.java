package com.kevin.aysunmatematicas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    TextView txt1, txt2, txt3;
    Button btn1,btn2,btn3;
    ImageButton btnNext;
    int part1,part2,partRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_game);
        txt1 = findViewById(R.id.textViewPart1);
        txt2 = findViewById(R.id.textPart2);
        txt3 = findViewById(R.id.textRes);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btnNext = findViewById(R.id.buttonnext);
        defChallenge();
    }
    private void defChallenge() {
        Random r=new Random();//permite la generacion de numeros aleatorios
        part1=r.nextInt(5)+6;
        part2=r.nextInt(5)+6;
        partRes=part1+part2;
        txt1.setText(String.valueOf(part1));
        txt2.setText(String.valueOf(part2));
        txt3.setText("0");
        setButtons();
    }

    private void setButtons() {
        // TODO: 15/06/2020 disable used buttons
        Random r=new Random();//permite la generacion de numeros aleatorios
        ArrayList<Integer> list = new ArrayList<>();
        list.add(partRes);
        list.add(partRes+r.nextInt(5)+1);
        list.add(partRes-r.nextInt(5)-1);
        Collections.shuffle(list);
        btn1.setText(String.valueOf(list.get(0)));
        btn2.setText(String.valueOf(list.get(1)));
        btn3.setText(String.valueOf(list.get(2)));
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt3.setText(btn1.getText());
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt3.setText(btn2.getText());
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt3.setText(btn3.getText());
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer();
            }
        });

    }

    private void validateAnswer() {
        if (!txt3.toString().trim().equals("0") && partRes==Integer.parseInt(txt3.getText().toString())){
            final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.correct);
            mediaPlayer.start(); // no need to call prepare(); create() does that for you
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.release();
                }
            });
            defChallenge();
        }else {
            Toast.makeText(getApplicationContext(),"Wrong",Toast.LENGTH_SHORT).show();
        }
    }
}
