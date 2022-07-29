package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

import org.junit.jupiter.api.BeforeEach

internal class CalculatorViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @BeforeEach
    fun setUp() {
    }
}