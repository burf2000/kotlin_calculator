package com.example.calculator.calculator

import bsh.Interpreter

// object is a singleton, class is not
object CalculatorOutputPresenter {

    private var mView: CalculatorOutputInterfaceView? = null

    private var mCurrentEquation: String = ""
    private var mCurrentOutcome: String = ""

    private val mInterpreter = Interpreter()


    fun attach(view : CalculatorOutputInterfaceView) {
        mView = view

        updateEquation()
        updateOutcome()
    }

    fun detach() {
        mView = null
    }

    fun add(item : String) {
        mCurrentEquation = mCurrentEquation.plus(item)

        updateEquation()
        calculateOutcome()
        updateOutcome()
    }

    fun remove() {
        mCurrentEquation = if (mCurrentEquation.length > 1) {
            mCurrentEquation.substring(0, mCurrentEquation.length - 1)
        } else {
            ""
        }

        updateEquation()
        calculateOutcome()
        updateOutcome()
    }

    fun solve() {

        if (mCurrentOutcome.isNotEmpty()) {
            mCurrentEquation = mCurrentOutcome
            mCurrentOutcome = ""
        }

        updateEquation()
        calculateOutcome()
    }

    fun clear() {
        mCurrentEquation = ""
        mCurrentOutcome = ""

        updateEquation()
        calculateOutcome()
    }

    private fun calculateOutcome() {
        if (mCurrentEquation.isNotEmpty()) {
            try {
                mInterpreter.eval("result = $mCurrentEquation")
                val result = mInterpreter.get("result")
                if (result != null && result is Int) {
                    mCurrentOutcome = result.toString()
                }
            } catch (e : Exception){
                mCurrentOutcome = ""
            }
        } else {
            mCurrentOutcome = ""
        }

    }

    private fun updateEquation() {
        mView?.setEquation(mCurrentEquation)
    }

    private fun updateOutcome() {
        mView?.setOutcome(mCurrentOutcome)
    }
}