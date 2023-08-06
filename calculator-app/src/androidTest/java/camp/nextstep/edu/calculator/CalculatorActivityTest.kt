package camp.nextstep.edu.calculator

import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class CalculatorActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(CalculatorActivity::class.java)

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
}
