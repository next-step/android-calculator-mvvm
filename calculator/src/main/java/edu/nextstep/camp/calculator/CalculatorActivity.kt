package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewBinding()
        initObserver()
    }

    private fun initViewBinding() {
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)
    }

    private fun initObserver() {
        viewModel.calculationFailed.observe(this) {
            showCalculationFailedToast()
        }
    }

    private fun showCalculationFailedToast() {
        Toast
            .makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT)
            .show()
    }
}
