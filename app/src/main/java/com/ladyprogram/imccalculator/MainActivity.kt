package com.ladyprogram.imccalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    lateinit var heightEditText: EditText
    lateinit var weightEditText: EditText
    lateinit var calculationButton: Button
    lateinit var resultTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        heightEditText = findViewById(R.id.heightEditText)
        weightEditText = findViewById(R.id.weightEditText)
        calculationButton = findViewById(R.id.calculationButton)
        resultTextView = findViewById(R.id.resultTextView)

        heightEditText.setText("180")
        weightEditText.setText("70")


        calculationButton.setOnClickListener {
            val height = heightEditText.text.toString().toFloat()
            val weight = weightEditText.text.toString().toFloat()
            val result = weight / (height / 100).pow(2)

            println("Altura: " + heightEditText.text.toString())
            println("Peso: " + weightEditText.text.toString())

            resultTextView.text = "$result"
        }
    }
}