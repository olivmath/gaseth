package com.example.gaseth

import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    var gasPrice = 0
    val ratioFactor = 10000.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obter componentes
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val txtGasPrice = findViewById<TextView>(R.id.txtGasPrice)
        val txtResult = findViewById<TextView>(R.id.txtResult)
        val btnLaunch = findViewById<Button>(R.id.btnLaunch)

        // Definir ouvinte da barra
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                txtResult.text = "Selecione o preço"
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                txtResult.text = "..."
                gasPrice = progress

                txtGasPrice.text = "R$ " + formatValue(gasPrice / ratioFactor)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                txtResult.text = "Pressione Calcular"
            }
        })

        // Definir ação do botão
        btnLaunch.setOnClickListener {
            val ethanolPrice = (gasPrice * 0.7)
            txtResult.text = "Use álcool se custar menos que: R$ " + formatValue(ethanolPrice / ratioFactor)
        }
    }

    private fun formatValue(number: Double): String {
        return String.format(Locale.FRANCE, "%.2f", number)
    }
}
