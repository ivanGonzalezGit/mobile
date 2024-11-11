package com.example.clubdeportivo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast

class PagarCuotaMensual : AppCompatActivity() {

    private lateinit var dbHelper: MainDatabaseHelper
    private lateinit var lvCuotas: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagar_cuota_mensual)

        dbHelper = MainDatabaseHelper(this)
        var cuotas = mutableListOf<String>()
        lvCuotas = findViewById<ListView>(R.id.lvCuotas)
        val cuotasAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cuotas)
        var selectedItem: String? = null

        val btnBack = findViewById<Button>(R.id.btnBack)
        val edtDni = findViewById<EditText>(R.id.edtDNI)
        val btnBuscar = findViewById<Button>(R.id.btnBuscar)
        val btnEfectivo = findViewById<Button>(R.id.btnEfectivo)
        val btnTarjeta = findViewById<Button>(R.id.btnTarjeta)

        lvCuotas.adapter = cuotasAdapter

        lvCuotas.setOnItemClickListener{parent, view, position, id ->
            val clickedItem = parent.getItemAtPosition(position) as String
            selectedItem = clickedItem
        }

        btnBuscar.setOnClickListener{
            cuotas.clear()
            cuotas.addAll(dbHelper.getCuotas(edtDni.text.toString()))
            cuotasAdapter.notifyDataSetChanged()
        }
        btnBack.setOnClickListener{
            val intent = Intent(this, CuotaMensual::class.java)
            startActivity(intent)
        }
        btnEfectivo.setOnClickListener{
            if (selectedItem != null){
                val id = selectedItem!!.substringAfter("ID: ").substringBefore(" -")
                val success = dbHelper.pagarCuota(id)
                if (success > 0){
                    Toast.makeText(this, "Pago realizado con exito", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "No se ha podido realizar el pago", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this, "No ha seleccionado ninguna cuota", Toast.LENGTH_SHORT).show()
            }
        }
        btnTarjeta.setOnClickListener{
            if (selectedItem != null){
                val id = selectedItem!!.substringAfter("ID: ").substringBefore(" -")
                val success = dbHelper.pagarCuota(id)
                if (success > 0){
                    Toast.makeText(this, "Pago realizado con exito", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "No se ha podido realizar el pago", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this, "No ha seleccionado ninguna cuota", Toast.LENGTH_SHORT).show()
            }
        }
    }
}