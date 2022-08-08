package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels { ViewModelFactory(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpBindingSettings()
        setUpEventObserver()
        setUpCalculationResult()
    }

    private fun setUpCalculationResult() {
        val adapter = CalculationHistoryAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.calculationResults.observe(this@CalculatorActivity) {
            it?.let(adapter::submitList)
        }
    }

    private fun setUpBindingSettings() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@CalculatorActivity
    }

    private fun setUpEventObserver() {
        viewModel.event.observe(this@CalculatorActivity) {
            it.consume()?.let(::showToastWithCalculatorEvent)
        }
    }

    private fun showToastWithCalculatorEvent(event: CalculatorEvent) {
        Toast.makeText(this, event.stringId, Toast.LENGTH_SHORT).show()
    }

}
