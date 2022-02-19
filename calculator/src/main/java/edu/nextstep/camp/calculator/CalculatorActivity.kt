package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    private val calculatorViewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater).apply {
            viewModel = calculatorViewModel
            lifecycleOwner = this@CalculatorActivity
        }
        setContentView(binding.root)

        calculatorViewModel.eventShowIncompleteExpressionError.observe(this) {
            if (it.consumed) return@observe
            Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
            it.consume()
        }
    }
}
