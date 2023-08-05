package camp.nextstep.edu.counter

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
    fun `0일때_UP버튼을_누르면_1`() {
        // given: 0

        // when: Up 버튼을 누르면
        onUpButtonClicked()

        // then: 1이 된다
        onCheckCount(1)
    }

    @Test
    fun `0일때_UP_DOWN버튼을_누르면_0`() {
        // given: 0

        // when: Up Down 버튼을 누르면
        onUpButtonClicked()
        onDownButtonClicked()

        // then: 0이 된다
        onCheckCount(0)
    }

    @Test
    fun `0일때_DOWN버튼을_누르면_0`() {
        // given: 0

        // when: Down 버튼을 누르면
        onDownButtonClicked()

        // then: 0이 유지된다.
        onCheckCount(0)
    }

    @Test
    fun `0일때_UP_UP버튼을_누르면_2`() {
        // given: 0

        // when: Up Up 버튼을 누르면
        onUpButtonClicked()
        onUpButtonClicked()

        // then: 2가 된다.
        onCheckCount(2)
    }

    @Test
    fun `0일때_UP_UP_DOWN버튼을_누르면_1`() {
        // given: 0

        // when: Up Up Down 버튼을 누르면
        onUpButtonClicked()
        onUpButtonClicked()
        onDownButtonClicked()

        // then: 1가 된다.
        onCheckCount(1)
    }

    fun onUpButtonClicked() {
        onView(withId(R.id.buttonUp)).perform(click())
    }

    fun onDownButtonClicked() {
        onView(withId(R.id.buttonDown)).perform(click())
    }

    fun onCheckCount(count: Int) {
        onView(withId(R.id.textView)).check(matches(withText(count.toString())))
    }
}
