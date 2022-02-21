package edu.nextstep.camp.calculator

sealed class CalculatorMode {

    object Expression : CalculatorMode()
    object Memory : CalculatorMode()
}