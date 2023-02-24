package camp.nextstep.edu.calculator.app

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import camp.nextstep.edu.calculator.CalculatorActivity
import camp.nextstep.edu.calculator.R
import org.junit.Rule
import org.junit.Test

class CalculatorActivityTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(CalculatorActivity::class.java)

    @Test
    fun append_1() {
        // when
        onView(withId(R.id.button1)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun append_8_to_9() {
        // given
        onView(withId(R.id.button8)).perform(click())

        // when
        onView(withId(R.id.button9)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("89")))
    }

    @Test
    fun calculate_1_plus_2() {
        // given
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button2)).perform(click())

        // when
        onView(withId(R.id.buttonEquals)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun plus_0() {
        // when
        onView(withId(R.id.buttonPlus)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun calculate_1_plus() {
        // given
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // when
        onView(withId(R.id.buttonEquals)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("1 +")))
    }
}