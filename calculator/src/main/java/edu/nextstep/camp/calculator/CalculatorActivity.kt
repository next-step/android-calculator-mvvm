package edu.nextstep.camp.calculator

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import edu.nextstep.camp.calculator.data.CalculatorRepositoryProvider
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels {
        CalculatorViewModelFactory(calculateRepository = CalculatorRepositoryProvider.getInstance(applicationContext))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calculator)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.calculateFailed.observe(this) {
            Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
        }

        viewModel.calculateHistory.observe(this) {
            it?.let {
                AlertDialog.Builder(this)
                    .apply { setMessage(it.joinToString(separator = "\n\n")) }
                    .create()
                    .show()
            }
        }
    }
}
