package com.example.fotointent

import android.app.Activity
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

            // Introducimos los valores de la suma y la respuesta del usuario en el intent
            intent.putExtra("suma", suma)
            intent.putExtra("resp", respuesta)

            // Seteamos el resultado de esta actividad y devolvemos los valores
            setResult(Activity.RESULT_OK, intent)

            // La terminamos
            finish()
        }
    }
}