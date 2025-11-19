package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class CuotasVencidas : AppCompatActivity() {

    private lateinit var dbHelper: MainDatabaseHelper
    private lateinit var lvVencimientos: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuotas_vencidas)

        dbHelper = MainDatabaseHelper(this)
        lvVencimientos = findViewById<ListView>(R.id.lvVencimientos)
        var vencimientos = mutableListOf<String>()
        val vencimientosAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, vencimientos)
        lvVencimientos.adapter = vencimientosAdapter

        val btnBack = findViewById<Button>(R.id.btnBack)
        val edtFecha = findViewById<EditText>(R.id.edtFecha)
        val btnBuscar = findViewById<Button>(R.id.btnBuscar)

        btnBuscar.setOnClickListener{
            vencimientos.clear()
            vencimientos.addAll(dbHelper.getCuotasVencimiento(edtFecha.text.toString()))
            vencimientosAdapter.notifyDataSetChanged()
        }
        btnBack.setOnClickListener{
            val intent = Intent(this, CuotaMensual::class.java)
            startActivity(intent)
        }

    }
}