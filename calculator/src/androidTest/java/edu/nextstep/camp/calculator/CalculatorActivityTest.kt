package edu.nextstep.camp.calculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import de.mannodermaus.junit5.ActivityScenarioExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@Suppress("NonAsciiCharacters")
internal class CalculatorActivityTest {
    @JvmField
    @RegisterExtension
    var activityScenarioRule = ActivityScenarioExtension.launch<CalculatorActivity>()

    // 사용자가 피연산자 0 ~ 9 버튼을 누르면 화면에 해당 숫자가 화면에 보여야 한다.
    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
    fun 숫자_버튼을_누르면_화면에_해당_숫자가_보여야_한다(number: Int) {
        // when: 숫자 버튼을 누르면
        onView(withText(number.toString())).perform(click())
        // then: 화면에 해당 숫자가 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText(number.toString())))
    }

    @Test
    fun 버튼_8_9를_누르면_화면에_89가_보여야_한다() {
        // given: 8이 입력되어 있을 때
        onView(withId(R.id.button8)).perform(click())

        // when: 9를 입력하면
        onView(withId(R.id.button9)).perform(click())

        // then: 89가 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("89")))
    }

    @ParameterizedTest
    @ValueSource(ints = [R.id.buttonPlus, R.id.buttonMinus, R.id.buttonMultiply, R.id.buttonDivide])
    fun 빈_화면에서_사칙연산_버튼을_누르면_화면에_아무런_변화가_없어야_한다(operationStringResId: Int) {
        // when: 사용자가 연산자 사칙연산 버튼을 누르면
        onView(withId(operationStringResId)).perform(click())

        // then: 화면에 아무런 변화가 없어야 한다.
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun 버튼_8_플러스_를_누른_경우_화면에_8_플러스가_보여야_한다() {
        // given: 8이 입력되어 있을 때
        onView(withId(R.id.button8)).perform(click())

        // 사용자가 연산자 + 버튼을 누르면
        onView(withId(R.id.buttonPlus)).perform(click())

        // '8 +' 가 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("8 +")))
    }

    @Test
    fun 화면에_8_플러스_수식이_있을때_마이너스를_누른_경우_화면에_8_마이너스가_보여야_한다() {
        // given: 8 + 가 입력되어 있을 때
        onView(withId(R.id.button8)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // 사용자가 연산자 - 버튼을 누르면
        onView(withId(R.id.buttonMinus)).perform(click())

        // '8 -' 가 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("8 -")))
    }

    @Test
    fun 화면에_8_플러스_수식이_있을때_곱하기_버튼을_누른_경우_화면에_8_곱하기가_보여야_한다() {
        // given: 8 + 가 입력되어 있을 때
        onView(withId(R.id.button8)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // 사용자가 연산자 × 버튼을 누르면
        onView(withId(R.id.buttonMultiply)).perform(click())

        // '8 ×' 가 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("8 *")))
    }

    @Test
    fun 화면에_8_플러스_수식이_있을때_나누기를_누른_경우_화면에_8_나누기가_보여야_한다() {
        // given: 8 + 가 입력되어 있을 때
        onView(withId(R.id.button8)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // 사용자가 연산자 ÷ 버튼을 누르면
        onView(withId(R.id.buttonDivide)).perform(click())

        // '8 ÷' 가 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("8 /")))
    }

    @Test
    fun 화면에_8_플러스_수식이_있을때_플러스를_누른_경우_화면에_8_플러스가_보여야_한다() {
        // given: 8 + 가 입력되어 있을 때
        onView(withId(R.id.button8)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // 사용자가 연산자 + 버튼을 누르면
        onView(withId(R.id.buttonPlus)).perform(click())

        // '8 +' 가 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("8 +")))
    }

    @Test
    fun 빈화면에서_지우기_버튼을_누르면_화면에_아무런_변화가_없어야_한다() {
        // when: 사용자가 지우기 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: 화면에 아무런 변화가 없어야 한다.
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun 화면에_32_플러스_1_수식이_있을때_지우기_버튼을_누르면_화면에_32_플러스가_보여야_한다() {
        // given: 32 + 1 수식이 있을 때
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // when: 사용자가 지우기 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '32 +' 가 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("32 +")))
    }

    @Test
    fun 화면에_32_플러스_수식이_있을때_지우기_버튼을_누르면_화면에_32가_보여야_한다() {
        // given: 32 + 수식이 있을 때
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // when: 사용자가 지우기 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '32' 가 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("32")))
    }

    @Test
    fun 화면에_32_수식이_있을때_지우기_버튼을_누르면_화면에_3이_보여야_한다() {
        // given: 32 수식이 있을 때
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())

        // when: 사용자가 지우기 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: '3' 이 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun 화면에_3_수식이_있을때_지우기_버튼을_누르면_화면에_빈화면이_보여야_한다() {
        // given: 3 수식이 있을 때
        onView(withId(R.id.button3)).perform(click())

        // when: 사용자가 지우기 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // then: 빈 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun 화면에_3_플러스_2_수식이_있을때_등호_버튼을_누르면_5가_화면에_보여야한다() {
        // given: 3 + 2 수식이 있을 때,
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button2)).perform(click())

        // when: 사용자가 = 버튼을 누르면
        onView(withId(R.id.buttonEquals)).perform(click())

        // then: 5가 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }
}