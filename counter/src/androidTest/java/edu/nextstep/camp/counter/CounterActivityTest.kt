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
    fun click_up_button_plus_number() {
        // given count = 0
        // when up 버튼 클릭
        onView(withId(R.id.buttonUp)).perform(click())

        // then count = 1
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun click_down_button_minus_number() {
        // given count = 1
        onView(withId(R.id.buttonUp)).perform(click())

        // when down 버튼 클릭
        onView(withId(R.id.buttonDown)).perform(click())

        // then count = 0
        onView(withId(R.id.textView)).check(matches(withText("0")))
    }

    @Test
    fun zero_number_click_down_button_still_zero() {
        // given count = 0
        // when down 버튼 클릭
        onView(withId(R.id.buttonDown)).perform(click())

        // then count = 0
        onView(withId(R.id.textView)).check(matches(withText("0")))
    }
}
