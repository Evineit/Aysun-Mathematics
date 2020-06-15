package com.kevin.aysunmatematicas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.kevin.aysunmatematicas.fragments.InicioFragment;

public class MainActivity extends AppCompatActivity implements CommFrag{
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InicioFragment inicio = new InicioFragment();
        fragmentTransaction.add(R.id.contenedor,inicio).commit();


    }
    public void iniciarJuego(){
        Toast.makeText(getApplicationContext(),"Iniciar Juego1",Toast.LENGTH_SHORT).show();
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
    public void callUsers() {
        Toast.makeText(getApplicationContext(),"Iniciar Juego4",Toast.LENGTH_SHORT).show();

    }

}