package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    private val viewModel: CalculatorViewModel by viewModels { ViewModelFactory(this) }
    private val adapter: CalculatorResultAdapter = CalculatorResultAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this@CalculatorActivity
        setContentView(binding.root)

        setBindingVariables()
        initObserver()
        initCalculatorResultList()
    }

    private fun setBindingVariables() {
        with(binding) {
            vm = viewModel
        }
    }

    private fun initObserver() {
        with(viewModel) {
            inCompleteExpressionEvent.observe(this@CalculatorActivity) {
                Toast.makeText(this@CalculatorActivity, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
            }
            calculationHistories.observe(this@CalculatorActivity) {
                adapter.submitList(it)
            }
        }
    }

    private fun initCalculatorResultList() {
        binding.recyclerView.adapter = adapter
    }
}
