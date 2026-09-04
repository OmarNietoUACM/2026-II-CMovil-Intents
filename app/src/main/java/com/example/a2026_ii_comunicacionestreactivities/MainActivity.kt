package com.example.a2026_ii_comunicacionestreactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var etLogin: EditText
    lateinit var etPasswd: EditText
    lateinit var btnAceptar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Se conectar a los objetos VIEW
        etLogin = findViewById<EditText>(R.id.main_login)
        etPasswd = findViewById<EditText>(R.id.main_password)
        btnAceptar = findViewById<Button>(R.id.main_boton)

        btnAceptar.setOnClickListener {

            val login = etLogin.text.toString()?:""
            val passwd = etPasswd.text.toString()?:""

            if (login.isBlank())
                Toast.makeText(this, "Se requiere ingresar el Usuario", Toast.LENGTH_LONG).show()
            else if (passwd.isBlank())
                Toast.makeText(this, "Se requiere ingresar el Password", Toast.LENGTH_LONG).show()
            else //Abrir una nueva actity
            {
                val intent = Intent(this, PricipalActivity::class.java)
                intent.putExtra(MAIN_LOGIN_KEY, login)
                intent.putExtra(MAIN_PASSWD_KEY, passwd)
                startActivity(intent)
            }
        }
    }
    companion object{
        const val  MAIN_LOGIN_KEY = "MAIN_LOGIN_KEY"
        const val  MAIN_PASSWD_KEY = "MAIN_PASSWD_KEY"
    }
}