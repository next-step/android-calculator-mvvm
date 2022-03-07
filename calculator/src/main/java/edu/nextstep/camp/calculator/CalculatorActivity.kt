package edu.nextstep.camp.calculator

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.calculator.adapter.CalculationMemoryAdapter
import edu.nextstep.camp.calculator.data.repository.Injector
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.calculator.domain.Calculator

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels { ViewModelFactory(this) }
    private val calculationMemoryAdapter by lazy { CalculationMemoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initRecyclerView()
        showMessage()
        initCalculationMemory()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = calculationMemoryAdapter
    }

    private fun showMessage() {
        viewModel.showExpressionErrorMessage.observe(this) {
            if (it.consumed) return@observe
            Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
            it.consume()
        }
    }

    private fun initCalculationMemory() {
        viewModel.calculationMemories.observe(this) {
            calculationMemoryAdapter.submitList(it)
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
            return CalculatorViewModel(calculator = Calculator(), calculatorRepository =  Injector.provideSampleRepository(context))
        }
    }
}
