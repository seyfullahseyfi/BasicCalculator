package com.seyf.basiccalculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.seyf.basiccalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var enteredNumber = ""
    var operator = ""
    var result = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btn0.setOnClickListener { enterNum("0") }
        binding.btn1.setOnClickListener { enterNum("1") }
        binding.btn2.setOnClickListener { enterNum("2") }
        binding.btn3.setOnClickListener { enterNum("3") }
        binding.btn4.setOnClickListener { enterNum("4") }
        binding.btn5.setOnClickListener { enterNum("5") }
        binding.btn6.setOnClickListener { enterNum("6") }
        binding.btn7.setOnClickListener { enterNum("7") }
        binding.btn8.setOnClickListener { enterNum("8") }
        binding.btn9.setOnClickListener { enterNum("9") }
        binding.btnDot.setOnClickListener { enterNum(".") }


        binding.btnAdd.setOnClickListener { operation("+") }
        binding.btnSub.setOnClickListener { operation("-") }
        binding.btnMulti.setOnClickListener { operation("*") }
        binding.btnDiv.setOnClickListener { operation("/") }
        binding.btnClear.setOnClickListener { clear() }
        binding.btnEqual.setOnClickListener { result() }
        binding.btnBackspace.setOnClickListener { backspace() }
    }

    fun enterNum(num: String) {
        enteredNumber += num
        binding.enteredNum.setText(enteredNumber)
    }


    fun operation(op: String) {
        if (enteredNumber.isNotEmpty()) {
            val num = enteredNumber.toDouble()

            if (operator.isEmpty()) {
                result = num
            } else {
                result = when (operator) {
                    "+" -> result + num
                    "-" -> result - num
                    "*" -> result * num
                    "/" -> {
                        if (num != 0.0) result / num
                        else {
                            Toast.makeText(this, "0'a bölme hatası", Toast.LENGTH_SHORT).show()
                            result
                        }
                    }

                    else -> result
                }
            }
            operator = op
            enteredNumber = ""
            if (result % 1.0 == 0.0) {
                binding.resultText.hint = result.toInt().toString()
            } else {
                binding.resultText.hint = result.toString()
            }

        } else {
            Toast.makeText(this, "Geçersiz İşlem", Toast.LENGTH_SHORT).show()
        }
    }


    fun result() {
        if (enteredNumber.isNotEmpty()) {
            val num = enteredNumber.toDouble()
            result = when (operator) {
                "+" -> result + num
                "-" -> result - num
                "*" -> result * num
                "/" -> {
                    if (num != 0.0) result / num
                    else {
                        Toast.makeText(this, "0'a bölme hatası", Toast.LENGTH_SHORT).show()
                        result
                    }
                }

                else -> num
            }
            if (result % 1.0 == 0.0) {
                binding.resultText.text = result.toInt().toString()
            } else {
                binding.resultText.text = result.toString()
            }
            enteredNumber = ""
            operator = ""
            binding.enteredNum.setText("")


        }
    }

    fun clear() {
        binding.enteredNum.setText("")
        binding.resultText.text = ""
        binding.resultText.hint = ""
        enteredNumber = ""
        operator = ""
        result = 0.0
    }

    fun backspace() {
        if (enteredNumber.isNotEmpty()) {
            enteredNumber = enteredNumber.substring(0, enteredNumber.length - 1)
            binding.enteredNum.setText(enteredNumber)
        }
    }


}