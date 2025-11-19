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

class AgregarCuotas : AppCompatActivity() {

    private lateinit var dbHelper: MainDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_cuotas)

        dbHelper = MainDatabaseHelper(this)

        val btnBack = findViewById<Button>(R.id.btnBack)
        val edtDni = findViewById<EditText>(R.id.edtDni)
        val edtMonto = findViewById<EditText>(R.id.edtMonto)
        val edtVencimiento = findViewById<EditText>(R.id.edtVencimiento)
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)

        btnAgregar.setOnClickListener{

            val dni = edtDni.text.toString()
            val monto = edtMonto.text.toString()
            val vencimiento = edtVencimiento.text.toString()

            if (dni.isNotEmpty() && monto.isNotEmpty() && vencimiento.isNotEmpty()){
                val success = dbHelper.addCuota(dni, monto, vencimiento)
                if (success > 0){
                    Toast.makeText(this, "Se agrego cuota exitosamente", Toast.LENGTH_SHORT).show()
                    edtDni.text.clear()
                    edtMonto.text.clear()
                    edtVencimiento.text.clear()
                }else {Toast.makeText(this, "Registro fallido", Toast.LENGTH_SHORT).show()}

            }else{Toast.makeText(this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show()}
        }
        btnBack.setOnClickListener{
            val intent = Intent(this, CuotaMensual::class.java)
            startActivity(intent)
        }

    }
}