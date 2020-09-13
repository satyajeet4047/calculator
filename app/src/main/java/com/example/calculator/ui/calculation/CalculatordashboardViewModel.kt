package com.example.calculator.ui.calculation

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.R
import com.example.calculator.data.NetworkServiceRepository
import com.example.githubapp.util.RequestStatus
import java.util.*
import javax.inject.Inject

class CalculatordashboardViewModel @Inject constructor(private val networkServiceRepository: NetworkServiceRepository) : ViewModel() {


    private val loginStatus : MutableLiveData<Boolean> = MutableLiveData()

    private val TAG = "CalculatorViewModel"

    private var expressionBuilder: StringBuilder = StringBuilder()
    var expression: MutableLiveData<String> = MutableLiveData()
    var isError: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { false }
    var isPreviousEntryNumber = false
    var isCalculationPerformed = false


    fun getloginStatus() : MutableLiveData<Boolean> {
        return loginStatus
    }


    fun checkLoginStatus(){
        loginStatus.value = networkServiceRepository.currentUser() != null
    }


    fun onClick(view: View) {

        when (view.id) {

            R.id.btn_clear -> {
                expressionBuilder.clear()
                expression.value = " "
            }
            R.id.op_equals -> {
                try {
                    expression.value =  calculate(expressionBuilder).toString()
                    expressionBuilder.clear()
                    expressionBuilder.append(expression.value)
                    isCalculationPerformed = true;
                }catch (e : Exception){
                    Log.d(TAG, "Error ")
                    isError.value = true
                    expressionBuilder.clear()
                    expression.value = " "
                }
            }

            R.id.btn_nine ->
                formExpression("9")
            R.id.btn_eight ->
                formExpression("8")
            R.id.btn_seven ->
                formExpression("7")
            R.id.btn_six ->
                formExpression("6")
            R.id.btn_five ->
                formExpression("5")
            R.id.btn_four ->
                formExpression("4")
            R.id.btn_three ->
                formExpression("3")
            R.id.btn_two ->
                formExpression("2")
            R.id.btn_one ->
                formExpression("1")
            R.id.btn_zero ->
                formExpression("0")
            R.id.op_addition ->
                formExpression("+")
            R.id.op_subtraction ->
                formExpression("-")
            R.id.op_multiply ->
                formExpression("*")
            R.id.op_div ->
                formExpression("/")
        }

    }

    @Throws(Exception::class)
    private fun calculate(displayText: StringBuilder): Int {

        val tokens = displayText.toString().split(' ')

        val values: Stack<Int> = Stack<Int>()

        val ops: Stack<String> = Stack<String>()
        for (token: String in tokens) {

            if (isNumber(token)) {
                values.push(token.toInt())
            } else {
                while (!ops.empty() && checkPrecedence(token, ops.peek()))
                    values.push(performCalculation(ops.pop(), values.pop(), values.pop()))

                ops.push(token)
            }
        }

        while (!ops.empty()) values.push(performCalculation(ops.pop(), values.pop(), values.pop()))

        return values.pop()
    }

    private fun isOperator(input: String)  =  (input == "*" || input == "+" || input == "-" || input == "/")

    private fun isNumber(token: String): Boolean {

        return try{
            token.toInt()
            true
        } catch (e : Exception){
            false
        }

    }


    private fun checkPrecedence(op1: String, op2: String): Boolean {
        if (op1 == "*" && (op2 == "+" || op2 == "-" || op2 == "/")) return false
        else if (op2 == "+" && (op2 == "-"  || op2 == "/")) return false
        else if (op2 == "/" && op2 == "-") return false
        return true
    }

    @Throws(Exception::class)
    private fun performCalculation(op: String, b: Int, a: Int): Int {
        when (op) {
            "+" -> return a + b
            "-" -> return a - b
            "*" -> return a * b
            "/" -> {
                if (b == 0) throw Exception("divide by zero")
                return a / b
            }
        }
        return 0
    }


    private fun formExpression(input: String) {

        if(isCalculationPerformed){
            expressionBuilder.clear()
            expression.value = " "
            isCalculationPerformed = false
        }
        isError.value = false
        if(!isOperator(input)){
            expression.value = expressionBuilder.append(input).toString()
            isPreviousEntryNumber = true
        } else {
            if(isPreviousEntryNumber){
                expression.value = expressionBuilder.append(" ").append(input).append(" ").toString()
                isPreviousEntryNumber = false
            }
        }
    }

}