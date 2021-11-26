package com.albertkingdom.calculator

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.allViews
import java.lang.Exception
import kotlin.math.abs

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var resultView: TextView
    lateinit var operator: Operator
    var firstDigit: Double = 0.0
    var secondDigit: Double = 0.0
    var currentDisplay: String = ""
    var afterEnterOperator: Boolean = false
    var result: Double = 0.0
    var pressEqualCount = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttons = findViewById<View>(R.id.buttons)
        val button_one = findViewById<Button>(R.id.btn_one)
        val button_two = findViewById<Button>(R.id.btn_two)
        val button_three = findViewById<Button>(R.id.btn_three)
        val button_four = findViewById<Button>(R.id.btn_four)
        val button_five = findViewById<Button>(R.id.btn_five)
        val button_six = findViewById<Button>(R.id.btn_six)
        val button_seven = findViewById<Button>(R.id.btn_seven)
        val button_eight = findViewById<Button>(R.id.btn_eight)
        val button_nine = findViewById<Button>(R.id.btn_nine)
        val button_zero = findViewById<Button>(R.id.btn_zero)

        val button_dot = findViewById<Button>(R.id.btn_dot)
        val button_times = findViewById<Button>(R.id.btn_times)
        val button_divide = findViewById<Button>(R.id.btn_divide)
        val button_plus = findViewById<Button>(R.id.btn_plus)
        val button_minus = findViewById<Button>(R.id.btn_minus)
        val button_remainder = findViewById<Button>(R.id.btn_remainder)
        val button_positive_negative = findViewById<Button>(R.id.btn_positive_negative)

        val button_clear = findViewById<Button>(R.id.btn_clear)
        val button_backspace = findViewById<Button>(R.id.btn_backspace)
        val button_equal = findViewById<Button>(R.id.btn_equal)

        resultView = findViewById(R.id.result)
        resultView.text = "0"

//        button_zero.setOnClickListener(this)
//        button_one.setOnClickListener(this)
//        button_two.setOnClickListener(this)
//        button_three.setOnClickListener(this)
//        button_four.setOnClickListener(this)
//        button_five.setOnClickListener(this)
//        button_six.setOnClickListener(this)
//        button_seven.setOnClickListener(this)
//        button_eight.setOnClickListener(this)
//        button_nine.setOnClickListener(this)
//
//        button_times.setOnClickListener(this)
//        button_divide.setOnClickListener(this)
//        button_plus.setOnClickListener(this)
//        button_minus.setOnClickListener(this)
//        button_remainder.setOnClickListener(this)
//        button_positive_negative.setOnClickListener(this)
//
//        button_dot.setOnClickListener(this)
//        button_clear.setOnClickListener(this)
//        button_equal.setOnClickListener(this)
//        button_backspace.setOnClickListener(this)

        for (child in buttons.allViews) {

            child.setOnClickListener(this)
        }

    }

    private fun clearTextView() {
        resultView.text = ""
        currentDisplay = ""
        afterEnterOperator = false
    }

    override fun onClick(view: View) {
// clear resultview after press operator button or default displayed 0
        if (afterEnterOperator || resultView.text == "0") {
            println("firstDigit, $firstDigit")
            clearTextView()
        }
        when (view.id) {
            R.id.btn_one -> {
                currentDisplay = "${currentDisplay}1"
            }
            R.id.btn_two -> {
                currentDisplay = "${currentDisplay}2"
            }
            R.id.btn_three -> {
                currentDisplay = "${currentDisplay}3"
            }
            R.id.btn_four -> {
                currentDisplay = "${currentDisplay}4"
            }
            R.id.btn_five -> {
                currentDisplay = "${currentDisplay}5"
            }
            R.id.btn_six -> {
                currentDisplay = "${currentDisplay}6"
            }
            R.id.btn_seven -> {
                currentDisplay = "${currentDisplay}7"
            }
            R.id.btn_eight -> {
                currentDisplay = "${currentDisplay}8"
            }
            R.id.btn_nine -> {
                currentDisplay = "${currentDisplay}9"
            }
            R.id.btn_zero -> {
                currentDisplay = "${currentDisplay}0"
            }
            R.id.btn_plus -> {
                println("plus")
                operator = Operator.PLUS
                pressOperator()

            }
            R.id.btn_minus -> {
                operator = Operator.MINUS
                println("minus")
                pressOperator()
            }
            R.id.btn_times -> {
                operator = Operator.TIMES
                println("x")
                pressOperator()
            }
            R.id.btn_divide -> {
                operator = Operator.DIVIDE
                println("divide")
                pressOperator()
            }
            R.id.btn_remainder -> {
                operator = Operator.REMAINDER
                println("remainder")
                pressOperator()
            }
            R.id.btn_positive_negative -> {

                println("postive_negative")
                currentDisplay = if (currentDisplay.toDouble().compareTo(0.0) > 0) {
                    "-${currentDisplay}"
                } else {
                    abs(currentDisplay.toDouble()).toString()
                }
            }
            R.id.btn_equal -> {

                try {
                    secondDigit = currentDisplay.toDouble()
                    println("first:${firstDigit}, second: ${secondDigit}")
                } catch (error: Exception) {
                    println("error, ${error}")
                }
                currentDisplay = calculate().toString()
                println("result ,$currentDisplay")

                // todo: 模擬按下＝會重複計算的行為
            }


            R.id.btn_dot -> {
                currentDisplay = "${currentDisplay}."
            }
            R.id.btn_clear -> {
                currentDisplay = "0"
                firstDigit = 0.0
                secondDigit = 0.0
            }
            R.id.btn_backspace -> {
                currentDisplay = currentDisplay.dropLast(1)
            }
            R.id.btn_factorial -> {
                var result = 1
                if (currentDisplay.toIntOrNull() != null) {
                    if (currentDisplay.toInt() > 0) {

                        for (i in 1..currentDisplay.toInt()) {
                            result *= i
                        }
                        println("factorial result $result")
                        currentDisplay = result.toString()
                    }
                    if (currentDisplay.toInt() == 0) {
                        println("factorial result $result")
                        currentDisplay = result.toString()
                    }
                } else {
                    showAlert()
                }
            }
        }
        resultView.text = currentDisplay
    }

    private fun pressOperator() {
        try {
            firstDigit = currentDisplay.toDouble()
        } catch (error: Exception) {
            println("error, ${error}")
        }
        afterEnterOperator = true
    }

    private fun calculate(): Double {
        result = when (operator) {
            Operator.PLUS -> firstDigit + secondDigit
            Operator.MINUS -> firstDigit - secondDigit
            Operator.TIMES -> firstDigit * secondDigit
            Operator.DIVIDE -> firstDigit / secondDigit
            Operator.REMAINDER -> firstDigit % secondDigit
        }

        return result
    }

    private fun showAlert() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Error")
        alertDialog.setMessage("請輸入正整數！")
        alertDialog.setPositiveButton("Ok", DialogInterface.OnClickListener { _, i ->
            println("press ok")
            currentDisplay = "0"
        })

        alertDialog.show()
    }
}

enum class Operator {
    TIMES, DIVIDE, PLUS, MINUS, REMAINDER
}