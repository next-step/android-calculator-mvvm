package camp.nextstep.edu.calculator

import android.content.Context
import androidx.activity.viewModels
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(CalculatorActivity::class.java)

    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity {
            val context = ApplicationProvider.getApplicationContext<Context>()
            it.viewModels<CalculatorViewModel>(
                factoryProducer = { CalculatorViewModelFactory(context = context) }
            )
        }
    }

    @Test
    fun `1을_누르면_1이_보인다`() {
        // when: 1을 누르면
        onButtonClicked(R.id.button1)

        // then: 1이 나온다
        val actual = "1"
        onShowTextView(actual = actual, viewId = R.id.textView)
    }

    @Test
    fun `12을_누르면_12이_보인다`() {
        // when: 12을 누르면
        onButtonClicked(R.id.button1)
        onButtonClicked(R.id.button2)

        // then: 12이 나온다
        val actual = "12"
        onShowTextView(actual = actual, viewId = R.id.textView)
    }

    @Test
    fun `12_더하기를_누르면_12더하기가_보인다`() {
        // when: 12 + 을 누르면
        onButtonClicked(R.id.button1)
        onButtonClicked(R.id.button2)
        onButtonClicked(R.id.buttonPlus)

        // then: 12 + 이 나온다
        val actual = "12 +"
        onShowTextView(actual = actual, viewId = R.id.textView)
    }

    @Test
    fun `12더하기을_누르고_12를_누르면_12더하기12이_보인다`() {
        // given: 12 +
        onButtonClicked(R.id.button1)
        onButtonClicked(R.id.button2)
        onButtonClicked(R.id.buttonPlus)

        // when: 12를 누르면
        onButtonClicked(R.id.button1)
        onButtonClicked(R.id.button2)

        // then: 12 + 12이 나온다
        val actual = "12 + 12"
        onShowTextView(actual = actual, viewId = R.id.textView)
    }

    @Test
    fun `12더하기12을_누르고_결과를_누르면_24_보인다`() {
        // given: 12 + 12
        onButtonClicked(R.id.button1)
        onButtonClicked(R.id.button2)
        onButtonClicked(R.id.buttonPlus)
        onButtonClicked(R.id.button1)
        onButtonClicked(R.id.button2)

        // when: 결과를 누르면
        onButtonClicked(R.id.buttonEquals)

        // then: 24이 나온다
        val actual = "24"
        onShowTextView(actual = actual, viewId = R.id.textView)
    }

    @Test
    fun `12을_누르고_지우기_누르면_1이_보인다`() {
        // given: 12을 누르면
        onButtonClicked(R.id.button1)
        onButtonClicked(R.id.button2)

        // when: 지우기를 누르면
        onButtonClicked(R.id.buttonDelete)

        // then: 1이 나온다
        val actual = "1"
        onShowTextView(actual = actual, viewId = R.id.textView)
    }

    @Test
    fun `12더하기12결과_누르고_리셋버튼을_누르면_빈문자열_보인다`() {
        // given: 12 + 12 =
        onButtonClicked(R.id.button1)
        onButtonClicked(R.id.button2)
        onButtonClicked(R.id.buttonPlus)
        onButtonClicked(R.id.button1)
        onButtonClicked(R.id.button2)
        onButtonClicked(R.id.buttonEquals)

        // when: 리셋 누르면
        onButtonClicked(R.id.buttonMemory)

        // then: 빈문자열이 나온다
        val actual = ""
        onShowTextView(actual = actual, viewId = R.id.textView)
    }

    @Test
    fun `초기상태에서_더하기를_누르면_동작을_안한다`() {
        // when: +를 누르면
        onButtonClicked(R.id.buttonPlus)

        // then: 동작을 안한다
        val actual = ""
        onShowTextView(actual = actual, viewId = R.id.textView)
    }

    @Test
    fun `메모리_버튼을_누르면_기록이_화면에_보인다`() {
        // given

        // when: 메모리 버튼을 누르면
        onButtonClicked(R.id.buttonMemory)

        // Then: 계산 기록이 화면에 보인다.
        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.textView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)))
    }

    @Test
    fun `메모리_버튼을_두번_누르면_기록이_화면에서_보였다_사라진다`() {
        // given

        // when: 메모리 버튼을 두번 누르면
        onButtonClicked(R.id.buttonMemory)
        onButtonClicked(R.id.buttonMemory)

        // Then: 계산 기록이 화면에 보였다 사라진다
        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)))
        onView(withId(R.id.textView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
}
