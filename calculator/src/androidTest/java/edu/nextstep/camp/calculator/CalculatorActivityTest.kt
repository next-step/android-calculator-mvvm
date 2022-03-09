package edu.nextstep.camp.calculator

import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.repository.CalculateRepository
import edu.nextstep.camp.calculator.domain.repository.History
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import util.ExpressionUtil.toExpression


@RunWith(AndroidJUnit4::class)
class CalculatorActivityTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(CalculatorActivity::class.java)
    private lateinit var calculateRepository: CalculateRepository

    private val savedHistory = listOf(
        History(Expression(listOf("1 + 2 - 1")), Expression()),
        History( "1 + 3 - 1".toExpression(), "1".toExpression()),
        History( "1 + 5 * 2".toExpression(), "12".toExpression()),
    )

    @Before
    fun setUp() {
        calculateRepository = mockk()
        coEvery { calculateRepository.getHistoryAll() } returns savedHistory

        activityScenarioRule.scenario.onActivity {
            it.viewModelStore.clear()
            ViewModelProvider(
                it,
                CalculatorViewModelFactory(
                    calculateRepository = calculateRepository,
                ),
            ).get(CalculatorViewModel::class.java)
        }
        activityScenarioRule.scenario.recreate()
    }

    @Test
    fun `Hisotry를_표시하는_버튼을_클릭하면_저장되어_있는_History가_표시형식에_맞게_표시된다`() {
        onView(ViewMatchers.withId(R.id.buttonMemory)).perform(ViewActions.click())

        onView(ViewMatchers
            .withText(
                savedHistory.joinToString(separator = "\n\n") { getStringForDisplay(it) }
            )).check(matches(ViewMatchers.isDisplayed()))
    }

    private fun getStringForDisplay(history: History): String {
        return "${history.expression}\n= ${history.result}"
    }
}