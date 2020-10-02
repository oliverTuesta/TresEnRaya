package com.example.tresenraya

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var mensaje = ("ganaste")
    var victorias: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtResult: TextView = findViewById(R.id.textResult)
        val checkBox0: CheckBox = findViewById(R.id.checkBox1)
        val checkBox1: CheckBox = findViewById(R.id.checkBox2)
        val checkBox2: CheckBox = findViewById(R.id.checkBox3)
        val checkBox3: CheckBox = findViewById(R.id.checkBox4)
        val checkBox4: CheckBox = findViewById(R.id.checkBox5)
        val checkBox5: CheckBox = findViewById(R.id.checkBox6)
        val checkBox6: CheckBox = findViewById(R.id.checkBox7)
        val checkBox7: CheckBox = findViewById(R.id.checkBox8)
        val checkBox8: CheckBox = findViewById(R.id.checkBox9)
        val btnNewGame: Button = findViewById(R.id.btnNewGame)
        val btnReiniciar: Button = findViewById(R.id.btnReiniciar)
        val txtVictorias: TextView = findViewById(R.id.txtVictorias)
        val txtVictoriasBot: TextView = findViewById(R.id.txtVictoriasBot)
        val modoExperto: Switch = findViewById(R.id.switch2)

        val lados = 3

        val x1 = arrayOf(checkBox0, checkBox1, checkBox2)
        val x2 = arrayOf(checkBox3, checkBox4, checkBox5)
        val x3 = arrayOf(checkBox6, checkBox7, checkBox8)

        val checkBoxes2D = arrayOf(x1, x2, x3)

        val bot:Bot = Bot(txtVictoriasBot)
        modoExperto.setOnClickListener { reiniciar(checkBoxes2D, txtVictorias, txtVictoriasBot, bot, txtResult) }
        btnReiniciar.setOnClickListener { reiniciar(checkBoxes2D, txtVictorias, txtVictoriasBot, bot, txtResult) }
        btnNewGame.setOnClickListener { newGame(checkBoxes2D, txtResult, bot) }
        checkBox0.setOnClickListener {
            if (isVictoria(checkBoxes2D)) {
                victoria(txtResult, txtVictorias, checkBoxes2D)
            } else {
                bot.marcarBot(checkBoxes2D, txtResult, modoExperto.isChecked)
            }
            checkBox0.isClickable = false
        }
        checkBox1.setOnClickListener {
            if (isVictoria(checkBoxes2D)) {
                victoria(txtResult, txtVictorias, checkBoxes2D)
            } else {
                bot.marcarBot(checkBoxes2D, txtResult, modoExperto.isChecked)
            }
            checkBox1.isClickable = false
        }
        checkBox2.setOnClickListener {
            if (isVictoria(checkBoxes2D)) {
                victoria(txtResult, txtVictorias, checkBoxes2D)
            } else {
                bot.marcarBot(checkBoxes2D, txtResult, modoExperto.isChecked)
            }
            checkBox2.isClickable = false
        }
        checkBox3.setOnClickListener {
            if (isVictoria(checkBoxes2D)) {
                victoria(txtResult, txtVictorias, checkBoxes2D)
            } else {
                bot.marcarBot(checkBoxes2D, txtResult, modoExperto.isChecked)
            }
            checkBox3.isClickable = false
        }
        checkBox4.setOnClickListener {
            if (isVictoria(checkBoxes2D)) {
                victoria(txtResult, txtVictorias, checkBoxes2D)
            } else {
                bot.marcarBot(checkBoxes2D, txtResult, modoExperto.isChecked)
            }
            checkBox4.isClickable = false
        }
        checkBox5.setOnClickListener {
            if (isVictoria(checkBoxes2D)) {
                victoria(txtResult, txtVictorias, checkBoxes2D)
            } else {
                bot.marcarBot(checkBoxes2D, txtResult, modoExperto.isChecked)
            }
            checkBox5.isClickable = false
        }
        checkBox6.setOnClickListener {
            if (isVictoria(checkBoxes2D)) {
                victoria(txtResult, txtVictorias, checkBoxes2D)
            } else {
                bot.marcarBot(checkBoxes2D, txtResult, modoExperto.isChecked)
            }
            checkBox6.isClickable = false
        }
        checkBox7.setOnClickListener {
            if (isVictoria(checkBoxes2D)) {
                victoria(txtResult, txtVictorias, checkBoxes2D)
            } else {
                bot.marcarBot(checkBoxes2D, txtResult, modoExperto.isChecked)
            }
            checkBox7.isClickable = false
        }
        checkBox8.setOnClickListener {
            if (isVictoria(checkBoxes2D)) {
                victoria(txtResult, txtVictorias, checkBoxes2D)
            } else {
                bot.marcarBot(checkBoxes2D, txtResult, modoExperto.isChecked)
            }
            checkBox8.isClickable = false
        }
    }

        fun victoria(txtResult: TextView, txtVictorias: TextView, checkBoxes2D: Array<Array<CheckBox>>) {
            victorias++
            txtVictorias.text = ""+victorias
            mensaje = "ganaste"
            txtResult.text = mensaje
            for (y in 0..2) {
                for (x in 0..2){
                    checkBoxes2D[y][x].isClickable = false
                }
            }
        }

        fun newGame(checkBoxes2D: Array<Array<CheckBox>>, txtResult: TextView, bot: Bot) {
            txtResult.text = ""
            bot.marcadasBot =  arrayOf(arrayOf(false, false, false),
                arrayOf(false, false, false),
                arrayOf(false, false, false))
            for (y in 0..2) {
                for (x in 0..2){
                    checkBoxes2D[y][x].isEnabled = true
                    checkBoxes2D[y][x].isChecked = false
                    checkBoxes2D[y][x].isClickable = true
                }
            }
        }
    fun reiniciar(checkBoxes2D: Array<Array<CheckBox>>, txtVictorias: TextView,
                  txtVictoriasBot: TextView, bot: Bot, txtResult: TextView){
        victorias = 0
        bot.victoriasBot = 0
        txtVictorias.text = ""+victorias
        txtVictoriasBot.text = ""+bot.victoriasBot
        newGame(checkBoxes2D, txtResult, bot)
    }
    fun isVictoria(checkBoxes2D: Array<Array<CheckBox>>): Boolean {
        var contadorD1 = 0
        var contadorD2 = 0
        var x = 2
        for (i in 0..2) {
            var contadorV = 0
            var contadorH = 0
            //diagonal 1
            if (checkBoxes2D[i][i].isChecked) {
                contadorD1++
                if (contadorD1 == 3) {
                    return true
                }
            }
            //diagonal 2
            if (checkBoxes2D[i][x].isChecked) {
                contadorD2++
                if (contadorD2 == 3) {
                    return true
                }
            }
            x--
            //horizontal
            for (x in 0..2){
                if (checkBoxes2D[i][x].isChecked) {
                    contadorH++
                    if (contadorH == 3) {
                        return true
                    }
                }
            }
            //vertical
            for (y in 0..2) {
                if (checkBoxes2D[y][i].isChecked) {
                    contadorV++
                    if (contadorV == 3) {
                        return true
                    }
                }
            }
        }
        return false
    }
    }
