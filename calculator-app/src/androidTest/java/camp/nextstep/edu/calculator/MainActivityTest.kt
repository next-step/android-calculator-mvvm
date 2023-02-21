package camp.nextstep.edu.calculator

import androidx.activity.viewModels
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.calculator_data.DaoModule
import com.example.calculator_data.DatabaseModule
import com.example.calculator_data.RepositoryModule
import com.example.domain.models.Calculator
import com.example.domain.usecases.GetHistoriesUseCase
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)


    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity {

            val repository = RepositoryModule.providerHistoryRepository(
                historyDao = DaoModule.provideHistoryDao(
                    DatabaseModule.provideDatabase(
                        ApplicationProvider.getApplicationContext()
                    )
                )
            )

            val factory = CalculatorViewModelFactory(
                calculator = Calculator(
                    historyRepository = repository
                ),
                getHistoriesUseCase = GetHistoriesUseCase(historyRepository = repository)
            )
            it.viewModels<CalculatorViewModel>(factoryProducer = { factory })
        }
    }

    @Test
    fun `사용자가_피연산자_0_버튼을_누르면_화면에_0이_보여야_한다`() {
        // when: '0' 버튼을 누르면
        onView(withId(R.id.button0)).perform(click())

        // then: '0'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("0")))
    }

    @Test
    fun `사용자가_피연산자_1_버튼을_누르면_화면에_1이_보여야_한다`() {
        // when: '1' 버튼을 누르면
        onView(withId(R.id.button1)).perform(click())

        // then: '1'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("1")))
    }

    @Test
    fun `사용자가_피연산자_2_버튼을_누르면_화면에_2이_보여야_한다`() {
        // when: '2' 버튼을 누르면
        onView(withId(R.id.button2)).perform(click())

        // then: '2'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("2")))
    }

    @Test
    fun `사용자가_피연산자_3_버튼을_누르면_화면에_3이_보여야_한다`() {
        // when: '3' 버튼을 누르면
        onView(withId(R.id.button3)).perform(click())

        // then: '3'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun `사용자가_피연산자_4_버튼을_누르면_화면에_4이_보여야_한다`() {
        // when: '4' 버튼을 누르면
        onView(withId(R.id.button4)).perform(click())

        // then: '4'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("4")))
    }

    @Test
    fun `사용자가_피연산자_5_버튼을_누르면_화면에_5이_보여야_한다`() {
        // when: '5' 버튼을 누르면
        onView(withId(R.id.button5)).perform(click())

        // then: '5'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun `사용자가_피연산자_6_버튼을_누르면_화면에_6이_보여야_한다`() {
        // when: '6' 버튼을 누르면
        onView(withId(R.id.button6)).perform(click())

        // then: '6'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("6")))
    }

    @Test
    fun `사용자가_피연산자_7_버튼을_누르면_화면에_7이_보여야_한다`() {
        // when: '7' 버튼을 누르면
        onView(withId(R.id.button7)).perform(click())

        // then: '7'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("7")))
    }

    @Test
    fun `사용자가_피연산자_8_버튼을_누르면_화면에_8이_보여야_한다`() {
        // when: '8' 버튼을 누르면
        onView(withId(R.id.button8)).perform(click())

        // then: '8'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("8")))
    }

    @Test
    fun `사용자가_피연산자_9_버튼을_누르면_화면에_9이_보여야_한다`() {
        // when: '9' 버튼을 누르면
        onView(withId(R.id.button9)).perform(click())

        // then: '9'이 보여야 한다
        onView(withId(R.id.textView)).check(matches(withText("9")))
    }

    @Test
    fun `5_덧셈기호_일때_1_버튼을_누르면_5_덧셈기호_1_보여야_한다`() {
        // Given
        onView(withId(R.id.button5)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("5 +")))
        // When
        onView(withId(R.id.button1)).perform(click())

        // Then
        onView(withId(R.id.textView)).check(matches(withText("5 + 1")))
    }

    @Test
    fun `8_일때_9_버튼을_누르면_89_보여야_한다`() {
        onView(withId(R.id.button8)).perform(click())

        // When
        onView(withId(R.id.button9)).perform(click())

        // Then
        onView(withId(R.id.textView)).check(matches(withText("89")))
    }

    @Test
    fun `공백일때_연산자_버튼을_누르면_아무_변화없다`() {

        // When
        onView(withId(R.id.buttonPlus)).perform(click())

        // Then
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `1일때_연선자_버튼을_누르면_1_덧셈기호_반환한다`() {
        // Given
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("1")))

        // When
        onView(withId(R.id.buttonPlus)).perform(click())

        // Then
        onView(withId(R.id.textView)).check(matches(withText("1 +")))
    }

    @Test
    fun `1_덧셈기호_일때_빼기_연산자_버튼을_누르면_1_뺄셈기호_반환한다`() {
        // Given
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("1 +")))

        // When
        onView(withId(R.id.buttonMinus)).perform(click())

        // Then
        onView(withId(R.id.textView)).check(matches(withText("1 -")))
    }

    @Test
    fun `공백일때_지우기_버튼을_누르면_아무변화가_없다`() {
        // When
        onView(withId(R.id.buttonDelete)).perform(click())

        // Then
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `32_덧셈기호_일때_지우기_버튼을_누르면_32가_된다`() {
        // Given
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("32 +")))

        // When
        onView(withId(R.id.buttonDelete)).perform(click())

        // Then
        onView(withId(R.id.textView)).check(matches(withText("32")))
    }

    @Test
    fun `두자리_수_이상의_피연산자_32일_때_지우기_버튼을_누르면_한자릿_수의_숫자3가_된다`() {
        // Given
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("32")))
        // When
        onView(withId(R.id.buttonDelete)).perform(click())

        // Then
        onView(withId(R.id.textView)).check(matches(withText("3")))
    }

    @Test
    fun `한자리_수의_피연산자_3일_때_지우기_버튼을_누르면_공백이_된다`() {
        // Given
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("3")))

        // When
        onView(withId(R.id.buttonDelete)).perform(click())

        // Then
        onView(withId(R.id.textView)).check(matches(withText("")))
    }

    @Test
    fun `3_덧셈기호_2일때_계산_버튼을_누르면_5를_반환한다`() {
        // GIVEN
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button2)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("3 + 2")))

        // When
        onView(withId(R.id.buttonEquals)).perform(click())

        // Then
        onView(withId(R.id.textView)).check(matches(withText("5")))
    }

    @Test
    fun `3_덧셈기호_일떄_계산_버튼을_누르면_결과가_나오지_않는다`() {
        // GIVEN
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("3 +")))

        // When
        onView(withId(R.id.buttonEquals)).perform(click())

        // Then
        onView(withId(R.id.textView)).check(matches(withText("3 +")))
    }

    @Test
    fun `메모리버튼을_누르면_현재계산식이_사라지고_기록화면이_보인다`() {
        // Given
        onView(withId(R.id.textView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(Visibility.GONE)))

        // When
        onView(withId(R.id.buttonMemory)).perform(click())

        // Then
        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.textView)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun `처음_메모리버튼을_누르면_빈_기록화면이_보인다`() {
        // GIVEN
        onView(withId(R.id.buttonMemory)).perform(click())

        // Then
        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.textView)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.list_item)).check(matches(not(isDisplayed())))
    }

    @Test
    fun `계산_후_메모리버튼을_누르면_기록화면에서_기록이_보인다`() {
        // GIVEN
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button2)).perform(click())


        // When
        onView(withId(R.id.buttonEquals)).perform(click())

        // Then
        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.textView)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.list_item)).check(matches(not(isDisplayed())))
    }
}
