package com.example.materialapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class AddEditMaquinaFragment extends Fragment {

    private static final String ARG_MAQUINA_ID = "arg_maquina_id";

    private String mMaquinaId;

    private MaquinasDbHelper mMaquinasDbHelper;

    private FloatingActionButton mSaveButton;
    private TextInputEditText mNameField;
    private TextInputEditText mPhoneNumberField;
    private TextInputEditText mSpecialtyField;
    private TextInputEditText mBioField;
    private TextInputLayout mNameLabel;
    private TextInputLayout mPhoneNumberLabel;
    private TextInputLayout mSpecialtyLabel;
    private TextInputLayout mBioLabel;

    public AddEditMaquinaFragment() {
        // Required empty public constructor
    }

    public static AddEditMaquinaFragment newInstance(String maquinaId) {
        AddEditMaquinaFragment fragment = new AddEditMaquinaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MAQUINA_ID, maquinaId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_edit_maquina, container, false);

        // Referencias UI
        mSaveButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        mNameField = (TextInputEditText) root.findViewById(R.id.et_name);
        mPhoneNumberField = (TextInputEditText) root.findViewById(R.id.et_phone_number);
        mSpecialtyField = (TextInputEditText) root.findViewById(R.id.et_specialty);
        mBioField = (TextInputEditText) root.findViewById(R.id.et_bio);
        mNameLabel = (TextInputLayout) root.findViewById(R.id.til_name);
        mPhoneNumberLabel = (TextInputLayout) root.findViewById(R.id.til_phone_number);
        mSpecialtyLabel = (TextInputLayout) root.findViewById(R.id.til_specialty);
        mBioLabel = (TextInputLayout) root.findViewById(R.id.til_bio);

        // Eventos
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEditMaquina();
            }
        });

        mMaquinasDbHelper = new mMaquinasDbHelper(getActivity());

        // Carga de datos
        if (mMaquinaId != null) {
            loadMaquina();
        }

        return root;
    }
    private void loadMaquina() {
        new GetMaquinaByIdTask().execute();
    }

    private void addEditMaquina() {
        boolean error = false;

        String name = mNameField.getText().toString();
        String phoneNumber = mPhoneNumberField.getText().toString();
        String specialty = mSpecialtyField.getText().toString();
        String bio = mBioField.getText().toString();

        if (TextUtils.isEmpty(name)) {
            mNameLabel.setError(getString(R.string.first_fragment_label));
            error = true;
        }

        if (TextUtils.isEmpty(phoneNumber)) {
            mPhoneNumberLabel.setError(getString(R.string.first_fragment_label));
            error = true;
        }

        if (TextUtils.isEmpty(specialty)) {
            mSpecialtyLabel.setError(getString(R.string.first_fragment_label));
            error = true;
        }


        if (TextUtils.isEmpty(bio)) {
            mBioLabel.setError(getString(R.string.first_fragment_label));
            error = true;
        }

        if (error) {
            return;
        }

        Maquina maquina = new Maquina(name, specialty, phoneNumber, bio, "");

        new AddEditMaquinaTask().execute(maquina);

    }

    private void showMaquinasScreen(Boolean requery) {
        if (!requery) {
            showAddEditError();
            getActivity().setResult(Activity.RESULT_CANCELED);
        } else {
            getActivity().setResult(Activity.RESULT_OK);
        }

        getActivity().finish();
    }

    private void showAddEditError() {
        Toast.makeText(getActivity(),
                "Error al agregar nueva información", Toast.LENGTH_SHORT).show();
    }

    private void showMaquina(Maquina maquina) {
        mNameField.setText(maquina.getNombre());
        mPhoneNumberField.setText(maquina.getCliente());
        mSpecialtyField.setText(maquina.getDescrip());
        mBioField.setText(maquina.getMeses());
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                "Error al editar maquina", Toast.LENGTH_SHORT).show();
    }

    private class GetMaquinaByIdTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mMaquinasDbHelper.getMaquinaById(mMaquinaId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                showMaquina(new Maquina(cursor));
            } else {
                showLoadError();
                getActivity().setResult(Activity.RESULT_CANCELED);
                getActivity().finish();
            }
        }


    }

    private class AddEditMaquinaTask extends AsyncTask<Maquina, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Maquina... maquinas) {
            if (mMaquinaId != null) {
                return mMaquinasDbHelper.updateMaquina(maquinas[0], mMaquinaId) > 0;

            } else {
                return mMaquinasDbHelper.saveMaquina(maquinas[0]) > 0;
            }

        }
    }
    @Override
    protected void onPostExecute(Boolean result) {
        showMaquinasScreen(result);
    }

}


