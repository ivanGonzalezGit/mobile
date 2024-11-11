package com.example.clubdeportivo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast

class Inscripcion : AppCompatActivity() {

    private lateinit var dbHelper: MainDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscripcion)

        dbHelper = MainDatabaseHelper(this)

        val socio = intent.getBooleanExtra("socio", false)

        val btnBack = findViewById<Button>(R.id.btnBack2)

        val registrarBtn = findViewById<Button>(R.id.btnRegistrar)
        val nameEdt = findViewById<EditText>(R.id.edtNombre)
        val surnameEdt = findViewById<EditText>(R.id.edtApellido)
        val dniEdt = findViewById<EditText>(R.id.edtDNI)
        val aptoFisicoRdb = findViewById<RadioButton>(R.id.rdbAptoFisico)

        registrarBtn.setOnClickListener{

            val name = nameEdt.text.toString()
            val surname = surnameEdt.text.toString()
            val dni = dniEdt.text.toString()
            val aptoFisico = aptoFisicoRdb.isChecked

            if(name.isNotEmpty() && surname.isNotEmpty() && dni.isNotEmpty()){
                if(socio){
                    val success = dbHelper.addSocio(name, surname, dni, aptoFisico)
                    if(success > 0){
                        Toast.makeText(this, "Socio registrado exitosamente", Toast.LENGTH_SHORT).show()
                        nameEdt.text.clear()
                        surnameEdt.text.clear()
                        dniEdt.text.clear()
                    }else {Toast.makeText(this, "Registro fallido", Toast.LENGTH_SHORT).show()}
                }else {
                    val success = dbHelper.addNoSocio(name, surname, dni, aptoFisico)
                    if(success > 0){
                        Toast.makeText(this, "No socio registrado exitosamente", Toast.LENGTH_SHORT).show()
                        nameEdt.text.clear()
                        surnameEdt.text.clear()
                        dniEdt.text.clear()
                    }else {Toast.makeText(this, "Registro fallido", Toast.LENGTH_SHORT).show()}
                }
            }else{Toast.makeText(this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show()}

        }
        btnBack.setOnClickListener{
            val intent = Intent(this, Continuar::class.java)
            startActivity(intent)
        }
    }
}