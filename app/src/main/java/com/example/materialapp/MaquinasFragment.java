package com.example.materialapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MaquinasFragment extends Fragment {

    public static final int REQUEST_UPDATE_DELETE_MAQUINA = 2;

    private MaquinaDbHelper mMaquinasDbHelper;

    private ListView mMaquinasList;
    private MaquinasCursorAdapter mMaquinasAdapter;
    private FloatingActionButton mAddButton;

    public MaquinasFragment() {

    }

    public static MaquinasFragment newInstance() {
        return new MaquinasFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_maquinas, container, false);

        mMaquinasList = (ListView) root.findViewById(R.id.maquinas_list);
        mMaquinasAdapter = new MaquinasCursorAdapter(getActivity(), null);
        mAddButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);


        mMaquinasList.setAdapter(mMaquinasAdapter);


        mMaquinasList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor currentItem = (Cursor) mMaquinasAdapter.getItem(i);
                @SuppressLint("Range") String currentMaquinaId = currentItem.getString(
                        currentItem.getColumnIndex(MaquinaContract.MaquinaEntry.ID_MAQUINA));

                showDetailScreen(currentMaquinaId);
            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddScreen();
            }
        });


        getActivity().deleteDatabase(MaquinaDbHelper.DATABASE_NAME);


        mMaquinasDbHelper = new MaquinaDbHelper(getActivity());


        loadMaquinas();

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                case AddEditMaquinaActivity.REQUEST_ADD_MAQUINA:
                    showSuccessfullSavedMessage();
                    loadMaquinas();
                    break;
                case REQUEST_UPDATE_DELETE_MAQUINA:
                    loadMaquinas();
                    break;
            }
        }
    }

    private void loadMaquinas() {
        new MaquinasLoadTask().execute();
    }

    private void showSuccessfullSavedMessage() {
        Toast.makeText(getActivity(),
                "Maquina guardada correctamente", Toast.LENGTH_SHORT).show();
    }

    private void showAddScreen() {
        Intent intent = new Intent(getActivity(), AddEditMaquinaActivity.class);
        startActivityForResult(intent, AddEditMaquinaActivity.REQUEST_ADD_LAWYER);
    }

    private void showDetailScreen(String maquinaId) {
        Intent intent = new Intent(getActivity(), MaquinaDetailActivity.class);
        intent.putExtra(MaquinasActivity.EXTRA_MAQUINA_ID, maquinaId);
        startActivityForResult(intent, REQUEST_UPDATE_DELETE_MAQUINA);
    }

    private class MaquinasLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mMaquinasDbHelper.getAllMaquinas();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                mMaquinasAdapter.swapCursor(cursor);
            } else {

            }
        }
    }
}