package com.example.fotointent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        // recoge los valores de tipo int pasado en el Intent
        val prim = intent.getIntExtra("prim", 0)
        val seg = intent.getIntExtra("seg", 0)

        val num : TextView = findViewById(R.id.numeros)
        num.text = prim.toString() + " + " + seg.toString()

        val resp : EditText = findViewById(R.id.respuesta)

        val suma = prim + seg

        val volver : Button = findViewById(R.id.volver)
        volver.setOnClickListener{

            val respuesta = Integer.parseInt(resp.text.toString())

            // crear un intent para llamar a la second activity
            val intent = Intent(this, MainActivity::class.java)

            //añade el resultado de la suma al intent
            intent.putExtra("suma", suma)
            intent.putExtra("resp", respuesta)

            // iniciamos la actividad
            startActivity(intent)
        }
    }
}