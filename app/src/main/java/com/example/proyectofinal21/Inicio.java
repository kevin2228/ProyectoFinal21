package com.example.proyectofinal21;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {
    private Button btn_consultaNumero, btn_consultaporNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_consultaNumero = findViewById(R.id.btn_consultaNumero);
        btn_consultaporNombre = findViewById(R.id.btn_consultaporNombre);


    }

    /*public void numerohimno(View view){
        Intent i  = new Intent(this, NumeroHimno.class);
        startActivity(i);*/

    }

    /*public void nombreAutor(View view){
        Intent i = new Intent(this, NombreAutor.class );
        startActivity(i);
    }

}*/
