package com.adrian.trabajofinalandroid.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adrian.trabajofinalandroid.Adaptadores.AdaptadorRecycler;
import com.adrian.trabajofinalandroid.R;
import com.adrian.trabajofinalandroid.SQL.DatabaseHelper;
import com.adrian.trabajofinalandroid.Utiles.Titulos;

import java.util.ArrayList;

public class FragmentMostrar extends Fragment {
    // Elementos
    RecyclerView recyclerMostrar;
    private RecyclerView recyclerViewBeneficiary;
    private ArrayList<Titulos> listaTitulos;
    private AdaptadorRecycler adaptadorRecycler;
    private DatabaseHelper databaseHelper;

    public static FragmentAgregar newInstance(String nombrePlataforma) {
        Bundle args = new Bundle();
        args.putString("Plataforma",nombrePlataforma);
        FragmentAgregar fragment = new FragmentAgregar();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentMostrar() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listaTitulos = new ArrayList();
        // BD, IMPORTANTE INSTANCIAS LA BD AQUI
        databaseHelper = new DatabaseHelper(getContext());
        Cursor cursor = databaseHelper.obtenerTodosTitulos();
        while (cursor.moveToNext()){
            listaTitulos.add(new Titulos(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
        }
        adaptadorRecycler = new AdaptadorRecycler(context, listaTitulos);
        adaptadorRecycler.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerMostrar.setAdapter(adaptadorRecycler);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_mostrar,container,false);
        recyclerMostrar = view.findViewById(R.id.idRecyclerMostrar);
        recyclerMostrar.setLayoutManager(new GridLayoutManager(getContext(),1, RecyclerView.VERTICAL, false));

        return view;
    }
}
