package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ReturnThis
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: MainDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        dbHelper = MainDatabaseHelper(this)
        val success = dbHelper.addAdmin("Admin", "12345")
        if (success < 0) {
            Toast.makeText(this, "Error al crear administrador", Toast.LENGTH_SHORT).show()
        }

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val usuEditText = findViewById<EditText>(R.id.edtUsuario)
        val passEditText = findViewById<EditText>(R.id.edtPass)


        btnLogin.setOnClickListener {

            val usu = usuEditText.text.toString()
            val pass = passEditText.text.toString()

            if (validateLogin(usu, pass)) {
                val intent = Intent(this, MainMenu::class.java)
                startActivity(intent)
            }else
            {
                Toast.makeText(this, "Usuario o contraseña invalidos", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun validateLogin(username: String, password: String): Boolean {
        val storedPass = dbHelper.getAdmin(username)
        if (storedPass == "-1") {
            return false
        } else if (storedPass != password) {
            return false
        } else {
            return true
        }
    }
}