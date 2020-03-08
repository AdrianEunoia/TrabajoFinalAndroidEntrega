package com.adrian.trabajofinalandroid.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.adrian.trabajofinalandroid.R;
import com.adrian.trabajofinalandroid.SQL.DatabaseHelper;

public class FragmentBuscar extends Fragment implements View.OnClickListener {
    // Elementos
    EditText ideditnombre;
    Button buttonBuscar;
    TextView texttitulo, textautor, textlanzamiento, textprecio;
    DatabaseHelper databaseHelper;

    public static FragmentAgregar newInstance(String nombrePlataforma) {
        Bundle args = new Bundle();
        args.putString("Plataforma",nombrePlataforma);
        FragmentAgregar fragment = new FragmentAgregar();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentBuscar() {
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
        View view = inflater.inflate(R.layout.layout_buscar,container,false);
        // Instancias
        ideditnombre = view.findViewById(R.id.idEditBuscar);
        texttitulo = view.findViewById(R.id.idtexttitulo);
        textautor = view.findViewById(R.id.idtextautor);
        textlanzamiento = view.findViewById(R.id.idtextaño);
        textprecio = view.findViewById(R.id.idtextprecio);
        buttonBuscar = view.findViewById(R.id.idButtonBuscar);
        // BD
        databaseHelper = new DatabaseHelper(getContext());
        // Acciones
        buttonBuscar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idButtonBuscar:
                // Comprobar existencia del titulo
                String nombreBuscado = ideditnombre.getText().toString();
                Boolean tituloExistente = databaseHelper.comprobarTitulo(nombreBuscado);
                if(tituloExistente==false) {
                    Toast.makeText(getContext(), "Buscando título en la biblioteca.", Toast.LENGTH_SHORT).show();
                    Cursor cursor = databaseHelper.obtenerInformacionTitulo(nombreBuscado);
                    if (cursor.getCount()==0){
                        Toast.makeText(getContext(), "Ha ocurrido un problema con la BASE DE DATOS!", Toast.LENGTH_LONG).show();
                    }else{
                        while (cursor.moveToNext()){
                            String tituloSacado = cursor.getString(0);
                            texttitulo.setText(cursor.getString(0));
                            textautor.setText(cursor.getString(1));
                            textlanzamiento.setText(cursor.getString(2));
                            textprecio.setText(cursor.getString(3));
                        }
                    }
                }else{
                    Toast.makeText(getContext(), "El título buscado no existe en nuestra biblioteca.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
