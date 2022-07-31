package edu.nextstep.camp.calculator

import de.mannodermaus.junit5.ActivityScenarioExtension
import edu.nextstep.camp.common.AndroidTestHelper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("Calculator 화면 테스트")
internal class CalculatorActivityTest {
    @JvmField
    @RegisterExtension
    val scenarioExtension = ActivityScenarioExtension.launch<CalculatorActivity>()

    @CsvSource(
        "${R.id.button0}, '0'",
        "${R.id.button1}, '1'",
        "${R.id.button2}, '2'",
        "${R.id.button3}, '3'",
        "${R.id.button4}, '4'",
        "${R.id.button5}, '5'",
        "${R.id.button6}, '6'",
        "${R.id.button7}, '7'",
        "${R.id.button8}, '8'",
        "${R.id.button9}, '9'"
    )
    @DisplayName("버튼을 누르면 해당 버튼의 값이 표시된다")
    @ParameterizedTest(name = "예상결과 = {1}")
    fun `버튼_동작`(buttonId: Int, expectedText: String) {
        //when: 버튼을 누르면
        AndroidTestHelper.onViewsClick(buttonId)

        //then: 화면에 버튼의 텍스트가 보여야 한다
        AndroidTestHelper.onViewMatchText(R.id.textView, expectedText)
    }

    // -> 1 클릭 -> 1
    @Test
    fun `입력된_피연산자가_없을때_숫자_클릭은_해당_숫자가_표시된다`() {
        AndroidTestHelper.onViewsClick(R.id.button1)

        AndroidTestHelper.onViewMatchText(R.id.textView, "1")
    }

    /* 5 + -> 1 클릭 -> 5 + 1 */
    @Test
    fun `입력_중_수식이_있을_때_숫자_클릭은_해당_숫자가_뒤에_추가된다`() {
        AndroidTestHelper.onViewsClick(R.id.button5, R.id.buttonPlus)

        AndroidTestHelper.onViewsClick(R.id.button1)

        AndroidTestHelper.onViewMatchText(R.id.textView, "5 + 1")
    }

    //'8' -> '9' 클릭 = '89'
    @Test
    fun `입력된_피연산자가_있을때_숫자_클릭은_기존_숫자_뒤에_해당_숫자가_표시된다`() {
        AndroidTestHelper.onViewsClick(R.id.button8)

        AndroidTestHelper.onViewsClick(R.id.button9)

        AndroidTestHelper.onViewMatchText(R.id.textView, "89")
    }

    //' ' -> '+ or - or / or *' 클릭 = ' '
    @Test
    fun `입력된_피연산자가_없을때_연산자_클릭은_변화가_없다`() {
        AndroidTestHelper.onViewsClick(R.id.buttonPlus, R.id.buttonDivide, R.id.buttonMultiply, R.id.buttonMinus)

        AndroidTestHelper.onViewMatchText(R.id.textView, "")
    }

    //1 -> '+' 클릭 = '1 +'
    @Test
    fun `입력된_피연산자가_있을때_연산자_클릭은_해당_기호가_보인다`() {
        AndroidTestHelper.onViewsClick(R.id.button1)

        AndroidTestHelper.onViewsClick(R.id.buttonPlus)

        AndroidTestHelper.onViewMatchText(R.id.textView, "1 +")
    }

    //'1 +' -> '-' 클릭 = '1 -'
    @Test
    fun `입력된_연산자_있을_때_연산자_클릭_시_해당_연산자로_교체된다`() {
        AndroidTestHelper.onViewsClick(R.id.button1, R.id.buttonPlus)

        AndroidTestHelper.onViewsClick(R.id.buttonMinus)

        AndroidTestHelper.onViewMatchText(R.id.textView, "1 -")
    }

    //' ' -> '삭제' 클릭 = ' '
    @Test
    fun `입력된_수식이_없을때_지우기_클릭은_변화가_없다`() {
        AndroidTestHelper.onViewsClick(R.id.buttonDelete)

        AndroidTestHelper.onViewMatchText(R.id.textView, "")
    }

    //'32 + 1' -> '삭제' 5 회 클릭 = ' '
    @Test
    fun `입력된_수식이_있을때_지우기_클릭은_마지막_연산자_또는_피연산자가_삭제된다`() {
        //given
        AndroidTestHelper.onViewsClick(R.id.button3, R.id.button2, R.id.buttonPlus, R.id.button1)

        //when
        AndroidTestHelper.onViewsClick(R.id.buttonDelete)

        //then
        AndroidTestHelper.onViewMatchText(R.id.textView, "32 +")

        //when
        AndroidTestHelper.onViewsClick(R.id.buttonDelete)

        //then
        AndroidTestHelper.onViewMatchText(R.id.textView, "32")

        //when
        AndroidTestHelper.onViewsClick(R.id.buttonDelete)

        //then
        AndroidTestHelper.onViewMatchText(R.id.textView, "3")

        //when
        AndroidTestHelper.onViewsClick(R.id.buttonDelete)

        //then
        AndroidTestHelper.onViewMatchText(R.id.textView, "")

        //when
        AndroidTestHelper.onViewsClick(R.id.buttonDelete)

        //then
        AndroidTestHelper.onViewMatchText(R.id.textView, "")
    }

    //'3 + 2' -> '=' 클릭 = '5'
    @Test
    fun `입력된_수식이_완전할때_등호_클릭은_수식의_결과가_표시된다`() {
        //given
        AndroidTestHelper.onViewsClick(R.id.button3, R.id.buttonPlus, R.id.button2)
        //when
        AndroidTestHelper.onViewsClick(R.id.buttonEquals)

        //then
        AndroidTestHelper.onViewMatchText(R.id.textView, "5")
    }

    //'3 +' -> '=' 클릭 = '3 +'
    @Test
    fun `입력된_수식이_완전하지_않을때_등호_클릭은_변화가_없다`() {
        //given
        AndroidTestHelper.onViewsClick(R.id.button3, R.id.buttonPlus)
        //when
        AndroidTestHelper.onViewsClick(R.id.buttonEquals)

        //then
        AndroidTestHelper.onViewMatchText(R.id.textView, "3 +")
    }
}