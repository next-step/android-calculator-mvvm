package edu.nextstep.camp.calculator

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
    fun click0_show0() {
        // when: 사용자가 피연산자 0버튼을 누르면
        onView(withId(R.id.button0)).perform(click())

        // then: 계산기 텍스트에 0이 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("0")))
    }

    @Test
    fun click1_show1() {
        // when: 사용자가 피연산자 1버튼을 누르면
        onView(withId(R.id.button1)).perform(click())

        // then: 계산기 텍스트에 1이 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun click2_show2() {
        // when: 사용자가 피연산자 2버튼을 누르면
        onView(withId(R.id.button2)).perform(click())

        // then: 계산기 텍스트에 2가 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("2")))
    }

    @Test
    fun click3_show3() {
        // when: 사용자가 피연산자 3버튼을 누르면
        onView(withId(R.id.button3)).perform(click())

        // then: 계산기 텍스트에 3이 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun click4_show4() {
        // when: 사용자가 피연산자 4버튼을 누르면
        onView(withId(R.id.button4)).perform(click())

        // then: 계산기 텍스트에 4가 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("4")))
    }

    @Test
    fun click5_show5() {
        // when: 사용자가 피연산자 5버튼을 누르면
        onView(withId(R.id.button5)).perform(click())

        // then: 계산기 텍스트에 5가 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun click6_show6() {
        // when: 사용자가 피연산자 6버튼을 누르면
        onView(withId(R.id.button6)).perform(click())

        // then: 계산기 텍스트에 6이 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("6")))
    }

    @Test
    fun click7_show7() {
        // when: 사용자가 피연산자 7버튼을 누르면
        onView(withId(R.id.button7)).perform(click())

        // then: 계산기 텍스트에 7이 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("7")))
    }

    @Test
    fun click8_show8() {
        // when: 사용자가 피연산자 8버튼을 누르면
        onView(withId(R.id.button8)).perform(click())

        // then: 계산기 텍스트에 8이 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("8")))
    }

    @Test
    fun click9_show9() {
        // when: 사용자가 피연산자 9버튼을 누르면
        onView(withId(R.id.button9)).perform(click())

        // then: 계산기 텍스트에 9가 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("9")))
    }

    @Test
    fun noOperand_input_showStatement() {
        // when: 사용자가 피연산자 1, +, 2를 누르면
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button2)).perform(click())

        // then: 계산기 텍스트에 '1 + 2'가 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("1 + 2")))
    }

    @Test
    fun withOperand_inputOperand_addOperandNextToNumber() {
        // given: 숫자 1이 입력되었을 때
        onView(withId(R.id.button1)).perform(click())

        // when: 사용자가 피연산자 2를 누르면
        onView(withId(R.id.button2)).perform(click())

        // then: 계산기 텍스트에 '12'가 화면에 보여야 한다.
        onView(withId(R.id.textView)).check(matches(withText("12")))
    }

    @Test
    fun withoutOperand_inputOperator_showNothing() {
        // GIVEN - 아무것도 입력이 되어있지 않을 때

        // WHEN - 사용자가 +, -, *, / 를 누르면
        onView(withId(R.id.buttonPlus)).perform(click())

        // THEN - 입력이 되지 않는다.
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun withOperand_inputOperator_showStatement() {
        // GIVEN - 1이 입력되어 있을 때
        onView(withId(R.id.button1)).perform(click())

        // WHEN - 사용자가 +, -, *, / 를 누르면
        onView(withId(R.id.buttonPlus)).perform(click())

        // THEN - 해당 기호가 보인다.
        onView(withId(R.id.textView)).check(matches(withText("1 +")))
    }

    @Test
    fun inputNothing_clickDeleteButton_showNothing() {
        // GIVEN - 아무것도 입력이 되어있지 않을 때

        // WHEN - 사용자가 취소 버튼을 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // THEN - 변화가 없다.
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun inputStatement_oneClickDeleteButton_deleteOperandOrOperator() {
        // GIVEN - 수식 32 + 1 이 입력이 될 때
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // WHEN - 사용자가 취소 버튼 한 번 누르면
        onView(withId(R.id.buttonDelete)).perform(click())

        // THEN - 기호삭제 및 숫자가 하나씩 제거 된다.
        onView(withId(R.id.textView)).check(matches(withText("32 +")))
    }

    @Test
    fun inputStatement_multiClickDeleteButton_deleteOperandOrOperator() {
        // GIVEN - 수식 32 + 1 이 입력이 될 때
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button1)).perform(click())

        // WHEN - 사용자가 취소 버튼을 여러번 누르면
        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.buttonDelete)).perform(click())
        onView(withId(R.id.buttonDelete)).perform(click())

        // THEN - 기호삭제 및 숫자가 하나씩 제거 된다.
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun inputStatement_clickEqualButton_showResult() {
        // GIVEN - 수식 2 + 2 이 입력이 될 때
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button2)).perform(click())

        // WHEN - 사용자가 결과 버튼을 누르면
        onView(withId(R.id.buttonEquals)).perform(click())

        // THEN - 수식의 결과가 화면에 나온다.
        onView(withId(R.id.textView)).check(matches(withText("4")))
    }

    @Test
    fun inputStatement_clickEqualButton_recordStatement() {
        // GIVEN - 수식 2 + 2 이 입력이 될 때
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button2)).perform(click())

        // WHEN - 사용자가 계산 버튼과 기록 보기 버튼을 누르면
        onView(withId(R.id.buttonEquals)).perform(click())
        onView(withId(R.id.buttonMemory)).perform(click())

        // THEN - 수식의 결과가 기록 목록에 저장된 걸 볼 수 있다.
        onView(withId(R.id.tv_expression)).check(matches(withText("2 + 2")))
        onView(withId(R.id.tv_result)).check(matches(withText("= 4")))
    }
}
