package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val calculatorViewModel: CalculatorViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater).apply {
            setContentView(root)
            lifecycleOwner = this@CalculatorActivity
            viewModel = calculatorViewModel
        }

        addObserve()
    }

    private fun addObserve() {
        calculatorViewModel.calculatorErrorMessage.observe(this) { errorMessageStringResourceId ->
            Toast.makeText(this, getString(errorMessageStringResourceId), Toast.LENGTH_SHORT).show()
        }
    }
}
