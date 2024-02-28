package com.example.kalkfrei

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var interimResult = 0f
    var currentOperation = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // calculation textview
        val interimResultView = findViewById<TextView>(R.id.interimResultView)
        val calculationView = findViewById<TextView>(R.id.calculationView)

        // Number buttons
        val buttonNR0 = findViewById<Button>(R.id.buttonNR0)
        val buttonNR1 = findViewById<Button>(R.id.buttonNR1)
        val buttonNR2 = findViewById<Button>(R.id.buttonNR2)
        val buttonNR3 = findViewById<Button>(R.id.buttonNR3)
        val buttonNR4 = findViewById<Button>(R.id.buttonNR4)
        val buttonNR5 = findViewById<Button>(R.id.buttonNR5)
        val buttonNR6 = findViewById<Button>(R.id.buttonNR6)
        val buttonNR7 = findViewById<Button>(R.id.buttonNR7)
        val buttonNR8 = findViewById<Button>(R.id.buttonNR8)
        val buttonNR9 = findViewById<Button>(R.id.buttonNR9)

        // operation buttons
        val buttonMult = findViewById<Button>(R.id.buttonMult)
        val buttonDiv = findViewById<Button>(R.id.buttonDiv)
        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
        val buttonMinus = findViewById<Button>(R.id.buttonMinus)

        // other buttons
        val buttonEquals = findViewById<Button>(R.id.buttonEquals)
        val buttonDelete = findViewById<Button>(R.id.buttonDelete)


        // Number onclick listeners
        setNumberButtonOnClickListener(buttonNR0, calculationView, interimResultView)
        setNumberButtonOnClickListener(buttonNR1, calculationView, interimResultView)
        setNumberButtonOnClickListener(buttonNR2, calculationView, interimResultView)
        setNumberButtonOnClickListener(buttonNR3, calculationView, interimResultView)
        setNumberButtonOnClickListener(buttonNR4, calculationView, interimResultView)
        setNumberButtonOnClickListener(buttonNR5, calculationView, interimResultView)
        setNumberButtonOnClickListener(buttonNR6, calculationView, interimResultView)
        setNumberButtonOnClickListener(buttonNR7, calculationView, interimResultView)
        setNumberButtonOnClickListener(buttonNR8, calculationView, interimResultView)
        setNumberButtonOnClickListener(buttonNR9, calculationView, interimResultView)

        // operation button listeners
        setOperationButtonOnClickListener(buttonPlus, calculationView, interimResultView)
        setOperationButtonOnClickListener(buttonMinus, calculationView, interimResultView)
        setOperationButtonOnClickListener(buttonDiv, calculationView, interimResultView)
        setOperationButtonOnClickListener(buttonMult, calculationView, interimResultView)

        buttonEquals.setOnClickListener {
            if(interimResult != 0f) {
                val nr = calculationView.text.toString()
                if (nr != "= 0") {
                    if (currentOperation == "+") {
                        interimResult += nr.toFloat()
                    } else if (currentOperation == "-") {
                        interimResult -= nr.toFloat()
                    } else if (currentOperation == "/") {
                        interimResult /= nr.toFloat()
                    } else if (currentOperation == "*") {
                        interimResult *= nr.toFloat()
                    } else {
                        Log.d("KalkFrei", "$currentOperation is not implemented!")
                    }
                }
                val text = "= $interimResult"
                calculationView.text = text
                interimResultView.text = ""
                interimResult = 0f
                currentOperation = ""
            }
        }
        buttonDelete.setOnClickListener {
            interimResult = 0f
            currentOperation = ""
            calculationView.text = "= 0"
            interimResultView.text = ""
        }
    }

    private fun setNumberButtonOnClickListener(button: Button, calculationView: TextView, interimResultView: TextView) {
        button.setOnClickListener {
            if (calculationView.text.toString().startsWith("= 0") || calculationView.text.toString().startsWith("0")) {
                calculationView.text = ""
            } else if (calculationView.text.toString().startsWith("=")) {
                interimResult = 0f
                currentOperation = ""
                calculationView.text = ""
                interimResultView.text = ""
            }
            calculationView.append(button.text.toString())
        }
    }

    private fun setOperationButtonOnClickListener(button: Button, calculationView: TextView, interimResultView: TextView) {
        button.setOnClickListener {
            var nr = calculationView.text.toString()
            if (nr != "= 0") {
                if (nr.startsWith("=")) {
                    nr = nr.substring(2, nr.length)
                }
                if (interimResult == 0f) {
                    interimResult = nr.toFloat()
                } else if (currentOperation == "+") {
                    interimResult += nr.toFloat()
                } else if (currentOperation == "-") {
                    interimResult -= nr.toFloat()
                } else if (currentOperation == "/") {
                    interimResult /= nr.toFloat()
                } else if (currentOperation == "*") {
                    interimResult *= nr.toFloat()
                } else {
                    Log.d("KalkFrei", "${button.text} is not implemented!")
                }
                currentOperation = button.text.toString()
                val text = "$interimResult $currentOperation"
                interimResultView.text = text
                calculationView.text = "0"
            }
        }
    }
}