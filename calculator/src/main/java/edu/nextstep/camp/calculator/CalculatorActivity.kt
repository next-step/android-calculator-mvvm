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
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setEventListener()
    }

    private fun setEventListener() {
        viewModel.calculatorError.observe(this@CalculatorActivity) {
            it.consume() ?: return@observe
            Toast.makeText(this@CalculatorActivity, R.string.toast_incomplete_expression, Toast.LENGTH_SHORT).show()
        }
    }
}
