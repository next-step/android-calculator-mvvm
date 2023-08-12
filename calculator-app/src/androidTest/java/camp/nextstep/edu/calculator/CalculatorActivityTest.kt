package camp.nextstep.edu.calculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class CalculatorActivityTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(CalculatorActivity::class.java)

    @Test
    fun click1() {
        // when: '1' 버튼을 누르면
        onView(withId(R.id.button1)).perform(click())

        // then: '1'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun click5_plus_1() {
        // when: '5 + 1' 버튼을 누르면
        onView(withId(R.id.button5)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // then: '5 + 1'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("5 + 1")))
    }

    @Test
    fun click8_and_9() {
        // when: '89' 버튼을 누르면
        onView(withId(R.id.button8)).perform(click())
        onView(withId(R.id.button9)).perform(click())

        // then: '89'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("89")))
    }

    @Test
    fun click1_plus() {
        // when: '1 + ' 버튼을 누르면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // then: '1 +'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("1 +")))
    }

    @Test
    fun click1_minus() {
        // when: '1 - ' 버튼을 누르면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonMinus)).perform(click())

        // then: '1 -'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("1 -")))
    }

    @Test
    fun click1_remove() {
        // when: '지우기 ' 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '1 -'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun click32_plus_1_remove() {
        // given: '32 + 1' 버튼을 누르면
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())
        // when: '지우기 ' 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '32 +'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("32 +")))

        // when: '지우기 ' 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '32'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("32")))

        // when: '지우기 ' 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '3'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("3")))

        // when: '지우기 ' 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: ''이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun click3_plus_2_equal() {
        // when: '3 + 2 =' 버튼을 누르면
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonEquals)).perform(click())

        // then: '5'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun click0_and_4() {
        // when: '0 4' 버튼을 누르면
        onView(withId(R.id.button0)).perform(click())
        onView(withId(R.id.button4)).perform(click())

        // then: '4'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("4")))
    }

    @Test
    fun clickplus_first() {
        // when: 'plus' 버튼을 누르면
        onView(withId(R.id.buttonPlus)).perform(click())

        // then: ''이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("")))
    }
}
