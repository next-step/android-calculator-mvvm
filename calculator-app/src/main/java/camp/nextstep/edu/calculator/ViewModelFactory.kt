package camp.nextstep.edu.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return when (modelClass) {
			CalculatorViewModel::class.java -> AppModule.provideCalculatorViewModel(context)
			else -> throw IllegalArgumentException()
		} as T
	}
}