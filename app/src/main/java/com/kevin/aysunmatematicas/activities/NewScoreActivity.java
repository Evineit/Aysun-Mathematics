package com.kevin.aysunmatematicas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kevin.aysunmatematicas.R;

public class NewScoreActivity extends AppCompatActivity {
    private EditText mEditWordView;
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_score);
        mEditWordView = findViewById(R.id.edit_word);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    if (word.equalsIgnoreCase("delete")){
                        replyIntent.putExtra(EXTRA_REPLY, 1);
                        setResult(RESULT_OK, replyIntent);
                    }else {
                        setResult(RESULT_CANCELED,replyIntent);
                    }
                }
                finish();
            }
        });
    }
}