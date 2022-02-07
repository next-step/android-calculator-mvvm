package edu.nextstep.camp.counter

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class CounterActivityTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(CounterActivity::class.java)

    @Test
    fun up() {
        // when
        onView(withId(R.id.buttonUp)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun down_1_to_0() {
        // when
        onView(withId(R.id.buttonUp)).perform(click())
        onView(withId(R.id.buttonDown)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("0")))
    }

    @Test
    fun down_0_to_0() {
        // when
        onView(withId(R.id.buttonDown)).perform(click())

        // then
        onView(withId(R.id.textView)).check(matches(withText("0")))
    }
}
