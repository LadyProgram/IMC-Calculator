package com.ladyprogram.imccalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    lateinit var heightTextView: TextView
    lateinit var heightSlider: Slider

    lateinit var weightTextView: TextView
    lateinit var weightAddButton: Button
    lateinit var weightMinusButton: Button

    lateinit var calculationButton: Button
    lateinit var resultTextView: TextView

    var weight = 75.0f
    var height = 170.0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        heightTextView = findViewById(R.id.heightTextView)
        heightSlider = findViewById(R.id.heightSlider)

        weightTextView = findViewById(R.id.weightTextView)
        weightAddButton = findViewById(R.id.weightAddButton)
        weightMinusButton = findViewById(R.id.weightMinusButton)





        calculationButton = findViewById(R.id.calculationButton)
        resultTextView = findViewById(R.id.resultTextView)

        heightSlider.addOnChangeListener { slider, value, fromUser ->
            heightTextView.text = "${value.toInt()} cm"
        }

        weightAddButton.setOnClickListener {
            weight ++
            weightTextView.text = "${weight.toInt()} kg"
        }

        weightMinusButton.setOnClickListener {
            weight --
            weightTextView.text = "${weight.toInt()} kg"
        }


        //heightEditText.setText("180")
        //weightEditText.setText("70")

        calculationButton.setOnClickListener {
            // val result = weight / (height / 100).pow(2)

            //val height = 0f//heightEditText.text.toString().toFloat()
            //val weight = 0f//weightEditText.text.toString().toFloat()

            val result = weight / (height / 100).pow(2)

            //resultTextView.text = "$result"
            resultTextView.text = String.format(Locale.getDefault(),"%.2f", result)

            // Con String.format le decimos cuántos decimales queremos que nos calcule.
            // El locale.getDefault mira el idioma del dispositivo y lo aplica
        }
    }
}