package com.example.kpi_kotlin_labs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class Calculator2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculator2, container, false)

        // EditText fields for input
        val editC = view.findViewById<EditText>(R.id.editC)
        val editH = view.findViewById<EditText>(R.id.editH)
        val editO = view.findViewById<EditText>(R.id.editO)
        val editS = view.findViewById<EditText>(R.id.editS)
        val editQ = view.findViewById<EditText>(R.id.editQ)
        val editW = view.findViewById<EditText>(R.id.editW)
        val editA = view.findViewById<EditText>(R.id.editA)
        val editV = view.findViewById<EditText>(R.id.editV)

        // Button and TextView for results
        val calculateButton = view.findViewById<Button>(R.id.calculateButton)
        val resultTextView = view.findViewById<TextView>(R.id.resultTextView)

        // OnClickListener for calculation
        calculateButton.setOnClickListener {
            // Get values from input fields
            val c = editC.text.toString().toDoubleOrNull() ?: 0.0
            val h = editH.text.toString().toDoubleOrNull() ?: 0.0
            val o = editO.text.toString().toDoubleOrNull() ?: 0.0
            val s = editS.text.toString().toDoubleOrNull() ?: 0.0
            val q = editQ.text.toString().toDoubleOrNull() ?: 0.0
            val w = editW.text.toString().toDoubleOrNull() ?: 0.0
            val a = editA.text.toString().toDoubleOrNull() ?: 0.0
            val v = editV.text.toString().toDoubleOrNull() ?: 0.0

            // Calculate elemental composition for the working mass
            val carbon = c * (100 - w - a) / 100
            val hydrogen = h * (100 - w - a) / 100
            val oxygen = o * (100 - w - a) / 100
            val sulfur = s * (100 - w - a) / 100
            val vanadium = v * (100 - w) / 100

            // Calculate the recalculated heat of combustion (Qir)
            val qir = q * (100 - w - a) / 100

            // Format the result string
            // Форматування рядка результату
            val result = """ 
                Вуглець (C): $carbon%
                Водень (H): $hydrogen%
                Кисень (O): $oxygen%
                Сірка (S): $sulfur%
                Ванадій (V): $vanadium мг/кг
                Теплота згорання (Qir): $qir МДж/кг
            """.trimIndent()


            // Display the result in the TextView
            resultTextView.text = result
        }

        return view
    }
}
