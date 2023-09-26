package com.example.basiccalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.basiccalculator.databinding.ActivityMainBinding
import java.util.LinkedList
import java.util.Queue


private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var operands = binding.tVNumbers.text.toString()

        binding.acBtn.setOnClickListener {
            binding.tVNumbers.text = ""
            binding.tVResult.text = ""
        }

        binding.removeBtn.setOnClickListener {
            var concat = binding.tVNumbers.text.toString()
            concat = if (concat == ""){
                ""
            } else {
                concat.substring(0, concat.length - 1)
            }
            binding.tVNumbers.text = concat
            binding.tVResult.text = ""
        }

        binding.zeroBtn.setOnClickListener {
            val concat = binding.tVNumbers.text.toString() + "0"
            binding.tVNumbers.text = concat
        }

        binding.oneBtn.setOnClickListener {
            val concat = if (binding.tVNumbers.text.toString() == "0"){
                "1"
            } else {
                binding.tVNumbers.text.toString() + "1"
            }
            binding.tVNumbers.text = concat
        }

        binding.twoBtn.setOnClickListener {
            val concat = if (binding.tVNumbers.text.toString() == "0"){
                "2"
            } else {
                binding.tVNumbers.text.toString() + "2"
            }
            binding.tVNumbers.text = concat
        }

        binding.threeBtn.setOnClickListener {
            val concat = if (binding.tVNumbers.text.toString() == "0"){
                "3"
            } else {
                binding.tVNumbers.text.toString() + "3"
            }
            binding.tVNumbers.text = concat
        }

        binding.fourBtn.setOnClickListener {
            val concat = if (binding.tVNumbers.text.toString() == "0"){
                "4"
            } else {
                binding.tVNumbers.text.toString() + "4"
            }
            binding.tVNumbers.text = concat
        }

        binding.fiveBtn.setOnClickListener {
            val concat = if (binding.tVNumbers.text.toString() == "0"){
                "5"
            } else {
                binding.tVNumbers.text.toString() + "5"
            }
            binding.tVNumbers.text = concat
        }

        binding.sixBtn.setOnClickListener {
            val concat = if (binding.tVNumbers.text.toString() == "0"){
                "6"
            } else {
                binding.tVNumbers.text.toString() + "6"
            }
            binding.tVNumbers.text = concat
        }

        binding.sevenBtn.setOnClickListener {
            val concat = if (binding.tVNumbers.text.toString() == "0"){
                "7"
            } else {
                binding.tVNumbers.text.toString() + "7"
            }
            binding.tVNumbers.text = concat
        }
        binding.eightBtn.setOnClickListener {
            val concat = if (binding.tVNumbers.text.toString() == "0"){
                "8"
            } else {
                binding.tVNumbers.text.toString() + "8"
            }
            binding.tVNumbers.text = concat
        }

        binding.nineBtn.setOnClickListener {
            val concat = if (binding.tVNumbers.text.toString() == "0"){
                "9"
            } else {
                binding.tVNumbers.text.toString() + "9"
            }
            binding.tVNumbers.text = concat
        }

        binding.periodBtn.setOnClickListener{
            var concat = binding.tVNumbers.text.toString()
            concat = if (concat == ""){
                "0."
            } else if (concat[concat.lastIndex] == '+' || concat[concat.lastIndex] == '-' ||
                concat[concat.lastIndex] == 'x'  || concat[concat.lastIndex] == '/' ){
               concat + "0."
            } else{
                "$concat."
            }
            binding.tVNumbers.text = concat
        }

        binding.plusBtn.setOnClickListener{
            var concat = binding.tVNumbers.text.toString()
            concat = if (concat == ""){
                "0+"
            } else if (concat[concat.lastIndex] == '+' || concat[concat.lastIndex] == '-' ||
                concat[concat.lastIndex] == 'x'  || concat[concat.lastIndex] == '/' ){
                concat.substring(0,concat.length - 1) + "+"
            } else if (concat[concat.lastIndex] == '.'){
                concat
            }else{
                "$concat+"
            }
            binding.tVNumbers.text = concat
        }
        binding.minusBtn.setOnClickListener{
            var concat = binding.tVNumbers.text.toString()
            concat = if (concat == ""){
                "0-"
            } else if (concat[concat.lastIndex] == '+' || concat[concat.lastIndex] == '-' ||
                concat[concat.lastIndex] == 'x'  || concat[concat.lastIndex] == '/' ){
                concat.substring(0,concat.length - 1) + "-"
            } else if (concat[concat.lastIndex] == '.'){
                concat
            }else{
                "$concat-"
            }
            binding.tVNumbers.text = concat
        }
        binding.multiplyBtn.setOnClickListener{
            var concat = binding.tVNumbers.text.toString()
            concat = if (concat == ""){
                "0x"
            } else if (concat[concat.lastIndex] == '+' || concat[concat.lastIndex] == '-' ||
                concat[concat.lastIndex] == 'x'  || concat[concat.lastIndex] == '/' ){
                concat.substring(0,concat.length - 1) + "x"
            } else if (concat[concat.lastIndex] == '.'){
                concat
            }else{
                concat + "x"
            }
            binding.tVNumbers.text = concat
        }
        binding.divideBtn.setOnClickListener{
            var concat = binding.tVNumbers.text.toString()
            concat = if (concat == ""){
                "0/"
            } else if (concat[concat.lastIndex] == '+' || concat[concat.lastIndex] == '-' ||
                concat[concat.lastIndex] == 'x'  || concat[concat.lastIndex] == '/' ){
                concat.substring(0,concat.length - 1) + "/"
            } else if (concat[concat.lastIndex] == '.'){
                concat
            }else{
                "$concat/"
            }
            binding.tVNumbers.text = concat
        }
        binding.equalBtn.setOnClickListener{
            val operations = binding.tVNumbers.text.toString()
            if (operations == ""){
                binding.tVResult.text = ""
            } else {
                val result = calculate(operations)
                binding.tVResult.text = "= $result"
            }
        }
    }

    private fun calculate(operations: String): Double? {
        val numbers: Queue<Double> = LinkedList()
        val operators: Queue<Char> = LinkedList()
        var num = ""
        for(i in operations){
            if (i == '+' || i == '-' || i == 'x' || i == '/' ){
                numbers.offer(num.toDouble())
                operators.offer(i)
                num = ""
            } else {
                num += i
            }
        }
        if (num != ""){
            numbers.offer(num.toDouble())
        }
        var first = numbers.poll()
        var second: Double? = 0.0
        while(!numbers.isEmpty()){
            second = numbers.poll()
            val operator = operators.poll()
            if (first != null && operator == '+'){
                first += second
            } else if (first != null && operator == '-'){
                first -= second
            } else if (first != null && operator == 'x'){
                first *= second
            } else if (first != null && operator == '/'){
                first /= second
            }
        }
        return first
    }
}