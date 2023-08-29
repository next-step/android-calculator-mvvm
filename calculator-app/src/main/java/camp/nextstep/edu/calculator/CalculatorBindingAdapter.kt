/**
 * @author Daewon on 29,August,2023
 *
 */

package camp.nextstep.edu.calculator

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("visibleHistoryMode")
fun setVisibleHistoryMode(view: View, uiState: UiState) {
    when(uiState) {
        is UiState.Result -> view.visibility = View.GONE
        is UiState.History -> view.visibility = View.VISIBLE
    }
}

@BindingAdapter("goneHistoryMode")
fun setGoneHistoryMode(view: View, uiState: UiState) {
    when(uiState) {
        is UiState.Result -> view.visibility = View.VISIBLE
        is UiState.History -> view.visibility = View.GONE
    }
}

@BindingAdapter("result")
fun setResultText(view: TextView, uiState: UiState) {
    when(uiState) {
        is UiState.Result -> view.text = uiState.result
        else -> Unit
    }
}
