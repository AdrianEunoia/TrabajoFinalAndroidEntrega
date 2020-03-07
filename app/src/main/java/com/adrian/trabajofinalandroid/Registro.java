package com.adrian.trabajofinalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adrian.trabajofinalandroid.SQL.DatabaseHelper;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    // Elementos
    EditText editUsuario, editPasswordUno, editPasswordDos;
    Button buttonRegistro, buttonVaciar;
    TextView idviewlogin;
    // Base de datos
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        instancias();
        acciones();
    }

    private void acciones() {
        buttonVaciar.setOnClickListener(this);
        buttonRegistro.setOnClickListener(this);
        idviewlogin.setOnClickListener(this);
    }

    private void instancias() {
        editUsuario = findViewById(R.id.editUsuarioRegistro);
        editPasswordDos = findViewById(R.id.editSegundaPassRegistro);
        editPasswordUno = findViewById(R.id.editPrimeraPassRegistro);
        buttonRegistro = findViewById(R.id.idButtonRegistrar);
        buttonVaciar = findViewById(R.id.idButtonVaciar);
        idviewlogin = findViewById(R.id.idtextLogin);
        // Base de datos
        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idButtonRegistrar:
                if (editUsuario.equals("") || editPasswordUno.equals("") || editPasswordDos.equals("")){
                    Toast.makeText(this, "Todos los datos deben de estar completos.", Toast.LENGTH_LONG).show();
                }
                else if(editPasswordUno.getText().toString().equals(editPasswordDos.getText().toString())){
                    System.out.println("Registrando usuario");
                    String usuarioRegistro = editUsuario.getText().toString();
                    String passwordRegistro = editPasswordUno.getText().toString();
                    Boolean usuarioExistente = databaseHelper.comprobarUsuario(usuarioRegistro);
                    if(usuarioExistente==true){
                        Boolean insertarUsuario = databaseHelper.insertarUsuarios(usuarioRegistro,passwordRegistro);
                        if(insertarUsuario==true){
                            Toast.makeText(this, "Usuario insertado con existo!", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(this, "Ya hay un usuario registrado con ese nombre, lo sentimos.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this, "Lo sentimos, las contraseñas no coinciden.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.idButtonVaciar:
                System.out.println("Vaciando campos");
                break;
            case R.id.idtextLogin:
                System.out.println("Accediendo a login");
                Intent intent = new Intent(Registro.this,Login.class);
                startActivity(intent);
                break;
        }
    }
}
