package com.example.tresenraya

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var mensaje = ("ganaste")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtResult: TextView = findViewById(R.id.textResult)
        val checkBox1: CheckBox = findViewById(R.id.checkBox1)
        val checkBox2: CheckBox = findViewById(R.id.checkBox2)
        val checkBox3: CheckBox = findViewById(R.id.checkBox3)
        val checkBox4: CheckBox = findViewById(R.id.checkBox4)
        val checkBox5: CheckBox = findViewById(R.id.checkBox5)
        val checkBox6: CheckBox = findViewById(R.id.checkBox6)
        val checkBox7: CheckBox = findViewById(R.id.checkBox7)
        val checkBox8: CheckBox = findViewById(R.id.checkBox8)
        val checkBox9: CheckBox = findViewById(R.id.checkBox9)
        val btnReiniciar: Button = findViewById(R.id.button)
        btnReiniciar.visibility = View.INVISIBLE

        val checkboxes = arrayListOf(
            checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6
            , checkBox7, checkBox8, checkBox9
        )

        btnReiniciar.setOnClickListener { reiniciar(checkboxes, txtResult) }


        checkBox1.setOnClickListener {
            if (checkBox2.isChecked && checkBox3.isChecked
                || checkBox4.isChecked && checkBox7.isChecked || checkBox5.isChecked
                && checkBox9.isChecked
            ) {
                victoria(txtResult)
            } else {
                marcarBot(checkboxes, txtResult)
            }
        }
        checkBox2.setOnClickListener {
            if (checkBox1.isChecked && checkBox3.isChecked
                || checkBox5.isChecked && checkBox8.isChecked
            ) {
                victoria(txtResult)
            } else {
                marcarBot(checkboxes, txtResult)
            }
        }
        checkBox3.setOnClickListener {
            if (checkBox2.isChecked && checkBox1.isChecked
                || checkBox6.isChecked && checkBox9.isChecked || checkBox5.isChecked
                && checkBox7.isChecked
            ) {
                victoria(txtResult)
            } else {
                marcarBot(checkboxes, txtResult)
            }
        }
        checkBox4.setOnClickListener {
            if (checkBox5.isChecked && checkBox6.isChecked
                || checkBox1.isChecked && checkBox7.isChecked
            ) {
                victoria(txtResult)
            } else {
                marcarBot(checkboxes, txtResult)
            }
        }
        checkBox5.setOnClickListener {
            if (checkBox4.isChecked && checkBox6.isChecked
                || checkBox2.isChecked && checkBox8.isChecked || checkBox1.isChecked
                && checkBox9.isChecked || checkBox3.isChecked && checkBox7.isChecked
            ) {
                victoria(txtResult)
            } else {
                marcarBot(checkboxes, txtResult)
            }
        }
        checkBox6.setOnClickListener {
            if (checkBox4.isChecked && checkBox5.isChecked
                || checkBox3.isChecked && checkBox9.isChecked
            ) {
                victoria(txtResult)
            } else {
                marcarBot(checkboxes, txtResult)
            }
        }
        checkBox7.setOnClickListener {
            if (checkBox8.isChecked && checkBox9.isChecked
                || checkBox1.isChecked && checkBox4.isChecked || checkBox5.isChecked
                && checkBox3.isChecked
            ) {
                victoria(txtResult)
            } else {
                marcarBot(checkboxes, txtResult)
            }
        }
        checkBox8.setOnClickListener {
            if (checkBox7.isChecked && checkBox9.isChecked
                || checkBox2.isChecked && checkBox5.isChecked
            ) {
                victoria(txtResult)
            } else {
                marcarBot(checkboxes, txtResult)
            }
        }
        checkBox9.setOnClickListener {
            if (checkBox8.isChecked && checkBox7.isChecked
                || checkBox3.isChecked && checkBox6.isChecked || checkBox5.isChecked
                && checkBox1.isChecked
            ) {
                victoria(txtResult)
            } else {
                marcarBot(checkboxes, txtResult)
            }
        }

    }

    private var marcadasBot =
        arrayOf<Boolean>(false, false, false, false, false, false, false, false, false)

    fun marcarBot(checkBoxes: ArrayList<CheckBox>, txtResult: TextView) {

        var contadorCasillasMarcadas = 0
        for (i in 0..8) {
            if (checkBoxes[i].isChecked) {
                contadorCasillasMarcadas++
            }
        }
        var contadorBot = 1
        for (i: Boolean in marcadasBot) {
            if (i) {
                contadorBot++
            }
        }
        if (contadorCasillasMarcadas >= 5 || contadorBot >= 5) {
            empate(txtResult)
        }

        if (contadorBot < 5) {
            val jugada: Int = nextBotGame(checkBoxes)

            marcadasBot[jugada] = true
            checkBoxes[jugada].visibility = View.INVISIBLE
        } else {
            empate(txtResult)
        }

        if (isBotWin()) {
            lose(txtResult)
        }
    }

    fun isBotWin(): Boolean {
        /**
         * horizontal
         */
        for (x in 0..6 step 3) {
            var contador = 0
            for (i in 0..2) {
                if (marcadasBot[x + i]) {
                    contador++
                    if (contador == 3) {
                        return true
                    }
                }
            }
        }
        /**
         * vertical
         */
        for (x in 0..2) {
            var contadorVertical = 0
            for (i in 0..6 step 3) {
                if (marcadasBot[x + i]) {
                    contadorVertical++
                    if (contadorVertical == 3) {
                        return true
                    }
                }
            }
        }
        /**
         * diagonaes
         */
                if(marcadasBot[2] && marcadasBot[4] && marcadasBot[6]
                    || marcadasBot[0] && marcadasBot[4] && marcadasBot[8]) {
                    return true
        }
        return false
    }

        fun lose(txtResult: TextView) {
            mensaje = "perdiste"
            txtResult.text = mensaje
            val btnReiniciar: Button = findViewById(R.id.button)
            btnReiniciar.visibility = View.VISIBLE
        }

        fun empate(txtResult: TextView) {
            mensaje = "empate"
            txtResult.text = mensaje
            val btnReiniciar: Button = findViewById(R.id.button)
            btnReiniciar.visibility = View.VISIBLE
        }

        fun victoria(txtResult: TextView) {
            mensaje = "ganaste"
            txtResult.text = mensaje
            val btnReiniciar: Button = findViewById(R.id.button)
            btnReiniciar.visibility = View.VISIBLE
        }

        fun nextBotGame(checkBoxes: ArrayList<CheckBox>): Int {
            var numRandom: Int = 0
            var jugada = 0
            var rango = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
            var z = 0

            var i = 0
            for (x in 0..8) {
                if (checkBoxes[x].isChecked) {
                    i++
                }
            }

            if (checkBoxes[4].isChecked && i < 2){
                rango = arrayOf(0, 2, 6, 8)
            }else if (i >= 2 && checkBoxes[2].isChecked && checkBoxes[5].isChecked
                || checkBoxes[2].isChecked && checkBoxes[8].isChecked && i >= 2
                || checkBoxes[5].isChecked && checkBoxes[8].isChecked && i >= 2){
                rango = arrayOf(2, 5, 8)
            }else if (i >= 2 && checkBoxes[1].isChecked && checkBoxes[4].isChecked
                || checkBoxes[1].isChecked && checkBoxes[7].isChecked && i >= 2
                || checkBoxes[4].isChecked && checkBoxes[7].isChecked && i >= 2){
                rango = arrayOf(1, 4, 7)
            }else if (i >= 2 && checkBoxes[0].isChecked && checkBoxes[3].isChecked
                || checkBoxes[0].isChecked && checkBoxes[6].isChecked && i >= 2
                || checkBoxes[3].isChecked && checkBoxes[6].isChecked && i >= 2){
                rango = arrayOf(0, 3, 6)
            }else if (i >= 2 && checkBoxes[6].isChecked && checkBoxes[7].isChecked
                || checkBoxes[6].isChecked && checkBoxes[8].isChecked && i >= 2
                || checkBoxes[7].isChecked && checkBoxes[8].isChecked && i >= 2){
                rango = arrayOf(6, 7, 8)
            }
            else if (i >= 2 && checkBoxes[3].isChecked && checkBoxes[4].isChecked
                        || checkBoxes[3].isChecked && checkBoxes[5].isChecked && i >= 2
                        || checkBoxes[4].isChecked && checkBoxes[5].isChecked && i >= 2){
                rango = arrayOf(3, 4, 5)
            }
            else if (i >= 2 && checkBoxes[0].isChecked && checkBoxes[1].isChecked
                || checkBoxes[0].isChecked && checkBoxes[2].isChecked && i >= 2
                || checkBoxes[1].isChecked && checkBoxes[2].isChecked && i >= 2) {
                rango = arrayOf(0, 1, 2)
            }else if (i >= 2 && checkBoxes[4].isChecked && checkBoxes[8].isChecked
                || checkBoxes[4].isChecked && checkBoxes[0].isChecked && i >= 2
                || checkBoxes[0].isChecked && checkBoxes[8].isChecked && i >= 2) {
                rango = arrayOf(0, 4, 8)
            }else if (i >= 2 && checkBoxes[2].isChecked && checkBoxes[4].isChecked
                || checkBoxes[2].isChecked && checkBoxes[6].isChecked && i >= 2
                || checkBoxes[4].isChecked && checkBoxes[6].isChecked && i >= 2){
                rango = arrayOf(2, 4, 6)
            } else {
                rango = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
            }
            do {
                numRandom = rango.random()
                z++
                if (z > 10) {
                    numRandom = (0..8).random()
                    continue
                }
            } while (checkBoxes[numRandom].isChecked || marcadasBot[numRandom])
            jugada = numRandom
            return jugada
        }

        fun reiniciar(checkBoxes: ArrayList<CheckBox>, txtResult: TextView) {
            txtResult.text = ""
            marcadasBot =
                arrayOf<Boolean>(false, false, false, false, false, false, false, false, false)
            for (x in 0..8) {
                checkBoxes[x].visibility = View.VISIBLE
                checkBoxes[x].isChecked = false
            }
            val btnReiniciar: Button = findViewById(R.id.button)
            btnReiniciar.visibility = View.INVISIBLE
        }

    }
