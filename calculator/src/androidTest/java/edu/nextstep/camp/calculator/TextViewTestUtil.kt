package edu.nextstep.camp.calculator

import android.view.View
import android.widget.TextView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

fun setTextInTextView(str: String): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return CoreMatchers.allOf(
                ViewMatchers.isDisplayed(),
                ViewMatchers.isAssignableFrom(
                    TextView::class.java
                )
            )
        }

        override fun perform(uiController: UiController, view: View) {
            (view as TextView).text = str
        }

        override fun getDescription(): String {
            return "replace text"
        }
    }
}
