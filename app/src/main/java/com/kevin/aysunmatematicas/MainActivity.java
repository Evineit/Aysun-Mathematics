package com.kevin.aysunmatematicas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.kevin.aysunmatematicas.fragments.InicioFragment;
import com.kevin.aysunmatematicas.fragments.UsersFragment;

public class MainActivity extends AppCompatActivity implements CommFrag{
    FragmentManager fragmentManager = getSupportFragmentManager();
//    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    UsersFragment usersFragment;
    private InicioFragment inicioFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicioFragment = new InicioFragment();
        usersFragment = new UsersFragment();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        String nickName = preferences.getString("Nick","Nombre");
        if (!nickName.equalsIgnoreCase("nombre") && !nickName.trim().isEmpty()) {
            fragmentManager.beginTransaction().add(R.id.contenedor, inicioFragment).commit();
        }else {
            fragmentManager.beginTransaction().add(R.id.contenedor,usersFragment).commit();
        }


    }
    public void iniciarJuego(){
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }

    @Override
    public void callSettings() {
        Toast.makeText(getApplicationContext(),"Iniciar Juego2",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void callInstrucciones() {
//        Toast.makeText(getApplicationContext(),"Iniciar Juego3",Toast.LENGTH_SHORT).show();
//        fragmentTransaction = fragmentManager.beginTransaction();
//        HelpFragment help = new HelpFragment();
//        fragmentTransaction.replace(R.id.contenedor,help).commit();
        Intent intent = new Intent(MainActivity.this, HelpActivity.class);
        startActivity(intent);
    }

    @Override
    public void callMenu() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,inicioFragment).commit();
    }

    @Override
    public void callUsers() {
//        Toast.makeText(getApplicationContext(),"Iniciar Juego4",Toast.LENGTH_SHORT).show();
        fragmentManager.beginTransaction().replace(R.id.contenedor,usersFragment).commit();

    }

}