package com.kevin.aysunmatematicas.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kevin.aysunmatematicas.R;
import com.kevin.aysunmatematicas.dbutil.Score;
import com.kevin.aysunmatematicas.dbutil.ScoreListAdapter;
import com.kevin.aysunmatematicas.dbutil.ScoresViewModel;

import java.util.List;

public class ScoresActivity extends AppCompatActivity {
    private ScoresViewModel mScoresViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ScoreListAdapter adapter = new ScoreListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Toast.makeText(getApplicationContext(),"Done loading",Toast.LENGTH_SHORT).show();
        mScoresViewModel = new ViewModelProvider(this).get(ScoresViewModel.class);
        mScoresViewModel.getAllScores().observe(this, new Observer<List<Score>>() {
            @Override
            public void onChanged(List<Score> scores) {
                adapter.setScores(scores);
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoresActivity.this, NewScoreActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Score word = new Score(data.getIntExtra(NewScoreActivity.EXTRA_REPLY,0));
            mScoresViewModel.deleteAll();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.not_deleted,
                    Toast.LENGTH_LONG).show();
        }
    }
}