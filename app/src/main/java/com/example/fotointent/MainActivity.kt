package com.example.fotointent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resul = intent.getIntExtra("suma", 0)
        val respuesta = intent.getIntExtra("resp", 0)


        val acierto : TextView = findViewById(R.id.acierto)
        if(resul == 0)
            acierto.text = "Presiona un botón"

        else if(Integer.valueOf(respuesta) == resul)
            acierto.text = "Respuesta correcta!"

        else
            acierto.text = "Respuesta incorrecta :("

        val resultado: TextView = findViewById(R.id.resultado)
        if (resul != 0) {
            resultado.text = resul.toString()
        }

        val suma : Button = findViewById(R.id.suma)
        suma.setOnClickListener{

            // crear un intent para llamar a la second activity
            val intent = Intent(this, SecondActivity::class.java)

            // añade los números al intent
            intent.putExtra("prim",  Random.nextInt(1, 10))
            intent.putExtra("seg",  Random.nextInt(1, 10))

            // iniciamos la actividad
            startActivity(intent)
        }
    }
}