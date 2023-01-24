package com.example.fotointent

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // Inicializamos dos códigos con los que vamos a reconocer que botón está presionando el usuario
    private val REQ_IMAGEN = 1 // Código de la foto
    private val REQ_NUMERO = 2 // Código de la suma

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Llamamos al botón foto y le colocamos un listener
        val foto : Button = findViewById(R.id.foto)
        foto.setOnClickListener{

            // Iniciamos el intento para que se abra la cámara y se tome la foto
            val intentFoto = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intentFoto, REQ_IMAGEN)
        }

        // Llamamos al botón suma y le colocamos un listener
        val suma : Button = findViewById(R.id.suma)
        suma.setOnClickListener{

            // Creamos un intent para llamar a la second activity
            val intent = Intent(this, SecondActivity::class.java)

            // Añadimos los números al intent
            intent.putExtra("prim",  Random.nextInt(1, 10))
            intent.putExtra("seg",  Random.nextInt(1, 10))

            // Iniciamos la actividad
            startActivityForResult(intent, REQ_NUMERO)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Si el código es el de la foto y los datos no son nulos
        if(requestCode == REQ_IMAGEN && data != null){

            // Buscamos el contenenido de los datos como un Bitmap
            val imageBitMap = data.extras!!.get("data") as Bitmap

            // Llamamos al ImageView y le colocamos la imagen como Bitmap
            val Foto : ImageView = findViewById(R.id.imagen)
            Foto.setImageBitmap(imageBitMap)
        }

        // Si el resultado de la actividad no fue el que se esperaba o los datos son nulos, retornamos a la actividad
        if(resultCode != Activity.RESULT_OK || data == null) return

        // Si el código es el de la suma
        if(requestCode == REQ_NUMERO){

            // Obtenemos los datos de la suma y de la respuesta del usuario
            val resul = intent.getIntExtra("suma", 0)
            val respuesta = intent.getIntExtra("resp", 0)

            // Llamamos al TextView donde le mostraremos al usuario si acertó
            val acierto : TextView = findViewById(R.id.acierto)

            // Si el resultado y la respuesta son iguales se le enseñará un mensaje de victoria
            if(Integer.valueOf(respuesta) == resul)
                acierto.text = "Respuesta correcta!"

            // En cualquier otro caso el mensaje será de derrota
            else
                acierto.text = "Respuesta incorrecta :("

            // Llamamos al TextView donde le enseñaremos el resultado preciso
            val resultado: TextView = findViewById(R.id.resultado)
            resultado.text = resul.toString()
        }
    }
}