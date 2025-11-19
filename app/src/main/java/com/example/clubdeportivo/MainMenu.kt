package com.example.clubdeportivo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val btnInscribirPostulante = findViewById<Button>(R.id.btnInscribirPostulante)
        val btnEditarDatos = findViewById<Button>(R.id.btnEditarDatos)
        val btnActividadDiaria = findViewById<Button>(R.id.btnActividadDiaria)
        val btnCuotaMensual = findViewById<Button>(R.id.btnCuotaMensual)
        val btnCerrarSession = findViewById<Button>(R.id.btnCerrarSesion)

        btnInscribirPostulante.setOnClickListener{
            val intent = Intent(this, Continuar::class.java)
            startActivity(intent)
        }
        btnEditarDatos.setOnClickListener{
            val intent = Intent(this, Buscar::class.java)
            startActivity(intent)
        }
        btnActividadDiaria.setOnClickListener{
            val intent = Intent(this, CuotaDiaria::class.java)
            startActivity(intent)
        }
        btnCuotaMensual.setOnClickListener{
            val intent = Intent(this, CuotaMensual::class.java)
            startActivity(intent)
        }

        btnCerrarSession.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}