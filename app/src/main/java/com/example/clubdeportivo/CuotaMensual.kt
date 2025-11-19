package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CuotaMensual : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuota_mensual)

        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnAgregarCuota = findViewById<Button>(R.id.btnAgregarCuotaMensual)
        val btnPagarCuota = findViewById<Button>(R.id.btnPagarCuotaMensual)
        val btnVencimientos = findViewById<Button>(R.id.btnVencimientos)

        btnAgregarCuota.setOnClickListener{
            val intent = Intent(this, AgregarCuotas::class.java)
            startActivity(intent)
        }
        btnPagarCuota.setOnClickListener{
            val intent = Intent(this, PagarCuotaMensual::class.java)
            startActivity(intent)
        }
        btnVencimientos.setOnClickListener{
            val intent = Intent(this, CuotasVencidas::class.java)
            startActivity(intent)
        }
        btnBack.setOnClickListener{
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }

    }
}