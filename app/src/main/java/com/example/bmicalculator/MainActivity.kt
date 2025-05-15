package com.example.bmicalculator

import android.annotation.SuppressLint
import android.graphics.Color
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    lateinit var heightTextView : TextView
    lateinit var heightSlider : Slider
    lateinit var heightEditText : EditText
    lateinit var weightTextView : TextView
    lateinit var  minusButton: Button
    lateinit var  addButton: Button
    lateinit var descriptionTextview :TextView
    lateinit var resultTextView:TextView
    lateinit var estadoTextView:TextView
    lateinit var  calculateButton: Button
    lateinit var imageViewPanel: ImageView

    var height: Int = 170
    var weight: Int = 70

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("IMC","Se ha creado el MainActivity")
        heightTextView = findViewById(R.id.heightTextView)
        heightSlider = findViewById(R.id.heightSlider)
        heightEditText = findViewById(R.id.heightEditText)
        weightTextView = findViewById(R.id.weightTextView)
        minusButton = findViewById(R.id.minusButton)
        addButton = findViewById(R.id.plusButton)
        descriptionTextview = findViewById(R.id.descripcionTextView)
        resultTextView = findViewById(R.id.resultTextView)
        calculateButton = findViewById(R.id.calculateButton)
        estadoTextView = findViewById(R.id.estadoTextView)
        imageViewPanel = findViewById(R.id.imagenImageView)
        setHeight()
        setWeight()
        //Barra de titulo de la aplicacion
        supportActionBar?.title = "Calculadora de IMC"
        supportActionBar?.subtitle = "Revisa tu IMC"

        //heightSlider.addOnChangeListener(heightSlider)
        heightSlider.value = height.toFloat()

        heightSlider.addOnChangeListener { _, value, _ ->
            height = value.toInt()
            setHeight()
        }
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
            val descripctionColor: Int?

            //Los rangos se podrian hacer con when
            /*When(result){}
                in 0f <= ..<=18.5f-->{
                    descripcion = getString(R.string.under_weight)
                    descriptionColor = getColor(R.color.light_weight)
                }
                in 18.5f <= ..<=25f-->{
                    descripcion = getString(R.string.normal_weight)
                    descriptionColor = getColor(R.color.normal_weight)
                }
                in 25f <= ..<=30f-->{
                    descripcion = getString(R.string.over_weight)
                    descriptionColor = getColor(R.color.over_weight)
                }
                else->{
                    descripcion = getString(R.string.ovesity)
                    descriptionColor = getColor(R.color.obesity)
                }
             */
            if (result < 16.5) {
                descripcion = "Bajo peso severo"
                descriptionTextview.setTextColor(Color.parseColor("#FF0000"))
                resultTextView.setTextColor(Color.parseColor("#FF0000"))
                estadoTextView.text = "Aumente su peso"
                //imageViewPanel.setImageResource()
                imageViewPanel.setImageDrawable  (getDrawable(R.drawable.extremadelgadez))
            }
            else if ((result >= 16.5) && (result < 18.5)){
                descripcion = "Bajo peso"
                descriptionTextview.setTextColor(Color.parseColor("#FF6C00"))
                resultTextView.setTextColor(Color.parseColor("#FF6C00"))
                estadoTextView.text = "Aumente su peso"
                imageViewPanel.setImageDrawable  (getDrawable(R.drawable.extremadelgadez))
            }
            else if ((result >= 18.5) && (result <25)){
                descripcion = "Peso normal"
                descriptionTextview.setTextColor(Color.parseColor("#00FF78"))
                resultTextView.setTextColor(Color.parseColor("#00FF78"))
                estadoTextView.text = "Peso correcto"
                imageViewPanel.setImageDrawable  (getDrawable(R.drawable.pesosaludable))

            }
            else if ((result >= 25) && (result <30)){
                descripcion = "Sobrepeso"
                descriptionTextview.setTextColor(Color.parseColor("#FF6C00"))
                resultTextView.setTextColor(Color.parseColor("#FF6C00"))
                estadoTextView.text = "Reduzca su peso"
                imageViewPanel.setImageDrawable  (getDrawable(R.drawable.obesidad))
            }
            else if ((result >= 30) && (result <35)) {
                descripcion = "Obesidad tipo 1 (moderada)"
                descriptionTextview.setTextColor(Color.parseColor("#FF2E00"))
                resultTextView.setTextColor(Color.parseColor("#FF2E00"))
                estadoTextView.text = "Consulte con su medico"
                imageViewPanel.setImageDrawable  (getDrawable(R.drawable.obesidad))
            }
            else if ((result >= 35) && (result <40)) {
                descripcion = "Obesidad tipo 2 (severa)"
                descriptionTextview.setTextColor(Color.parseColor("#FF2300"))
                resultTextView.setTextColor(Color.parseColor("#FF2300"))
                estadoTextView.text = "Consulte con su medico"
                imageViewPanel.setImageDrawable  (getDrawable(R.drawable.obesidad))
            }
            else if ((result >= 40)) {
                descripcion = "Obesidad tipo 3 (mórbida)"
                descriptionTextview.setTextColor(Color.parseColor("#FF0000"))
                resultTextView.setTextColor(Color.parseColor("#FF0000"))
                estadoTextView.text = "Consulte con su medico"
                imageViewPanel.setImageDrawable  (getDrawable(R.drawable.obesidad))
            }
            descriptionTextview.text = descripcion.toString()
            //Limitar el numero de decimales que muestra el resultTextView
            var decimalFormat = DecimalFormat("#.##")
            resultTextView.text = decimalFormat.format(result)
            //resultTextView.setTextColor(descripctionColor)
            //descriptionTextview.setTextColor(descripctionColor)
        }

    };


    fun setHeight(){
        heightEditText.setText(height.toString())
        heightTextView.text = "$height cm"
    }
    fun setWeight(){
        weightTextView.text = "$weight Kg"
    }


}