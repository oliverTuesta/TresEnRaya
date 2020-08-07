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
        val checkBox1: CheckBox = findViewById(R.id.checkBox1)
        val checkBox2: CheckBox = findViewById(R.id.checkBox2)
        val checkBox3: CheckBox = findViewById(R.id.checkBox3)
        val checkBox4: CheckBox = findViewById(R.id.checkBox4)
        val checkBox5: CheckBox = findViewById(R.id.checkBox5)
        val checkBox6: CheckBox = findViewById(R.id.checkBox6)
        val checkBox7: CheckBox = findViewById(R.id.checkBox7)
        val checkBox8: CheckBox = findViewById(R.id.checkBox8)
        val checkBox9: CheckBox = findViewById(R.id.checkBox9)
        val btnNewGame: Button = findViewById(R.id.btnNewGame)
        val btnReiniciar: Button = findViewById(R.id.btnReiniciar)
        val txtVictorias: TextView = findViewById(R.id.txtVictorias)
        val txtVictoriasBot: TextView = findViewById(R.id.txtVictoriasBot)
        val modoExperto: Switch = findViewById(R.id.switch2)
        val checkboxes = arrayListOf(
            checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6
            , checkBox7, checkBox8, checkBox9)
        val bot:Bot = Bot(txtVictoriasBot)
        modoExperto.setOnClickListener { reiniciar(checkboxes, txtVictorias, txtVictoriasBot, bot, txtResult) }
        btnReiniciar.setOnClickListener { reiniciar(checkboxes, txtVictorias, txtVictoriasBot, bot, txtResult) }
        btnNewGame.setOnClickListener { newGame(checkboxes, txtResult, bot) }
        checkBox1.setOnClickListener {
            if (checkBox2.isChecked && checkBox3.isChecked
                || checkBox4.isChecked && checkBox7.isChecked || checkBox5.isChecked
                && checkBox9.isChecked
            ) {
                victoria(txtResult, txtVictorias, checkboxes)
            } else {
                bot.marcarBot(checkboxes, txtResult, modoExperto.isChecked)
            }
            checkBox1.isClickable = false
        }
        checkBox2.setOnClickListener {
            if (checkBox1.isChecked && checkBox3.isChecked
                || checkBox5.isChecked && checkBox8.isChecked
            ) {
                victoria(txtResult, txtVictorias, checkboxes)
            } else {
                bot.marcarBot(checkboxes, txtResult, modoExperto.isChecked)
            }
            checkBox2.isClickable = false
        }
        checkBox3.setOnClickListener {
            if (checkBox2.isChecked && checkBox1.isChecked
                || checkBox6.isChecked && checkBox9.isChecked || checkBox5.isChecked
                && checkBox7.isChecked
            ) {
                victoria(txtResult, txtVictorias, checkboxes)
            } else {
                bot.marcarBot(checkboxes, txtResult, modoExperto.isChecked)
            }
            checkBox3.isClickable = false
        }
        checkBox4.setOnClickListener {
            if (checkBox5.isChecked && checkBox6.isChecked
                || checkBox1.isChecked && checkBox7.isChecked
            ) {
                victoria(txtResult, txtVictorias, checkboxes)
            } else {
                bot.marcarBot(checkboxes, txtResult, modoExperto.isChecked)
            }
            checkBox4.isClickable = false
        }
        checkBox5.setOnClickListener {
            if (checkBox4.isChecked && checkBox6.isChecked
                || checkBox2.isChecked && checkBox8.isChecked || checkBox1.isChecked
                && checkBox9.isChecked || checkBox3.isChecked && checkBox7.isChecked
            ) {
                victoria(txtResult, txtVictorias, checkboxes)
            } else {
                bot.marcarBot(checkboxes, txtResult, modoExperto.isChecked)
            }
            checkBox5.isClickable = false
        }
        checkBox6.setOnClickListener {
            if (checkBox4.isChecked && checkBox5.isChecked
                || checkBox3.isChecked && checkBox9.isChecked
            ) {
                victoria(txtResult, txtVictorias, checkboxes)
            } else {
                bot.marcarBot(checkboxes, txtResult, modoExperto.isChecked)
            }
            checkBox6.isClickable = false
        }
        checkBox7.setOnClickListener {
            if (checkBox8.isChecked && checkBox9.isChecked
                || checkBox1.isChecked && checkBox4.isChecked || checkBox5.isChecked
                && checkBox3.isChecked
            ) {
                victoria(txtResult, txtVictorias, checkboxes)
            } else {
                bot.marcarBot(checkboxes, txtResult, modoExperto.isChecked)
            }
            checkBox7.isClickable = false
        }
        checkBox8.setOnClickListener {
            if (checkBox7.isChecked && checkBox9.isChecked
                || checkBox2.isChecked && checkBox5.isChecked
            ) {
                victoria(txtResult, txtVictorias, checkboxes)
            } else {
                bot.marcarBot(checkboxes, txtResult, modoExperto.isChecked)
            }
            checkBox8.isClickable = false
        }
        checkBox9.setOnClickListener {
            if (checkBox8.isChecked && checkBox7.isChecked
                || checkBox3.isChecked && checkBox6.isChecked || checkBox5.isChecked
                && checkBox1.isChecked
            ) {
                victoria(txtResult, txtVictorias, checkboxes)
            } else {
                bot.marcarBot(checkboxes, txtResult, modoExperto.isChecked)
            }
            checkBox9.isClickable = false
        }

    }

        fun victoria(txtResult: TextView, txtVictorias: TextView, checkBoxes: ArrayList<CheckBox>) {
            victorias++
            txtVictorias.text = ""+victorias
            mensaje = "ganaste"
            txtResult.text = mensaje
            for (checkBox: CheckBox in checkBoxes){
                checkBox.isClickable = false
            }
        }

        fun newGame(checkBoxes: ArrayList<CheckBox>, txtResult: TextView, bot: Bot) {
            txtResult.text = ""
            bot.marcadasBot =  arrayOf<Boolean>(false, false, false, false, false, false, false, false, false)
            for (x in 0..8) {
                checkBoxes[x].isEnabled = true
                checkBoxes[x].isChecked = false
            }
            for (checkBox: CheckBox in checkBoxes){
                checkBox.isClickable = true
            }
        }
    fun reiniciar(checkBoxes: ArrayList<CheckBox>, txtVictorias: TextView, txtVictoriasBot: TextView, bot: Bot, txtResult: TextView) {
        victorias = 0
        bot.victoriasBot = 0
        txtVictorias.text = ""+victorias
        txtVictoriasBot.text = ""+bot.victoriasBot
        newGame(checkBoxes, txtResult, bot)
    }
    }
