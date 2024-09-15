package com.example.kpi_kotlin_labs

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Отримання посилань на UI елементи
        val editH = findViewById<EditText>(R.id.editH)
        val editC = findViewById<EditText>(R.id.editC)
        val editS = findViewById<EditText>(R.id.editS)
        val editN = findViewById<EditText>(R.id.editN)
        val editO = findViewById<EditText>(R.id.editO)
        val editW = findViewById<EditText>(R.id.editW)
        val editA = findViewById<EditText>(R.id.editA)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        calculateButton.setOnClickListener {
            // Отримання значень з полів вводу
            val h = editH.text.toString().toDoubleOrNull() ?: 0.0
            val c = editC.text.toString().toDoubleOrNull() ?: 0.0
            val s = editS.text.toString().toDoubleOrNull() ?: 0.0
            val n = editN.text.toString().toDoubleOrNull() ?: 0.0
            val o = editO.text.toString().toDoubleOrNull() ?: 0.0
            val w = editW.text.toString().toDoubleOrNull() ?: 0.0
            val a = editA.text.toString().toDoubleOrNull() ?: 0.0

            // Розрахунок коефіцієнтів
            val krs = calculateKRS(w)
            val krg = calculateKRG(w, a)

            // Розрахунок сухої маси
            val hc = h * krs
            val cc = c * krs
            val sc = s * krs
            val nc = n * krs
            val oc = o * krs
            val ac = a * krs

            // Перевірка сухої маси
            val dryMassCheck = hc + cc + sc + nc + oc + ac

            // Розрахунок горючої маси
            val hg = h * krg
            val cg = c * krg
            val sg = s * krg
            val ng = n * krg
            val og = o * krg

            // Перевірка горючої маси
            val combustibleMassCheck = hg + cg + sg + ng + og

            // Розрахунок нижчої теплоти згоряння для робочої маси
            val heat = calculateLowerHeatingValue(c, h, o, s, w)

            // Формування результату для відображення
            val result = """
                Коефіцієнт переходу до сухої маси: $krs
                Коефіцієнт переходу до горючої маси: $krg
                
                Склад сухої маси: 
                H: $hc%, C: $cc%, S: $sc%, N: $nc%, O: $oc%, A: $ac
                Перевірка: $dryMassCheck%

                Склад горючої маси: 
                H: $hg%, C: $cg%, S: $sg%, N: $ng%, O: $og%
                Перевірка: $combustibleMassCheck%

                Нижча теплота згоряння для робочої маси: $heat кДж/кг
            """.trimIndent()

            resultTextView.text = result
        }
    }

    // Розрахунок коефіцієнта переходу до сухої маси
    private fun calculateKRS(w: Double): Double {
        return 100 / (100 - w)
    }

    // Розрахунок коефіцієнта переходу до горючої маси
    private fun calculateKRG(w: Double, a: Double): Double {
        return 100 / (100 - w - a)
    }

    // Розрахунок нижчої теплоти згоряння
    private fun calculateLowerHeatingValue(c: Double, h: Double, o: Double, s: Double, w: Double): Double {
        return 339 * c + 1030 * h - 108.8 * (o - s) - 25 * w
    }
}
