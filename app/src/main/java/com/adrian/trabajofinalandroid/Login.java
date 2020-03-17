package com.adrian.trabajofinalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adrian.trabajofinalandroid.R;
import com.adrian.trabajofinalandroid.SQL.DatabaseHelper;

public class Login extends AppCompatActivity implements View.OnClickListener {
    // Elementos
    EditText editUsuario, editPasswordUno;
    Button idButtonLogin;
    // Base de datos
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        instancias();
        acciones();
    }

    private void acciones() {
        idButtonLogin.setOnClickListener(this);
    }

    private void instancias() {
        editUsuario = findViewById(R.id.editUsuarioLogin);
        editPasswordUno = findViewById(R.id.editPrimeraPassLogin);
        idButtonLogin = findViewById(R.id.idButtonLogin);
        // Base de datos
        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idButtonLogin:
                if (editUsuario.equals("") || editPasswordUno.equals("")){
                    Toast.makeText(this, "Todos los datos deben de estar completos.", Toast.LENGTH_LONG).show();
                }else{
                    System.out.println("Logeando en la APP");
                    String nombreLogin = editUsuario.getText().toString();
                    String passwordLogin = editPasswordUno.getText().toString();
                    // Metodo
                    boolean comprobarLogin = databaseHelper.comprobarLogin(nombreLogin,passwordLogin);
                    if(comprobarLogin==true){
                        System.out.println("Login correcto");
                        Toast.makeText(this, "Accediendo a la aplicación.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this,MainAplicacion.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(this, "Los datos introducidos no son correctos. Comprueba el email o el password.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
