package camp.nextstep.edu.calculator

import android.os.Looper.getMainLooper
import android.widget.Button
import android.widget.TextView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.calculator.domain.Calculator
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.controller.ActivityController

@RunWith(RobolectricTestRunner::class)
class CalculatorActivityTest {
    lateinit var activityController: ActivityController<CalculatorActivity>

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        activityController = Robolectric.buildActivity(CalculatorActivity::class.java)
    }

    @Test
    fun `입력된_피연산자가_없을_때_사용자가_피연산자_0_부터_9_버튼을_누르면_화면에_해당_숫자가_화면에_보여야_한다`() {
        activityController.setup()
        val activity = activityController.get()

        // when
        activity.findViewById<Button>(R.id.button0).performClick()
        activity.findViewById<Button>(R.id.button1).performClick()
        activity.findViewById<Button>(R.id.button2).performClick()
        activity.findViewById<Button>(R.id.button3).performClick()
        activity.findViewById<Button>(R.id.button4).performClick()
        activity.findViewById<Button>(R.id.button5).performClick()
        activity.findViewById<Button>(R.id.button6).performClick()
        activity.findViewById<Button>(R.id.button7).performClick()
        activity.findViewById<Button>(R.id.button8).performClick()
        activity.findViewById<Button>(R.id.button9).performClick()

        // then
        shadowOf(getMainLooper()).idle()
        val result = activity.findViewById<TextView>(R.id.textView).text.toString()
        assertThat(result).isEqualTo("123456789")
    }

    @Test
    fun `입력된_피연산자가_있을_때_기존_숫자_뒤에_해당_숫자가_화면에_보여야_한다_예를_들면_8이_입력되어_있을_때_9를_입력하면_89가_보여야_한다`() {
        activityController.setup()
        val activity = activityController.get()
        // when
        activity.findViewById<Button>(R.id.button8).performClick()
        activity.findViewById<Button>(R.id.button9).performClick()

        // then
        shadowOf(getMainLooper()).idle()
        val result = activity.findViewById<TextView>(R.id.textView).text
        assertThat(result).isEqualTo("89")
    }

    @Test
    fun `입력된_피연산자가_없을_때_사용자가_연산자_덧셈_뺄셈_곱셈_나눗셈_버튼을_누르면_화면에_아무런_변화가_없어야_한다`() {
        activityController.setup()
        val activity = activityController.get()
        // when  -> + 클릭 ->
        activity.findViewById<Button>(R.id.buttonPlus).performClick()

        // then
        shadowOf(getMainLooper()).idle()
        val result = activity.findViewById<TextView>(R.id.textView).text
        assertThat(result).isEqualTo("")
    }

    @Test
    fun `입력된_피연산자가_있을_때_사용자가_연산자_덧셈_뺄셈_곱셈_나눗셈_버튼을_누르면_해당_기호가_화면에_보여야_한다`() {
        activityController.setup()
        val activity = activityController.get()
        // when
        // 1 -> + 클릭 -> 1 +
        activity.findViewById<Button>(R.id.button1).performClick()
        activity.findViewById<Button>(R.id.buttonPlus).performClick()

        // then
        shadowOf(getMainLooper()).idle()
        var result = activity.findViewById<TextView>(R.id.textView).text
        assertThat(result).isEqualTo("1 +")

        // 1 + ->
        //        // when - 클릭 -> 1 -
        activity.findViewById<Button>(R.id.buttonMinus).performClick()

        // then
        shadowOf(getMainLooper()).idle()
        result = activity.findViewById<TextView>(R.id.textView).text
        assertThat(result).isEqualTo("1 -")
    }

    @Test
    fun `입력된_수식이_없을_때_사용자가_지우기_버튼을_누르면_화면에_아무런_변화가_없어야_한다`() {
        activityController.setup()
        val activity = activityController.get()
        // when
        activity.findViewById<Button>(R.id.buttonDelete).performClick()

        // then
        shadowOf(getMainLooper()).idle()
        val result = activity.findViewById<TextView>(R.id.textView).text
        assertThat(result).isEqualTo("")
    }

    @Test
    fun `입력된_수식이_있을_때_사용자가_지우기_버튼을_누르면_수식에_마지막으로_입력된_연산자_또는_피연산자가_지워져야_한다`() {
        activityController.setup()
        val activity = activityController.get()
        // when
        // 32 + 1 -> 지우기 클릭 -> 32 + -> 지우기 클릭 -> 32 -> 지우기 클릭 -> 3 -> 지우기 클릭 ->  -> 지우기 클릭 ->
        activity.findViewById<Button>(R.id.button3).performClick()
        activity.findViewById<Button>(R.id.button2).performClick()
        activity.findViewById<Button>(R.id.buttonPlus).performClick()
        activity.findViewById<Button>(R.id.button1).performClick()

        // then
        activity.findViewById<Button>(R.id.buttonDelete).performClick()
        shadowOf(getMainLooper()).idle()

        var result = activity.findViewById<TextView>(R.id.textView).text
        assertThat(result).isEqualTo("32 +")

        activity.findViewById<Button>(R.id.buttonDelete).performClick()
        shadowOf(getMainLooper()).idle()

        result = activity.findViewById<TextView>(R.id.textView).text
        assertThat(result).isEqualTo("32")

        activity.findViewById<Button>(R.id.buttonDelete).performClick()
        shadowOf(getMainLooper()).idle()

        result = activity.findViewById<TextView>(R.id.textView).text
        assertThat(result).isEqualTo("3")

        activity.findViewById<Button>(R.id.buttonDelete).performClick()
        shadowOf(getMainLooper()).idle()

        result = activity.findViewById<TextView>(R.id.textView).text
        assertThat(result).isEqualTo("")

        activity.findViewById<Button>(R.id.buttonDelete).performClick()
        shadowOf(getMainLooper()).idle()

        result = activity.findViewById<TextView>(R.id.textView).text
        assertThat(result).isEqualTo("")
    }

    @Test
    fun `입력된_수신이_완전할_때_사용자가_등호_버튼을_누르면_입력된_수식의_결과가_화면에_보여야_한다`() {
        activityController.setup()
        val activity = activityController.get()
        // when
        // 3 + 2 -> = 클릭 -> 5
        activity.findViewById<Button>(R.id.button3).performClick()
        activity.findViewById<Button>(R.id.buttonPlus).performClick()
        activity.findViewById<Button>(R.id.button2).performClick()
        activity.findViewById<Button>(R.id.buttonEquals).performClick()


        //then
        shadowOf(getMainLooper()).idle()
        val result = activity.findViewById<TextView>(R.id.textView).text

        assertThat(result).isEqualTo("5")
    }
}