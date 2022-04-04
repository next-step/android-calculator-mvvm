package edu.nextstep.camp.calculator

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.data.injector.Injector

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels { ViewModelFactory(this) }
    private val historyAdapter = CalculatorHistoryAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.vm = viewModel
        binding.recyclerView.adapter = historyAdapter


        viewModel.showErrorMessage.observe(this) {
            it.consume()?.let {
                Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.historyList.observe(this) {
            historyAdapter.submitList(it)

        }

    }

    class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return when (modelClass) {
                CalculatorViewModel::class.java -> createMainViewModel()
                else -> throw IllegalArgumentException()
            } as T
        }

        private fun createMainViewModel(): CalculatorViewModel {
            return CalculatorViewModel(
                calculatorRepository = Injector.providesCalculatorRepository(context)
            )
        }
    }

}
