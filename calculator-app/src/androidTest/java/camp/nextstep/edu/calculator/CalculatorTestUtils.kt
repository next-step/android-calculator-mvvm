package camp.nextstep.edu.calculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import camp.nextstep.edu.calculator.domain.Operator

fun onNumberButtonClicked(number: Int) {
    val viewId: Int? = when (number) {
        0 -> R.id.button0
        1 -> R.id.button1
        2 -> R.id.button2
        3 -> R.id.button3
        4 -> R.id.button4
        5 -> R.id.button5
        6 -> R.id.button6
        7 -> R.id.button7
        8 -> R.id.button8
        9 -> R.id.button9
        else -> null
    }
    viewId?.let { id ->
        onView(withId(id)).perform(click())
    }
}

fun onMethodButtonClicked(operator: Operator) {
    val viewId: Int = when (operator) {
        Operator.Plus -> R.id.buttonPlus
        Operator.Minus -> R.id.buttonMinus
        Operator.Multiply -> R.id.buttonMultiply
        Operator.Divide -> R.id.buttonDivide
    }
    onView(withId(viewId)).perform(click())
}

fun onEqualButtonClicked() {
    onView(withId(R.id.buttonEquals)).perform(click())
}

fun onDeleteButtonClicked() {
    onView(withId(R.id.buttonDelete)).perform(click())
}

fun onMemoryButtonClicked() {
    onView(withId(R.id.buttonMemory)).perform(click())
}

fun onCheckExpression(expressions: String) {
    onView(withId(R.id.textView)).check(ViewAssertions.matches(ViewMatchers.withText(expressions)))
}