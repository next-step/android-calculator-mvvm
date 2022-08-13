package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.data.CalculationHistoryRepositoryImpl
import edu.nextstep.camp.data.local.CalculatorDatabase
import edu.nextstep.camp.domain.calculator.CalculationHistory
import edu.nextstep.camp.domain.calculator.usecase.GetAllCalculationHistoryUseCase
import edu.nextstep.camp.domain.calculator.usecase.InsertCalculationHistoryUseCase

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels {
        val database = CalculatorDatabase.getCalculatorDatabase(application)
        val calculationHistoryRepository = CalculationHistoryRepositoryImpl(database.calculationHistoryEntityDao)
        val insertCalculationHistoryUseCase = InsertCalculationHistoryUseCase(calculationHistoryRepository)
        val getAllCalculationHistoryUseCase = GetAllCalculationHistoryUseCase(calculationHistoryRepository)

        CalculatorViewModelFactory(insertCalculationHistoryUseCase, getAllCalculationHistoryUseCase)
    }

    private val calculationHistoryAdapter = CalculationHistoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculatorBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initBinding()

        initView()

        observeUiState()
    }

    private fun initView() {
        binding.recyclerView.adapter = calculationHistoryAdapter
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun observeUiState() {
        viewModel.errorMessage.observe(this) { errorMessage ->
            errorMessage ?: return@observe

            showErrorMessage(errorMessage)
        }

        viewModel.calculationHistoryList.observe(this) { calculationHistoryList ->
            calculationHistoryList ?: return@observe

            setCalculationHistoryList(calculationHistoryList)
        }
    }

    private fun showErrorMessage(errorMessage: UiText) {
        Toast.makeText(this, errorMessage.asString(this), Toast.LENGTH_LONG).show()
    }

    private fun setCalculationHistoryList(calculationHistoryList: List<CalculationHistory>) {
        calculationHistoryAdapter.submitList(calculationHistoryList)
    }
}
