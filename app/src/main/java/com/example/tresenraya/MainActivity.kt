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
        val bot:Bot = Bot(btnReiniciar)
        btnReiniciar.setOnClickListener { reiniciar(checkboxes, txtResult, bot) }
        checkBox1.setOnClickListener {
            if (checkBox2.isChecked && checkBox3.isChecked
                || checkBox4.isChecked && checkBox7.isChecked || checkBox5.isChecked
                && checkBox9.isChecked
            ) {
                victoria(txtResult)
            } else {
                bot.marcarBot(checkboxes, txtResult)
            }
        }
        checkBox2.setOnClickListener {
            if (checkBox1.isChecked && checkBox3.isChecked
                || checkBox5.isChecked && checkBox8.isChecked
            ) {
                victoria(txtResult)
            } else {
                bot.marcarBot(checkboxes, txtResult)
            }
        }
        checkBox3.setOnClickListener {
            if (checkBox2.isChecked && checkBox1.isChecked
                || checkBox6.isChecked && checkBox9.isChecked || checkBox5.isChecked
                && checkBox7.isChecked
            ) {
                victoria(txtResult)
            } else {
                bot.marcarBot(checkboxes, txtResult)
            }
        }
        checkBox4.setOnClickListener {
            if (checkBox5.isChecked && checkBox6.isChecked
                || checkBox1.isChecked && checkBox7.isChecked
            ) {
                victoria(txtResult)
            } else {
                bot.marcarBot(checkboxes, txtResult)
            }
        }
        checkBox5.setOnClickListener {
            if (checkBox4.isChecked && checkBox6.isChecked
                || checkBox2.isChecked && checkBox8.isChecked || checkBox1.isChecked
                && checkBox9.isChecked || checkBox3.isChecked && checkBox7.isChecked
            ) {
                victoria(txtResult)
            } else {
                bot.marcarBot(checkboxes, txtResult)
            }
        }
        checkBox6.setOnClickListener {
            if (checkBox4.isChecked && checkBox5.isChecked
                || checkBox3.isChecked && checkBox9.isChecked
            ) {
                victoria(txtResult)
            } else {
                bot.marcarBot(checkboxes, txtResult)
            }
        }
        checkBox7.setOnClickListener {
            if (checkBox8.isChecked && checkBox9.isChecked
                || checkBox1.isChecked && checkBox4.isChecked || checkBox5.isChecked
                && checkBox3.isChecked
            ) {
                victoria(txtResult)
            } else {
                bot.marcarBot(checkboxes, txtResult)
            }
        }
        checkBox8.setOnClickListener {
            if (checkBox7.isChecked && checkBox9.isChecked
                || checkBox2.isChecked && checkBox5.isChecked
            ) {
                victoria(txtResult)
            } else {
                bot.marcarBot(checkboxes, txtResult)
            }
        }
        checkBox9.setOnClickListener {
            if (checkBox8.isChecked && checkBox7.isChecked
                || checkBox3.isChecked && checkBox6.isChecked || checkBox5.isChecked
                && checkBox1.isChecked
            ) {
                victoria(txtResult)
            } else {
                bot.marcarBot(checkboxes, txtResult)
            }
        }

    }

        fun victoria(txtResult: TextView) {
            mensaje = "ganaste"
            txtResult.text = mensaje
            val btnReiniciar: Button = findViewById(R.id.button)
            btnReiniciar.visibility = View.VISIBLE
        }

        fun reiniciar(checkBoxes: ArrayList<CheckBox>, txtResult: TextView, bot: Bot) {
            txtResult.text = ""
            bot.marcadasBot =  arrayOf<Boolean>(false, false, false, false, false, false, false, false, false)
            for (x in 0..8) {
                checkBoxes[x].visibility = View.VISIBLE
                checkBoxes[x].isChecked = false
            }
            val btnReiniciar: Button = findViewById(R.id.button)
            btnReiniciar.visibility = View.INVISIBLE
        }

    }
