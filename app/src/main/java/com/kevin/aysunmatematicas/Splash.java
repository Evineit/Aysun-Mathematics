package com.kevin.aysunmatematicas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.kevin.aysunmatematicas.fragments.HelpFragment;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();

                int bandera = Integer.parseInt(preferences.getString("Bandera","0"));

                if (bandera==1) {
                    Intent intent = new Intent(Splash.this,MainActivity.class);
                    startActivity(intent);
                }else {

                    Intent intent = new Intent(Splash.this,MainActivity.class);
                    startActivity(intent);

                    Intent intent2 = new Intent(Splash.this, HelpActivity.class);
                    startActivity(intent2);
                    // TODO: 15/06/2020 registrar alumno activity
//                    Intent intent3 = new Intent(Splash.this, HelpActivity.class);
//                    startActivity(intent3);
                }
                finish();
            }
        },2000);
    }
}