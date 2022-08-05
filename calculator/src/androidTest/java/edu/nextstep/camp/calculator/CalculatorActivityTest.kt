package edu.nextstep.camp.calculator

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.Visibility.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

internal class CalculatorActivityTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(CalculatorActivity::class.java)

    @Test
    fun 계산_기록이_보이지_않을_때_계산기록_버튼을_클릭하면_계산기록이_보인다() {
        //given 계산기록이 보이지 않을 때
        Espresso.onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(GONE)))

        //when 계산기록 버튼이 눌리면
        Espresso.onView(withId(R.id.buttonMemory)).perform(click())

        //then 계산기록이 보여야 한다.
        Espresso.onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun 계산_기록이_보일때_계산기록_버튼을_클릭하면_계산기록이_보이지_않게된다() {
        //given 계산기록이 보일때
        Espresso.onView(withId(R.id.buttonMemory)).perform(click())
        Espresso.onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))

        //when 계산기록 버튼이 눌리면
        Espresso.onView(withId(R.id.buttonMemory)).perform(click())

        //then 계산기록이 보이지 않는다.
        Espresso.onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(GONE)))
    }
}