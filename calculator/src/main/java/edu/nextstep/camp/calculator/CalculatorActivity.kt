package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.calculator.domain.CalculationResult

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels { ViewModelFactory(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBindingSettings()
        setViewModelObservers()
        viewModel.requestGetCalculationResultsFromDB()
        setCalculationResultRecyclerView()
    }

    private fun setBindingSettings() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@CalculatorActivity
    }

    private fun setViewModelObservers() {
        viewModel.event.observe(this@CalculatorActivity) {
            it.consume()?.let(::showToastWithCalculatorEvent)
        }
        viewModel.calculationResults.observe(this@CalculatorActivity) {
            it?.let(::changeCalculateResults)
        }
    }

    private fun changeCalculateResults(calculationResultList: List<CalculationResult>) {
        val adapter = binding.recyclerView.adapter as CalculationHistoryAdapter
        adapter.submitList(calculationResultList)
    }

    private fun setCalculationResultRecyclerView() {
        binding.recyclerView.apply {
            adapter = CalculationHistoryAdapter()
        }
    }

    private fun showToastWithCalculatorEvent(event: CalculatorEvent) {
        Toast.makeText(this, event.stringId, Toast.LENGTH_SHORT).show()
    }

}
