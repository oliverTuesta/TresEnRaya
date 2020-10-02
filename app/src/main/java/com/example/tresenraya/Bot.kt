package com.example.tresenraya

import android.graphics.Point
import android.widget.CheckBox
import android.widget.TextView

class Bot (val txtVictoriasBot: TextView){

    var mensaje = ""
    var marcadasBot = arrayOf(arrayOf(false, false, false), arrayOf(false, false, false), arrayOf(false, false, false))
    var victoriasBot: Int = 0

    fun marcarBot(checkBoxes: Array<Array<CheckBox>>, txtResult: TextView, modoExperto: Boolean) {
        var contadorBot = 0
        for (y in 0..2) {
            for (x in 0..2)
                if (marcadasBot[y][x]) {
                    contadorBot++
                }
        }
        if (contadorBot < 4) {
            val jugada: Point = nextBotGame(checkBoxes, modoExperto)
            marcadasBot[jugada.y][jugada.x] = true
            checkBoxes[jugada.y][jugada.x].isEnabled = false
        } else {
            empate(txtResult, checkBoxes)
        }

        if (isBotWin()) {
            victoriasBot++
            lose(txtResult, checkBoxes)
        }
    }

    fun isBotWin(): Boolean {
        var contadorD1 = 0
        var contadorD2 = 0
        var D2x = 2
        for (i in 0..2) {
            var contadorV = 0
            var contadorH = 0
            //diagonal 1
            if (marcadasBot[i][i]) {
                contadorD1++
                if (contadorD1 == 3) {
                    return true
                }
            }
            //diagonal 2
            if (marcadasBot[i][D2x]) {
                contadorD2++
                if (contadorD2 == 3) {
                    return true
                }
            }
            D2x--
            //horizontal
            for (x in 0..2){
                if (marcadasBot[i][x]) {
                    contadorH++
                    if (contadorH == 3) {
                        return true
                    }
                }
            }
            //vertical
            for (y in 0..2) {
                if (marcadasBot[y][i]) {
                    contadorV++
                    if (contadorV == 3) {
                        return true
                    }
                }
            }
        }
        return false
    }
    fun nextBotGame(checkBoxes: Array<Array<CheckBox>>, modoExperto: Boolean): Point {
        var jugada: Point
        var contadorMarcadas = 0
        for (y in 0..2) {
            for (x in 0..2)
                if (checkBoxes[y][x].isChecked) {
                    contadorMarcadas++
                }
        }
        var rango = atacar(checkBoxes)
        if (rango == arrayListOf<Point>() && modoExperto){
            rango = defender(checkBoxes)
            //rango = defender(checkBoxes)
        }
        var z = 0
        do {
            if (z >= 8 || rango.equals(arrayListOf<Point>())){
                rango = arrayListOf()
                for (y in 0..2){
                    for (x in 0..2){
                        rango.add(Point(x,y))
                    }
                }
            }
            jugada = rango.random()
            z++
        } while (checkBoxes[jugada.y][jugada.x].isChecked || marcadasBot[jugada.y][jugada.x])

        return jugada
    }

