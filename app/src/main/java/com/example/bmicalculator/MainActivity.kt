package com.example.bmicalculator

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var welcomeTextView : TextView
    lateinit var  clickButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Va a la vista del activity y busca el componentes visual
        // tenemos la referencia al componente visual
        welcomeTextView = findViewById(R.id.welcomeTextView)
        clickButton = findViewById(R.id.clickButton)
        //CAmbiamos el texto y al hacer click cambiamos el e¡texto otra vez
        welcomeTextView.text = getString(R.string.main_activity_welcome_text)
        clickButton.setOnClickListener{
            welcomeTextView.text = getString(R.string.main_activity_click_text)
            welcomeTextView.setBackgroundColor(Color.parseColor("#FF0000"))
            clickButton.setBackgroundColor(Color.parseColor("#FF0000"));
        };

    }
}