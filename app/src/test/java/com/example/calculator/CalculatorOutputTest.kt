package com.example.calculator

import com.example.calculator.calculator.CalculatorOutputInterfaceView
import com.example.calculator.calculator.CalculatorOutputPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.then
import org.mockito.Mockito

class CalculatorOutputTest {

    private val mPresenter :CalculatorOutputPresenter = CalculatorOutputPresenter
    private val mView = Mockito.mock(CalculatorOutputInterfaceView::class.java)

    @Before
    fun setup() {
        mPresenter.clear()
    }

    @Test
    fun onePlusOne() {
        // Given that the view is attached
        mPresenter.attach(mView)

        // When
        mPresenter.add("1")

        //Then
        then(mView).should().setEquation("1")

        // When
        mPresenter.add("+")

        //Then
        then(mView).should().setEquation("1+")

        // When
        mPresenter.add("1")

        //Then
        then(mView).should().setEquation("1+1")

        //Then
        then(mView).should().setOutcome("2")
    }




    @Test
    fun twoPlusTwoMinusOne() {
        // Given that the view is attached
        mPresenter.attach(mView)

        // When
        mPresenter.add("2")

        //Then
        then(mView).should().setEquation("2")

        // When
        mPresenter.add("+")

        //Then
        then(mView).should().setEquation("2+")

        // When
        mPresenter.add("2")

        //Then
        then(mView).should().setEquation("2+2")

        //Then
        then(mView).should().setOutcome("4")

        // When
        mPresenter.add("-")
        mPresenter.add("1")

        //Then
        then(mView).should().setEquation("2+2-1")

        //Then
        then(mView).should().setOutcome("3")
    }
}