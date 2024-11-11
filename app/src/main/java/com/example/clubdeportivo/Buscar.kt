package com.example.clubdeportivo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Buscar : AppCompatActivity() {

    private lateinit var dbHelper: MainDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)

        dbHelper = MainDatabaseHelper(this)

        val btnBack = findViewById<Button>(R.id.btnBack3)
        val edtBuscar = findViewById<EditText>(R.id.edtBuscar)
        val btnContinuar = findViewById<Button>(R.id.btnContinuar)

        btnContinuar.setOnClickListener{
            val dni = edtBuscar.text.toString()

            val member = dbHelper.getMemberByDni(dni)
            if (member.size < 3){
                Toast.makeText(this, "No se encontro miembro con el dni " + dni, Toast.LENGTH_SHORT).show()
            }else {
                val id = member[0]
                val name = member[1]
                val surname = member[2]
                val isSocio = member[3]

                val intent = Intent(this, EditarDatos::class.java)
                intent.putExtra("id", id)
                intent.putExtra("name", name)
                intent.putExtra("surname", surname)
                intent.putExtra("dni", dni)
                intent.putExtra("socio", isSocio)
                startActivity(intent)
            }
        }
        btnBack.setOnClickListener{
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }


    }
}