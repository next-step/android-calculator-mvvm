package camp.nextstep.edu.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.example.calculator_data.Injector
import com.example.domain.models.Calculator
import com.example.domain.usecases.GetHistoriesUseCase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CalculatorViewModel by viewModels {
        val repository = Injector.provideRepository(applicationContext, false)
        CalculatorViewModelFactory(
            Calculator(historyRepository = repository),
            GetHistoriesUseCase(historyRepository = repository),
        )
    }
    private val historyAdapter: HistoryAdapter by lazy {
        HistoryAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        observeException()
        observeHistory()
    }

    private fun init() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = historyAdapter
    }

    private fun observeException() {
        viewModel.exceptionMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeHistory() {
        viewModel.histories.observe(this) {
            historyAdapter.submitList(it)
        }
    }
}

@BindingAdapter("android:setHistoryViewVisibility")
fun setHistoryViewVisibility(view: View?, showHistory: Boolean?) {
    view?.visibility = if (showHistory == true) View.VISIBLE else View.GONE
}

@BindingAdapter("android:setTextViewVisibility")
fun setTextViewVisibility(view: View?, showHistory: Boolean?) {
    view?.visibility = if (showHistory == true) View.GONE else View.VISIBLE
}
