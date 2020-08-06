package com.example.tresenraya

import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class Bot (val btnReiniciar: Button){

    var mensaje = ""
    var marcadasBot = arrayOf<Boolean>(false, false, false, false, false, false, false, false, false)

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
        /**
         * atque
         */
        if (marcadasBot[2] && marcadasBot[4] && !checkBoxes[6].isChecked
            || marcadasBot[2] && marcadasBot[6] && !checkBoxes[4].isChecked
            || marcadasBot[4] && marcadasBot[6] && !checkBoxes[2].isChecked) {
            rango = arrayOf(6, 7, 8)
        } else if (marcadasBot[0] && marcadasBot[4] && !checkBoxes[8].isChecked
            || marcadasBot[0] && marcadasBot[8] && !checkBoxes[4].isChecked
            || marcadasBot[4] && marcadasBot[8] && !checkBoxes[0].isChecked) {
            rango = arrayOf(0, 4, 8)
        } else if (marcadasBot[2] && marcadasBot[5] && !checkBoxes[8].isChecked
            || marcadasBot[2] && marcadasBot[8] && !checkBoxes[5].isChecked
            || marcadasBot[5] && marcadasBot[8] && !checkBoxes[2].isChecked) {
            rango = arrayOf(6, 7, 8)
        } else if (marcadasBot[1] && marcadasBot[4] && !checkBoxes[7].isChecked
            || marcadasBot[1] && marcadasBot[7] && !checkBoxes[4].isChecked
            || marcadasBot[4] && marcadasBot[7] && !checkBoxes[1].isChecked
        ) {
            rango = arrayOf(1, 4, 7)
        } else if (marcadasBot[0] && marcadasBot[3] && !checkBoxes[6].isChecked
            || marcadasBot[0] && marcadasBot[6] && !checkBoxes[3].isChecked
            || marcadasBot[3] && marcadasBot[6] && !checkBoxes[0].isChecked
        ) {
            rango = arrayOf(0, 3, 6)
        } else if (marcadasBot[6] && marcadasBot[7] && !checkBoxes[8].isChecked
            || marcadasBot[6] && marcadasBot[8] && !checkBoxes[7].isChecked
            || marcadasBot[7] && marcadasBot[8] && !checkBoxes[8].isChecked
        ) {
            rango = arrayOf(6, 7, 8)
        } else if (marcadasBot[3] && marcadasBot[4] && !checkBoxes[5].isChecked
            || marcadasBot[3] && marcadasBot[5] && !checkBoxes[4].isChecked
            || marcadasBot[4] && marcadasBot[5] && !checkBoxes[3].isChecked
        ) {
            rango = arrayOf(3, 4, 5)
        } else if (marcadasBot[0] && marcadasBot[1] && !checkBoxes[2].isChecked
            || marcadasBot[0] && marcadasBot[2] && !checkBoxes[1].isChecked
            || marcadasBot[1] && marcadasBot[2] && !checkBoxes[0].isChecked
        ) {
            rango = arrayOf(0, 1, 2)
        }else {
            rango = defender(checkBoxes)
        }
        do {
            numRandom = rango.random()
            z++
            if (z > 15) {
                numRandom = (0..8).random()
                continue
            }
        } while (checkBoxes[numRandom].isChecked || marcadasBot[numRandom])
        jugada = numRandom
        return jugada
    }

    fun defender(checkBoxes: ArrayList<CheckBox>): Array<Int>{
        var i = 0
        for (x in 0..8) {
            if (checkBoxes[x].isChecked) {
                i++
            }
        }
        var rango = arrayOf(0,1,2,3,4,5,6,7,8)
        if (checkBoxes[0].isChecked && i==1 || checkBoxes[2].isChecked && i==1 // si empieza en una esquina
            || checkBoxes[6].isChecked && i==1|| checkBoxes[8].isChecked&& i==1){
            rango = arrayOf(4)
        }else if (checkBoxes[1].isChecked && i==1 || checkBoxes[3].isChecked && i==1 //si no empieza en una esquina
            || checkBoxes[5].isChecked && i==1 || checkBoxes[7].isChecked&& i==1){
            rango= arrayOf(4)
        }else if (checkBoxes[4].isChecked && i < 2){                                   //si empieza en el centro
            rango = arrayOf(0, 2, 6, 8)
        }else if (i >= 2 && checkBoxes[2].isChecked && checkBoxes[5].isChecked         // evitar 3ra linea vertical
            || checkBoxes[2].isChecked && checkBoxes[8].isChecked && i >= 2
            || checkBoxes[5].isChecked && checkBoxes[8].isChecked && i >= 2){
            rango = arrayOf(2, 5, 8)
        }else if (i >= 2 && checkBoxes[1].isChecked && checkBoxes[4].isChecked          // evitar 2da linea vertical
            || checkBoxes[1].isChecked && checkBoxes[7].isChecked && i >= 2
            || checkBoxes[4].isChecked && checkBoxes[7].isChecked && i >= 2){
            rango = arrayOf(1, 4, 7)
        }else if (i >= 2 && checkBoxes[0].isChecked && checkBoxes[3].isChecked          // evitar 1ra linea vertical
            || checkBoxes[0].isChecked && checkBoxes[6].isChecked && i >= 2
            || checkBoxes[3].isChecked && checkBoxes[6].isChecked && i >= 2){
            rango = arrayOf(0, 3, 6)
        }else if (i >= 2 && checkBoxes[6].isChecked && checkBoxes[7].isChecked          // evitar 3ra linea horizontal
            || checkBoxes[6].isChecked && checkBoxes[8].isChecked && i >= 2
            || checkBoxes[7].isChecked && checkBoxes[8].isChecked && i >= 2){
            println("aaa")
            rango = arrayOf(6, 7, 8)
        }
        else if (i >= 2 && checkBoxes[3].isChecked && checkBoxes[4].isChecked           // evitar 2da linea horizontal
            || checkBoxes[3].isChecked && checkBoxes[5].isChecked && i >= 2
            || checkBoxes[4].isChecked && checkBoxes[5].isChecked && i >= 2){
            rango = arrayOf(3, 4, 5)
        }
        else if (i >= 2 && checkBoxes[0].isChecked && checkBoxes[1].isChecked           // evitar 1ra linea horizontal
            || checkBoxes[0].isChecked && checkBoxes[2].isChecked && i >= 2
            || checkBoxes[1].isChecked && checkBoxes[2].isChecked && i >= 2) {
            rango = arrayOf(0, 1, 2)
        }else if (i > 2 && checkBoxes[0].isChecked && checkBoxes[8].isChecked){         // evitar 3ra linea horizontal
            rango = arrayOf(1,3,5,7)
        }else if (i >= 2 && checkBoxes[4].isChecked && checkBoxes[8].isChecked          // evitar diagonal 1
            || checkBoxes[4].isChecked && checkBoxes[0].isChecked && i >= 2
            || checkBoxes[0].isChecked && checkBoxes[8].isChecked && i >= 2) {
            rango = arrayOf(0, 4, 8)
        }else if (i >= 2 && checkBoxes[2].isChecked && checkBoxes[4].isChecked          // evitar diagonal 2
            || checkBoxes[2].isChecked && checkBoxes[6].isChecked && i >= 2
            || checkBoxes[4].isChecked && checkBoxes[6].isChecked && i >= 2){
            rango = arrayOf(2, 4, 6)
        }
        return rango
    }

    fun lose(txtResult: TextView) {
        mensaje = "perdiste"
        txtResult.text = mensaje
        btnReiniciar.visibility = View.VISIBLE
    }

    fun empate(txtResult: TextView) {
        mensaje = "empate"
        txtResult.text = mensaje
        btnReiniciar.visibility = View.VISIBLE
    }

}