package com.kevin.aysunmatematicas.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kevin.aysunmatematicas.CommFrag;
import com.kevin.aysunmatematicas.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageButton iniciarButton;
    View vista;
    Activity actividad;
    CommFrag interfaz;
    ImageButton configButton;
    ImageButton usersButton;
    ImageButton helpuButton;


    public InicioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_inicio2, container, false);

        iniciarButton=vista.findViewById(R.id.botonIniciar);
        configButton=vista.findViewById(R.id.configButton);
        helpuButton=vista.findViewById(R.id.helpoButton);
        usersButton=vista.findViewById(R.id.userButton);
        TextView nick = vista.findViewById(R.id.textNickName);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String nickName = preferences.getString("Nick","Nombre");
        nick.setText(nickName);
        addEventsMenu();


        return vista;

    }

    private void addEventsMenu() {
        iniciarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"Iniciar Juego desde el fragment",Toast.LENGTH_SHORT).show();
                interfaz.iniciarJuego();
            }
        });
        configButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"Iniciar Juego desde el fragment",Toast.LENGTH_SHORT).show();
                interfaz.callSettings();
            }
        });
        helpuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"Iniciar Juego desde el fragment",Toast.LENGTH_SHORT).show();
                interfaz.callInstrucciones();
            }
        });
        usersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"Iniciar Juego desde el fragment",Toast.LENGTH_SHORT).show();
                interfaz.callUsers();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            actividad= (Activity) context;
            interfaz = (CommFrag) actividad;
        }
    }
}