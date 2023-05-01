package com.example.materialapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.fragment.app.Fragment;




public class MaquinaDetailFragment extends Fragment {



    private static final String ARG_MAQUINA_ID = "MaquinaId";

    private String mMaquinaId;

    private TextView mCliente;
    private TextView mDescrip;
    private TextView mMeses;

    private MaquinaDbHelper mMaquinasDbHelper;
    public MaquinaDetailFragment() {

    }


    public static MaquinaDetailFragment newInstance(String maquinaId) {
        MaquinaDetailFragment fragment = new MaquinaDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MAQUINA_ID, maquinaId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMaquinaId = getArguments().getString(ARG_MAQUINA_ID);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_maquina_detail, container, false){
            View root = inflater.inflate(R.layout.fragment_maquina_detail, container, false);
            mCliente = (TextView) root.findViewById(R.id.tv_phone_number);
            mDescrip = (TextView) root.findViewById(R.id.tv_specialty);
            mMeses = (TextView) root.findViewById(R.id.tv_bio);

            mMaquinasDbHelper = new mMaquinasDbHelper(getActivity());

            loadMaquina();

            return root;
        };
    }
}