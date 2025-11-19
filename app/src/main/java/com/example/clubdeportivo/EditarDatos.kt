package com.example.clubdeportivo

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditarDatos : AppCompatActivity() {

    private lateinit var dbHelper: MainDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_datos)

        dbHelper = MainDatabaseHelper(this)

        val txtID = findViewById<TextView>(R.id.txtID)
        val btnBack = findViewById<Button>(R.id.btnBack4)
        val edtName = findViewById<EditText>(R.id.edtNombre)
        val edtSurname = findViewById<EditText>(R.id.edtApellido)
        val edtDni = findViewById<EditText>(R.id.edtDNI)
        val rbdAptoFisico = findViewById<RadioButton>(R.id.rdbAptoFisico)
        val btnEdit = findViewById<Button>(R.id.btnEditar)

        val socio = intent.getStringExtra("socio")
        val id = intent.getStringExtra("id")

        txtID.text = id
        edtName.setText(intent.getStringExtra("name"))
        edtSurname.setText(intent.getStringExtra("surname"))
        edtDni.setText(intent.getStringExtra("dni"))

        btnEdit.setOnClickListener{

            val name = edtName.text.toString()
            val surname = edtSurname.text.toString()
            val dni = edtDni.text.toString()
            val aptoFisico = rbdAptoFisico.isChecked

            if(name.isNotEmpty() && surname.isNotEmpty() && dni.isNotEmpty()){
                if(socio == "Socio"){
                    val affectedRows = dbHelper.updateSocio(id, name, surname, dni, aptoFisico)
                    if(affectedRows > 0){
                        Toast.makeText(this, "Socio editado exitosamente", Toast.LENGTH_SHORT).show()
                        edtName.text.clear()
                        edtSurname.text.clear()
                        edtDni.text.clear()
                    }else {
                        Toast.makeText(this, "No se realizo ningun  cambio", Toast.LENGTH_SHORT).show()}
                }else {
                    val affectedRows = dbHelper.updateNoSocio(id, name, surname, dni, aptoFisico)
                    if(affectedRows > 0){
                        Toast.makeText(this, "No socio editado exitosamente", Toast.LENGTH_SHORT).show()
                        edtName.text.clear()
                        edtSurname.text.clear()
                        edtDni.text.clear()
                    }else {
                        Toast.makeText(this, "No se realizo ningun cambio", Toast.LENGTH_SHORT).show()}
                }
            }else{
                Toast.makeText(this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show()}
        }



        btnBack.setOnClickListener{
            val intent = Intent(this, Buscar::class.java)
            startActivity(intent)
        }

    }


}