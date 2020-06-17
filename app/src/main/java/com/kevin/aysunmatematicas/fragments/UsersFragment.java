package com.kevin.aysunmatematicas.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.kevin.aysunmatematicas.CommFrag;
import com.kevin.aysunmatematicas.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CommFrag interfaz;
    private EditText name;

    public UsersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UsersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UsersFragment newInstance(String param1, String param2) {
        UsersFragment fragment = new UsersFragment();
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
        View vista = inflater.inflate(R.layout.fragment_users, container, false);
        ImageButton nextButton = vista.findViewById(R.id.nextButton);
        ImageButton cancelButton = vista.findViewById(R.id.cancelButton);
        name =vista.findViewById(R.id.editTextTextPersonName);
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String nickName = preferences.getString("Nick","Nombre");
        name.setText(nickName);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 15/06/2020 back to home
                registerUser();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().equalsIgnoreCase("nombre") && !name.toString().trim().isEmpty()) {
                    interfaz.callMenu();
                    name.setText(nickName);
                }else{
                    Toast.makeText(getContext(),"Inserta un nombre",Toast.LENGTH_SHORT).show();
                }

            }
        });
        return vista;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            interfaz = (CommFrag) getActivity();
        }
    }
    public void registerUser(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        if (name.getText()!=null && !name.getText().toString().trim().equalsIgnoreCase("")
        && !name.getText().toString().equalsIgnoreCase("Nombre")){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Nick", name.getText().toString());
            editor.apply();
            interfaz.callMenu();
        }else{
            Toast.makeText(getContext(),"Ingresa un nombre",Toast.LENGTH_SHORT).show();
        }
    }
}