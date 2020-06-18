package com.kevin.aysunmatematicas.activities;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.kevin.aysunmatematicas.R;
import com.kevin.aysunmatematicas.dbutil.Score;
import com.kevin.aysunmatematicas.dbutil.ScoresViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameActivity extends AppCompatActivity {
    TextView txt1, txt2, txt3;
    Button btn1,btn2,btn3;
    ImageButton btnNext;
    int part1,part2,partRes;
    Score score;
    ScoresViewModel mScoresViewModel;
    int id;
    static final ExecutorService executorService =
            Executors.newFixedThreadPool(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_game);
        mScoresViewModel = new ViewModelProvider(this).get(ScoresViewModel.class);
        txt1 = findViewById(R.id.textViewPart1);
        txt2 = findViewById(R.id.textPart2);
        txt3 = findViewById(R.id.textRes);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btnNext = findViewById(R.id.buttonnext);
        newScore();
        defChallenge();
    }

    private void newScore() {
        executorService.execute(() -> mScoresViewModel.insert(new Score(0)));

        executorService.execute(() -> {
            id = mScoresViewModel.getLastId();
            System.out.println(id);
            score = new Score(id,0);
        });
    }

    private void defChallenge() {
        Random r=new Random();//permite la generacion de numeros aleatorios
        // TODO: 16/06/2020 range on config 
        part1=r.nextInt(5)+1;
        part2=r.nextInt(5)+1;
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
        list.add(partRes+r.nextInt(1)+1);
        list.add(partRes-r.nextInt(1)-1);
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
            incrementPoints();
        }else {
            Toast.makeText(getApplicationContext(),"Wrong",Toast.LENGTH_SHORT).show();
        }
    }

    private void incrementPoints() {
        score.puntos++;
        executorService.execute(new Runnable() {
            @Override
            public void run() {
//                id = mScoresViewModel.getLastId();
//                System.out.println(id);
                mScoresViewModel.update(score);
                System.out.println(score.puntos);
            }
        });
    }
}
