package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CuotaDiaria : AppCompatActivity() {

    private lateinit var dbHelper: MainDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuota_diaria)

        dbHelper = MainDatabaseHelper(this)

        val btnBack = findViewById<Button>(R.id.btnBack)
        val edtDni = findViewById<EditText>(R.id.edtDNI)
        val edtActividad = findViewById<EditText>(R.id.edtActividad)
        val edtMonto = findViewById<EditText>(R.id.edtMonto)
        val btnEfectivo = findViewById<Button>(R.id.btnEfectivo)
        val btnTarjeta = findViewById<Button>(R.id.btnTarjeta)

        btnBack.setOnClickListener {
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }
        btnEfectivo.setOnClickListener{
            val success = dbHelper.pagarActividad(edtDni.text.toString(), edtActividad.text.toString(), edtMonto.text.toString())
            if (success > 0){
                Toast.makeText(this, "Actividad abonada con exito", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "No se pudo pagar actividad", Toast.LENGTH_SHORT).show()
            }
        }
        btnTarjeta.setOnClickListener{
            val success = dbHelper.pagarActividad(edtDni.text.toString(), edtActividad.text.toString(), edtMonto.text.toString())
            if (success > 0){
                Toast.makeText(this, "Actividad abonada con exito", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "No se pudo pagar actividad", Toast.LENGTH_SHORT).show()
            }
        }
    }

}