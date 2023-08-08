package camp.nextstep.edu.calculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId

fun onButtonClicked(viewId: Int) {
    onView(withId(viewId)).perform(click())
}

fun onShowTextView(actual: String, viewId: Int) {
    onView(withId(viewId)).check(ViewAssertions.matches(ViewMatchers.withText(actual)))
}