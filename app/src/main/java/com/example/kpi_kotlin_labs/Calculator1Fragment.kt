package com.example.kpi_kotlin_labs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class Calculator1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calculator1, container, false)

        // Initialize input fields
        val editH = view.findViewById<EditText>(R.id.editH)
        val editC = view.findViewById<EditText>(R.id.editC)
        val editS = view.findViewById<EditText>(R.id.editS)
        val editN = view.findViewById<EditText>(R.id.editN)
        val editO = view.findViewById<EditText>(R.id.editO)
        val editW = view.findViewById<EditText>(R.id.editW)
        val editA = view.findViewById<EditText>(R.id.editA)
        val calculateButton = view.findViewById<Button>(R.id.calculateButton)
        val resultTextView = view.findViewById<TextView>(R.id.resultTextView)

        // Set the onClickListener for the calculate button
        calculateButton.setOnClickListener {
            // Get the input values
            val h = editH.text.toString().toDoubleOrNull() ?: 0.0
            val c = editC.text.toString().toDoubleOrNull() ?: 0.0
            val s = editS.text.toString().toDoubleOrNull() ?: 0.0
            val n = editN.text.toString().toDoubleOrNull() ?: 0.0
            val o = editO.text.toString().toDoubleOrNull() ?: 0.0
            val w = editW.text.toString().toDoubleOrNull() ?: 0.0
            val a = editA.text.toString().toDoubleOrNull() ?: 0.0

            // Calculate KRS (coefficient transition to dry mass)
            val krs = calculateKRS(w)

            // Calculate KRG (coefficient transition to combustible mass)
            val krg = calculateKRG(w, a)

            // Calculate dry mass composition
            val hc = h * krs
            val cc = c * krs
            val sc = s * krs
            val nc = n * krs
            val oc = o * krs
            val ac = a * krs

            // Calculate combustible mass composition
            val hg = h * krg
            val cg = c * krg
            val sg = s * krg
            val ng = n * krg
            val og = o * krg

            // Calculate the lower heating value for the working mass
            val lowerHeatingValue = calculateLowerHeatingValue(c, h, o, s, w)

            // Display results in a formatted string
            val result = """
                1.1. КРС (для сухої маси): ${String.format("%.2f", krs)}
                1.2. КРГ (для горючої маси): ${String.format("%.2f", krg)}
                1.3. Склад сухої маси:
                H: ${String.format("%.2f", hc)}%, C: ${String.format("%.2f", cc)}%, S: ${String.format("%.2f", sc)}%, N: ${String.format("%.2f", nc)}%, O: ${String.format("%.2f", oc)}%, A: ${String.format("%.2f", ac)}%
                
                1.4. Склад горючої маси:
                H: ${String.format("%.2f", hg)}%, C: ${String.format("%.2f", cg)}%, S: ${String.format("%.2f", sg)}%, N: ${String.format("%.2f", ng)}%, O: ${String.format("%.2f", og)}%

                1.5. Нижча теплота згоряння для робочої маси: ${String.format("%.2f", lowerHeatingValue)} кДж/кг
            """.trimIndent()

            resultTextView.text = result
        }

        return view
    }

    // Функція для обчислення КРС (коефіцієнт переходу до сухої маси)
    private fun calculateKRS(w: Double): Double {
        return 100 / (100 - w)
    }

    // Функція для обчислення КРГ (коефіцієнт переходу до горючої маси)
    private fun calculateKRG(w: Double, a: Double): Double {
        return 100 / (100 - w - a)
    }

    // Функція для обчислення нижчої теплоти згоряння
    private fun calculateLowerHeatingValue(c: Double, h: Double, o: Double, s: Double, w: Double): Double {
        return 339 * c + 1030 * h - 108.8 * (o - s) - 25 * w
    }
}
