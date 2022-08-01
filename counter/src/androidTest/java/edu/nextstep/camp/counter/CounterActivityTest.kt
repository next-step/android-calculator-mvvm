package edu.nextstep.camp.counter

import androidx.test.ext.junit.rules.ActivityScenarioRule
import edu.nextstep.camp.common.AndroidTestHelper
import org.junit.Rule
import org.junit.Test

class CounterActivityTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(CounterActivity::class.java)

    @Test
    fun up() {
        // when
        AndroidTestHelper.onViewsClick(R.id.buttonUp)

        // then
        AndroidTestHelper.onViewMatchText(R.id.textView, "1")
    }

    @Test
    fun down_1_to_0() {
        // when
        AndroidTestHelper.onViewsClick(R.id.buttonUp, R.id.buttonDown)

        // then
        AndroidTestHelper.onViewMatchText(R.id.textView, "0")
    }

    @Test
    fun down_0_to_0() {
        // when
        AndroidTestHelper.onViewsClick(R.id.buttonDown)

        // then
        AndroidTestHelper.onViewMatchText(R.id.textView, "0")
    }
}
