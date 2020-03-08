package com.adrian.trabajofinalandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.adrian.trabajofinalandroid.Adaptadores.AdaptadorRecycler;
import com.adrian.trabajofinalandroid.R;
import com.adrian.trabajofinalandroid.SQL.DatabaseHelper;
import com.adrian.trabajofinalandroid.Utiles.Titulos;

import java.util.ArrayList;

public class FragmentAgregar  extends Fragment implements View.OnClickListener {
    // Elementos
    EditText editnombre, editautor, editlanzamiento, editprecio;
    Button buttonAgregar;
    DatabaseHelper databaseHelper;
    ArrayList<Titulos> listaTitulos;
    AdaptadorRecycler adaptadorRecycler;


    public static FragmentAgregar newInstance(String nombrePlataforma) {
        Bundle args = new Bundle();
        args.putString("Plataforma",nombrePlataforma);
        FragmentAgregar fragment = new FragmentAgregar();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentAgregar() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_agregar,container,false);
        // Instancias
        editnombre = view.findViewById(R.id.editnombretitulo);
        editlanzamiento = view.findViewById(R.id.editlanzamientotitulo);
        editautor = view.findViewById(R.id.editautortitulo);
        editprecio = view.findViewById(R.id.editpreciotitulo);
        buttonAgregar = view.findViewById(R.id.buttonAgregar);
        // BD
        databaseHelper = new DatabaseHelper(getContext());
        // Acciones
        buttonAgregar.setOnClickListener(this);
        // Lista
        listaTitulos = new ArrayList();
        adaptadorRecycler = new AdaptadorRecycler(getContext(), listaTitulos);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAgregar:
                if(editautor.getText().toString().length()==0  || editlanzamiento.getText().toString().length()==0  || editnombre.getText().toString().length()==0  || editprecio.getText().toString().length()==0 ){
                    Toast.makeText(getContext(), "Debes rellenar todos los campos del formulario.", Toast.LENGTH_LONG).show();
                }else{
                    String nombreAutor = editautor.getText().toString();
                    String precio = editprecio.getText().toString();
                    String lanzamiento = editlanzamiento.getText().toString();
                    String nombre = editnombre.getText().toString();
                    Boolean tituloExistente = databaseHelper.comprobarTitulo(nombre);
                    if(tituloExistente==true){
                        Boolean insertarTitulo = databaseHelper.insertarTitulos(nombre,nombreAutor,lanzamiento,precio);
                        if (insertarTitulo==true){
                            listaTitulos.add(new Titulos(nombre,nombreAutor,lanzamiento,precio));
                            adaptadorRecycler.notifyDataSetChanged();
                            Toast.makeText(getContext(), "Agregando tiítulo a la biblioteca.", Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(getContext(), "Ha ocurrido un problema con la BASE DE DATOS!", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getContext(), "Ya hay un título registrado con ese nombre, lo sentimos.", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }

    }
}
