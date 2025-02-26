package com.ladyprogram.imccalculator

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView
import com.google.android.material.slider.Slider
import java.util.Locale
import kotlin.math.pow
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {

    lateinit var heightTextView: TextView
    lateinit var heightSlider: Slider

    lateinit var weightEditText: EditText
    lateinit var weightAddButton: Button
    lateinit var weightMinusButton: Button

    lateinit var calculationButton: Button
    lateinit var resultTextView: TextView
    lateinit var descriptionTextView: TextView

    lateinit var resultCardView: MaterialCardView

    //lateinit var weightEditText: EditText

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

        weightEditText = findViewById(R.id.weightEditText)
        weightAddButton = findViewById(R.id.weightAddButton)
        weightMinusButton = findViewById(R.id.weightMinusButton)


        calculationButton = findViewById(R.id.calculationButton)
        resultTextView = findViewById(R.id.resultTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)

        resultCardView = findViewById(R.id.resultCardView)


        heightSlider.addOnChangeListener { slider, value, fromUser ->
            heightTextView.text = "${value.toInt()} cm"
        }

        weightAddButton.setOnClickListener {
            weight ++
            if (weight > 300) {
                weight = 300f
            }
            weightEditText.setText("${weight.toInt()}")
        }


        weightMinusButton.setOnClickListener {
            weight --
            if (weight < 1) {
                weight = 1f
            }
            weightEditText.setText("${weight.toInt()}")

        }
        weightEditText.addTextChangedListener {
            if (weightEditText.text.toString().isNotEmpty()) {
                weight = weightEditText.text.toString().toFloat()
            }
        }


        //heightEditText.setText("180")
        //weightEditText.setText("70")

        calculationButton.setOnClickListener {
            // val result = weight / (height / 100).pow(2)

            //val height = 0f//heightEditText.text.toString().toFloat()
            //val weight = 0f//weightEditText.text.toString().toFloat()

            resultCardView.visibility = View.VISIBLE

            val result = weight / (height / 100).pow(2)

            //resultTextView.text = "$result"
            resultTextView.text = String.format(Locale.getDefault(),"%.2f", result)

            // Con String.format le decimos cuántos decimales queremos que nos calcule.
            // El locale.getDefault mira el idioma del dispositivo y lo aplica

            //COLOR RANGOS IMC

            var colorId = 0
            var textId = 0

            when (result) {
                in 0f..<18.5f -> {
                    //resultTextView.setTextColor(getColor(R.color.imc_underweight))
                    colorId = R.color.imc_underweight
                    textId = R.string.imc_underweight
                }

                in 18.5f..<25f -> {
                    colorId = R.color.imc_normal
                    textId = R.string.imc_normal
                }

                in 25f..<30f -> {
                    colorId = R.color.imc_overweight
                    textId = R.string.imc_overweight
                }

                in 30f..<35f -> {
                    colorId = R.color.imc_obesity1
                    textId = R.string.imc_obesity1
                    showObesityDialog()
                }

                in 35f..<40f -> {
                    colorId = R.color.imc_obesity2
                    textId = R.string.imc_obesity2
                    showObesityDialog()
                }

                else -> {
                    colorId = R.color.imc_obesity3
                    textId = R.string.imc_obesity3
                    showObesityDialog()
                }
            }

            resultTextView.setTextColor(getColor(colorId))
            descriptionTextView.text = getString(textId)

        }
    }

    fun showObesityDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.Obesity_alert_title)
            .setMessage(R.string.Obesity_alert_message)
            .setPositiveButton(R.string.Obesity_alert_button, {dialog, which ->
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://blog.ibv.csic.es/combatir-obesidad-consejos/"))
                startActivity(browserIntent);
            })
            .setNegativeButton(android.R.string.cancel, null)
            .show()


    }
}