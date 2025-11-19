package com.example.clubdeportivo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button

class Continuar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_continuar)

        val btnBack = findViewById<Button>(R.id.btnBack1)
        val btnSocio = findViewById<Button>(R.id.btnSocio)
        val btnNoSocio = findViewById<Button>(R.id.btnNoSocio)

        btnSocio.setOnClickListener{
            val intent = Intent(this, Inscripcion::class.java)
            intent.putExtra("socio", true)
            startActivity(intent)
        }
        btnNoSocio.setOnClickListener{
            val intent = Intent(this, Inscripcion::class.java)
            intent.putExtra("socio", false)
            startActivity(intent)
        }
        btnBack.setOnClickListener{
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }
    }
}