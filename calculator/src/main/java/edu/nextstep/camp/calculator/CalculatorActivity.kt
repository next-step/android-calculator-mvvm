package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator

class CalculatorActivity : AppCompatActivity() {

    private val viewModel: CalculatorViewModel by viewModels()

    private lateinit var binding: ActivityCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.errorEvent.observe(this) {
            Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
        }
    }
}
