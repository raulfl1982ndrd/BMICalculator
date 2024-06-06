package com.example.bmicalculator

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    lateinit var heightEditText : EditText
    lateinit var weightTextView : TextView
    lateinit var  minusButton: Button
    lateinit var  addButton: Button
    lateinit var descriptionTextview :TextView
    lateinit var resultTextView:TextView
    lateinit var estadoTextView:TextView
    lateinit var  calculateButton: Button
    lateinit var imageViewPanel: ImageView

    var height: Int = 120
    var weight: Int = 70

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("IMC","Se ha creado el MainActivity")
        heightEditText = findViewById(R.id.heightEditText)
        weightTextView = findViewById(R.id.weightTextView)
        minusButton = findViewById(R.id.minusButton)
        addButton = findViewById(R.id.plusButton)
        descriptionTextview = findViewById(R.id.descripcionTextView)
        resultTextView = findViewById(R.id.resultTextView)
        calculateButton = findViewById(R.id.calculateButton)
        estadoTextView = findViewById(R.id.estadoTextView)
        imageViewPanel = findViewById(R.id.imageViewPanel)
        setHeight()
        setWeight()
        //Barra de titulo de la aplicacion
        supportActionBar?.title = "Mi calculadores de IMC"
        supportActionBar?.subtitle = "Una descripcion cualquiera"
        minusButton.setOnClickListener{
            weight--
            setWeight()
            Log.i("IMC","He reducido el peso")
        }
        addButton.setOnClickListener{
            weight++
            setWeight()
            Log.i("IMC","He aumentado el peso")
        }
        calculateButton.setOnClickListener {
            height = heightEditText.text.toString().toInt()
            var result = weight / (height / 100f).pow(2)
            var descripcion: String = "IMC"

            if (result < 16.5) {
                descripcion = "Bajo peso severo"
                descriptionTextview.setTextColor(Color.parseColor("#FF0000"))
                resultTextView.setTextColor(Color.parseColor("#FF0000"))
                estadoTextView.text = "Aumente su peso"
                //imageViewPanel.setImageResource()
            }
            else if ((result >= 16.5) && (result < 18.5)){
                descripcion = "Bajo peso"
                descriptionTextview.setTextColor(Color.parseColor("#FF6C00"))
                resultTextView.setTextColor(Color.parseColor("#FF6C00"))
                estadoTextView.text = "Aumente su peso"
            }
            else if ((result >= 18.5) && (result <25)){
                descripcion = "Peso normal"
                descriptionTextview.setTextColor(Color.parseColor("#00FF78"))
                resultTextView.setTextColor(Color.parseColor("#00FF78"))
                estadoTextView.text = "Peso correcto"
            }
            else if ((result >= 25) && (result <30)){
                descripcion = "Sobrepeso"
                descriptionTextview.setTextColor(Color.parseColor("#FF6C00"))
                resultTextView.setTextColor(Color.parseColor("#FF6C00"))
                estadoTextView.text = "Reduzca su peso"
            }
            else if ((result >= 30) && (result <35)) {
                descripcion = "Obesidad tipo 1 (moderada)"
                descriptionTextview.setTextColor(Color.parseColor("#FF2E00"))
                resultTextView.setTextColor(Color.parseColor("#FF2E00"))
                estadoTextView.text = "Consulte con su medico"
            }
            else if ((result >= 35) && (result <40)) {
                descripcion = "Obesidad tipo 2 (severa)"
                descriptionTextview.setTextColor(Color.parseColor("#FF2300"))
                resultTextView.setTextColor(Color.parseColor("#FF2300"))
                estadoTextView.text = "Consulte con su medico"
            }
            else if ((result >= 40)) {
                descripcion = "Obesidad tipo 3 (mórbida)"
                descriptionTextview.setTextColor(Color.parseColor("#FF0000"))
                resultTextView.setTextColor(Color.parseColor("#FF0000"))
                estadoTextView.text = "Consulte con su medico"
            }
            descriptionTextview.text = descripcion.toString()
            resultTextView.text = result.toString()

        }

    };


    fun setHeight(){
        heightEditText.setText(height.toString())
    }
    fun setWeight(){
        weightTextView.text = "$weight Kg"
    }

    }
