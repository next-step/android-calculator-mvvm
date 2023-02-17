package camp.nextstep.edu.calculator

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class CalculatorActivityTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(CalculatorActivity::class.java)

    @Test
    fun `0을_누르면_0이_보인다`() {
        // when: '0' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.button0)).perform(ViewActions.click())

        // then: '0'이 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("0")))
    }

    @Test
    fun `1를_누르면_1이_보인다`() {
        // when: '1' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click())

        // then: '1'이 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("1")))
    }

    @Test
    fun `2를_누르면_2가_보인다`() {
        // when: '2' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.button2)).perform(ViewActions.click())

        // then: '2'이 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("2")))
    }

    @Test
    fun `3을_누르면_3이_보인다`() {
        // when: '3' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.button3)).perform(ViewActions.click())

        // then: '3'이 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("3")))
    }

    @Test
    fun `4를_누르면_4이_보인다`() {
        // when: '4' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.button4)).perform(ViewActions.click())

        // then: '4'이 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("4")))
    }

    @Test
    fun `5를_누르면_5가_보인다`() {
        // when: '5' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.button5)).perform(ViewActions.click())

        // then: '5'이 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("5")))
    }

    @Test
    fun `6을_누르면_6이_보인다`() {
        // when: '6' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.button6)).perform(ViewActions.click())

        // then: '6'이 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("6")))
    }

    @Test
    fun `7을_누르면_7이_보인다`() {
        // when: '7' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.button7)).perform(ViewActions.click())

        // then: '7'이 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("7")))
    }

    @Test
    fun `8을_누르면_8이_보인다`() {
        // when: '8' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.button8)).perform(ViewActions.click())

        // then: '8'이 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("8")))
    }

    @Test
    fun `9를_누르면_9가_보인다`() {
        // when: '9' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.button9)).perform(ViewActions.click())

        // then: '9'이 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("9")))
    }

    @Test
    fun `입력된_수식이_없을때_1을_누르면_1이_보인다`() {
        // given : 입력된 수식이 없을 때

        // when: '1' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click())

        // then: '1'이 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("1")))
    }

    @Test
    fun `입력된_값이_5_더하기_일때_1을_누르면_5_더하기_1이_보인다`() {
        // given : 입력된 수식 '1+' 일때
        Espresso.onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.buttonPlus)).perform(ViewActions.click())

        // when: '1' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click())

        // then: '1+1'이 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("1+1")))
    }

    @Test
    fun `입력된_값이_8일때_9를_누르면_89가_보인다`() {
        // given : 입력된 수식이 '8' 일때
        Espresso.onView(ViewMatchers.withId(R.id.button8)).perform(ViewActions.click())

        // when: '9' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.button9)).perform(ViewActions.click())

        // then: '89'가 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("89")))
    }

    @Test
    fun `입력된_값이_없을때_플러스를_누르면_안보여야한다`() {
        // given : 입력된 수식 없을때

        // when: '+' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.buttonPlus)).perform(ViewActions.click())

        // then: 안보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("")))
    }

    @Test
    fun `입력된_값이_1일때_플러스를_누르면_1_플러스가_보인다`() {
        // given : 입력된 수식이 '1'일때
        Espresso.onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click())

        // when: '+' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.buttonPlus)).perform(ViewActions.click())

        // then: '1+'가 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("1+")))
    }

    @Test
    fun `입력된_값이_1_플러스일때_마이너스를_누르면_1_마이너스가_보인다`() {
        // given : 입력된 수식이 '1+'일때
        Espresso.onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.buttonPlus)).perform(ViewActions.click())

        // when: '-' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.buttonMinus)).perform(ViewActions.click())

        // then: '1-'가 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("1-")))
    }

    @Test
    fun `입력된_값이_없을때_지우기를_누르면_변화가_없다`() {
        // given : 입력된 수식이 ''일때

        // when: '지우기' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.buttonDelete)).perform(ViewActions.click())

        // then: ''가 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("")))
    }

    @Test
    fun `입력된_값이_32_플러스_1일때_지우기를_누르면_32_플러스가_보인다`() {
        // given : 입력된 수식이 '32+1'일때
        Espresso.onView(ViewMatchers.withId(R.id.button3)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.button2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.buttonPlus)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click())

        // when: '지우기' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.buttonDelete)).perform(ViewActions.click())

        // then: '32+'가 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("32+")))
    }

    @Test
    fun `입력된_값이_32_플러스_1일때_지우기를_두번_누르면_32이_보인다`() {
        // given : 입력된 수식이 '32+1'일때
        Espresso.onView(ViewMatchers.withId(R.id.button3)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.button2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.buttonPlus)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click())

        // when: '지우기' 버튼을 두번 누르면
        Espresso.onView(ViewMatchers.withId(R.id.buttonDelete)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.buttonDelete)).perform(ViewActions.click())

        // then: '32'가 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("32")))
    }

    @Test
    fun `입력된_식이_3_플러스_2로_온전할때_계산을_누르면_결과가_보인다`() {
        // given : 입력된 수식이 '3+2'일때
        Espresso.onView(ViewMatchers.withId(R.id.button3)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.buttonPlus)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.button2)).perform(ViewActions.click())

        // when: '=' 버튼을 누르면
        Espresso.onView(ViewMatchers.withId(R.id.buttonEquals)).perform(ViewActions.click())

        // then: '5'가 보여야 한다
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("5")))
    }
}
