package com.example.a2026_ii_comunicacionestreactivities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a2026_ii_comunicacionestreactivities.MainActivity.Companion.MAIN_LOGIN_KEY
import com.example.a2026_ii_comunicacionestreactivities.MainActivity.Companion.MAIN_PASSWD_KEY

class PricipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_pricipal)

        val mensaje = findViewById<TextView>(R.id.tv_mesaje)
        if(intent != null)
        {
            //Se recibe los datos del Intent
            val login = intent.getStringExtra(MainActivity.MAIN_LOGIN_KEY)
            val passwd = intent.getStringExtra(MainActivity.MAIN_PASSWD_KEY)
            if (!login.equals(PricipalActivity.login) || !passwd.equals(PricipalActivity.passwd))
                mensaje.text = "El usuario o la contraseña son incorrectos"
            else
                mensaje.text = "Has inciado seción!"
        }

        //-------------------------------------------------
        //Se cierra la Activity y regresa a la Activity previa en el Stack
        //-------------------------------------------------
        val boton = findViewById<Button>(R.id.boton2)
        boton.setOnClickListener {
            finish()
        }

        //---------------------------------------------
        //Se abre la p+agina web de WhatsApp para enviar
        // o compartir texto o imágenes
        //---------------------------------------------
        val botonWhatsAppWeb = findViewById<Button>(R.id.botonWhatsAppWeb)
        botonWhatsAppWeb.setOnClickListener {

            // Se abre el navegador con la pagina de WhatsApp
            val numero = "5512345678" //Número de registro de WhatsApp
            val uri = Uri.parse("https://wa.me/" + numero)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        //---------------------------------------------
        //Se abre directamente la aplicación de WhatsApp para enviar
        // o compartir texto o imágenes
        //---------------------------------------------
        val botonWhatsApp = findViewById<Button>(R.id.botonWhatsApp)
        botonWhatsApp.setOnClickListener {

            val sendIntent = Intent(Intent.ACTION_SEND)
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Este el el texto que se enviará a la aplicación."
            )
            sendIntent.setType("text/plain")
            sendIntent.setPackage("com.whatsapp")
            if (sendIntent.resolveActivity(packageManager) != null)
                startActivity(sendIntent)
            else Toast.makeText(this,
                "La aplicción WhatsApp no está instalada",
                Toast.LENGTH_LONG).show()

        }

        //---------------------------------------------
        //Si varias aplicaciones coinciden con la acción ACTION_SEND
        // Se abrirá el dialogo Agente de Resolución de Intent para
        // que se seleccione una aplicación destino
        //En el siguiente código se muestra este ejemplo
        //---------------------------------------------
        val botonSendAgen = findViewById<Button>(R.id.botonSendAgen)
        botonSendAgen.setOnClickListener {
            val sendIntent =  Intent(Intent.ACTION_SEND)
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Este el el texto que se enviará a la aplicación.")
            sendIntent.setType("text/plain")
            startActivity(sendIntent)
        }

        //---------------------------------------------
        //Se puede utilizar Android Sharesheet
        // en lugar del Agente de Resolución de Intent
        // para seleccionar una aplicación destino
        //tiene mejores funcionalidades para desarrolladores
        //En el siguiente código se muestra este ejemplo
        //---------------------------------------------

        val botonSharesheet = findViewById<Button>(R.id.botonSharesheet)
        botonSharesheet.setOnClickListener {

            val sendIntent =  Intent(Intent.ACTION_SEND)
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Este es el texto que se debe enviar")
            sendIntent.setType("text/plain")

            var shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent);

        }



    }

    companion object{
        val login = "Juan"
        val  passwd = "12345678"
    }
}