    fun atacar(checkBoxes: Array<Array<CheckBox>>): ArrayList<Point>{
        var rango = arrayListOf<Point>()
        //horizontal
        if (marcadasBot[0][0] && marcadasBot[0][1] && !checkBoxes[0][2].isChecked ||
            marcadasBot[0][0] && marcadasBot[0][2] && !checkBoxes[0][1].isChecked ||
            marcadasBot[0][1] && marcadasBot[0][2] && !checkBoxes[0][0].isChecked){
            rango = arrayListOf(Point(0,0), Point(1,0), Point(2, 0))
        }else if (marcadasBot[1][0] && marcadasBot[1][1] && !checkBoxes[1][2].isChecked ||
            marcadasBot[1][0] && marcadasBot[1][2] && !checkBoxes[1][1].isChecked ||
            marcadasBot[1][1] && marcadasBot[1][2] && !checkBoxes[1][0].isChecked){
            rango = arrayListOf(Point(0,1), Point(1,1), Point(2, 1))
        }else if (marcadasBot[2][0] && marcadasBot[2][1] && !checkBoxes[2][2].isChecked ||
            marcadasBot[2][0] && marcadasBot[2][2] && !checkBoxes[2][1].isChecked ||
            marcadasBot[2][1] && marcadasBot[2][2] && !checkBoxes[2][0].isChecked){
            rango = arrayListOf(Point(0,2), Point(1,2), Point(2, 2))
        }
        //vertical
        else if (marcadasBot[0][0] && marcadasBot[1][0] && !checkBoxes[2][0].isChecked ||
            marcadasBot[0][0] && marcadasBot[2][0] && !checkBoxes[1][0].isChecked ||
            marcadasBot[1][0] && marcadasBot[2][0] && !checkBoxes[0][0].isChecked){
            rango = arrayListOf(Point(0,0), Point(0,1), Point(0, 2))
        }else if (marcadasBot[0][1] && marcadasBot[1][1] && !checkBoxes[2][1].isChecked ||
            marcadasBot[0][1] && marcadasBot[2][1] && !checkBoxes[1][1].isChecked ||
            marcadasBot[1][1] && marcadasBot[2][1] && !checkBoxes[0][1].isChecked){
            rango = arrayListOf(Point(1,0), Point(1,1), Point(1, 2))
        }else if (marcadasBot[0][2] && marcadasBot[1][2] && !checkBoxes[2][2].isChecked ||
            marcadasBot[0][2] && marcadasBot[2][2] && !checkBoxes[1][2].isChecked ||
            marcadasBot[1][2] && marcadasBot[2][2] && !checkBoxes[0][2].isChecked){
            rango = arrayListOf(Point(2,0), Point(2,1), Point(2, 2))
        }
        //diagonal 1
        else if (marcadasBot[0][0] && marcadasBot[1][1] && !checkBoxes[2][2].isChecked ||
            marcadasBot[0][0] && marcadasBot[2][2] && !checkBoxes[1][1].isChecked ||
            marcadasBot[1][1] && marcadasBot[2][2] && !checkBoxes[0][0].isChecked){
            rango = arrayListOf(Point(0,0), Point(1,1), Point(2, 2))
        }else if (marcadasBot[0][2] && marcadasBot[1][1] && !checkBoxes[2][0].isChecked ||
            marcadasBot[0][2] && marcadasBot[2][0] && !checkBoxes[1][1].isChecked ||
            marcadasBot[1][1] && marcadasBot[2][0] && !checkBoxes[0][2].isChecked){
            rango = arrayListOf(Point(2,0), Point(1,1), Point(0, 2))
        }
        return rango
    }
    fun defender(checkBoxes: Array<Array<CheckBox>>): ArrayList<Point>{
        var rango = arrayListOf<Point>()
        var i = 0
        for (y in 0..2) {
            for (x in 0..2){
                if (checkBoxes[y][x].isChecked){
                    i++
                }
            }
        }
        if (i == 1 && !checkBoxes[1][1].isChecked){                     //empieza en centro
            rango = arrayListOf(Point(1,1))
        }
        else if (i == 1 && checkBoxes[1][1].isChecked) {                //no empieza en centro
            for (y in 0..2 step 2){
                for (x in 0..2 step 2){
                    rango.add(Point(x,y))
                }
            }
        }else if (i == 2 && checkBoxes[0][0].isChecked && checkBoxes[2][2].isChecked && marcadasBot[1][1] ||        // si marca en diagonal
            i == 2 && checkBoxes[0][2].isChecked && checkBoxes[2][0].isChecked && marcadasBot[1][1]){
            rango = arrayListOf(Point(1,0), Point(0,1), Point(2,1), Point(2,1))
        }

        else if (i >= 2 && checkBoxes[0][0].isChecked && checkBoxes[0][1].isChecked && !marcadasBot[0][2] ||
             i >= 2 && checkBoxes[0][0].isChecked && checkBoxes[0][2].isChecked && !marcadasBot[0][1] ||          // evitar 0 linea horizonta
             i >= 2 && checkBoxes[0][1].isChecked && checkBoxes[0][2].isChecked && !marcadasBot[0][0]) {
                for (x in 0..2){
                    rango.add(Point(x,0))
                }
        }else if (i >= 2 && checkBoxes[1][0].isChecked && checkBoxes[1][1].isChecked && !marcadasBot[1][2] ||
            i >= 2 && checkBoxes[1][0].isChecked && checkBoxes[1][2].isChecked && !marcadasBot[1][1] ||          // evitar 1 linea horizonta
            i >= 2 && checkBoxes[1][1].isChecked && checkBoxes[1][2].isChecked && !marcadasBot[1][0]) {
            for (x in 0..2){
                rango.add(Point(x,1))
            }
        }else if (i >= 2 && checkBoxes[2][0].isChecked && checkBoxes[2][1].isChecked && !marcadasBot[2][2] ||
            i >= 2 && checkBoxes[2][0].isChecked && checkBoxes[2][2].isChecked && !marcadasBot[2][1] ||          // evitar 2 linea horizonta
            i >= 2 && checkBoxes[2][1].isChecked && checkBoxes[2][2].isChecked && !marcadasBot[2][0]) {
            for (x in 0..2){
                rango.add(Point(x,2))
            }
        }else if (i >= 2 && checkBoxes[0][0].isChecked && checkBoxes[1][0].isChecked && !marcadasBot[2][0] ||        // evitar 0 linea vertical
             i >= 2 && checkBoxes[0][0].isChecked && checkBoxes[2][0].isChecked && !marcadasBot[1][0] ||
             i >= 2 && checkBoxes[1][0].isChecked && checkBoxes[2][0].isChecked && !marcadasBot[0][0]){
            for (y in 0..2){
                rango.add(Point(0,y))
            }
        }else if (i >= 2 && checkBoxes[0][1].isChecked && checkBoxes[1][1].isChecked && !marcadasBot[2][1] ||        // evitar 1 linea vertical
            i >= 2 && checkBoxes[0][1].isChecked && checkBoxes[2][1].isChecked && !marcadasBot[1][1] ||
            i >= 2 && checkBoxes[1][1].isChecked && checkBoxes[2][1].isChecked && !marcadasBot[0][1]){
            for (y in 0..2){
                rango.add(Point(1,y))
            }
        }else if (i >= 2 && checkBoxes[0][2].isChecked && checkBoxes[1][2].isChecked && !marcadasBot[2][2] ||        // evitar 2 linea vertical
            i >= 2 && checkBoxes[0][2].isChecked && checkBoxes[2][2].isChecked && !marcadasBot[1][2] ||
            i >= 2 && checkBoxes[1][2].isChecked && checkBoxes[2][2].isChecked && !marcadasBot[0][2]){
            for (y in 0..2){
                rango.add(Point(2,y))
            }
        }else if (i >= 2 && checkBoxes[0][0].isChecked && checkBoxes[1][1].isChecked && !marcadasBot[2][2] ||        // evitar D1
            i >= 2 && checkBoxes[0][0].isChecked && checkBoxes[2][2].isChecked && !marcadasBot[1][1] ||
            i >= 2 && checkBoxes[1][1].isChecked && checkBoxes[2][2].isChecked && !marcadasBot[0][0]){
                for (j in 0..2){
                    rango.add(Point(j,j))
                }
        }else if (i >= 2 && checkBoxes[0][2].isChecked && checkBoxes[1][1].isChecked && !marcadasBot[2][0] ||        // evitar D2
            i >= 2 && checkBoxes[0][2].isChecked && checkBoxes[2][0].isChecked && !marcadasBot[1][1] ||
            i >= 2 && checkBoxes[1][1].isChecked && checkBoxes[2][0].isChecked && !marcadasBot[0][2]){
            var x = 2
            for (y in 0..2){
                    rango.add(Point(x,y))
                x--
            }
        }
        /**else if (i == 2 && checkBoxes[7].isChecked && checkBoxes[3].isChecked
            || i == 2 && checkBoxes[7].isChecked && checkBoxes[5].isChecked){
            rango = arrayOf(6,8)
        }else if (i == 2 && checkBoxes[1].isChecked && checkBoxes[3].isChecked
            || i == 2 && checkBoxes[1].isChecked && checkBoxes[5].isChecked){
            rango = arrayOf(0,2)
        }else if (i == 2 && checkBoxes[0].isChecked && checkBoxes[8].isChecked
            || i == 2 && checkBoxes[2].isChecked && checkBoxes[6].isChecked){          // si marca en dso esquinas opuestas
            rango = arrayOf(1,3,5,7)
        }else if (i==1 && !checkBoxes[4].isChecked){
            rango = arrayOf(4)
        }else if (checkBoxes[4].isChecked && i == 1){                                   //si empieza en el centro
            rango = arrayOf(0, 2, 6, 8)
        }else if (i == 2 && checkBoxes[0].isChecked && checkBoxes[4].isChecked && marcadasBot[8]
            || checkBoxes[8].isChecked && checkBoxes[4].isChecked && i == 2 && marcadasBot[0]
            || checkBoxes[2].isChecked && checkBoxes[4].isChecked && i == 2 && marcadasBot[6]
            || checkBoxes[4].isChecked && checkBoxes[6].isChecked && i == 2 && marcadasBot[2]){
            rango = arrayOf(0,2,6,8)
        }
        */
        return rango
    }
    fun lose(txtResult: TextView, checkBoxes: Array<Array<CheckBox>>) {
        txtVictoriasBot.text = ""+victoriasBot
        mensaje = "perdiste"
        txtResult.text = mensaje
        for (y in 0..2){
            for (x in 0..2){
                checkBoxes[y][x].isClickable = false
            }
        }
    }

    fun empate(txtResult: TextView, checkBoxes: Array<Array<CheckBox>>) {
        mensaje = "empate"
        txtResult.text = mensaje
        for (y in 0..2){
            for (x in 0..2){
                checkBoxes[y][x].isClickable = false
            }
        }
    }

}