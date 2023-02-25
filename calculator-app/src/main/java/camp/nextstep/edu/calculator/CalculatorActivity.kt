package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding
import camp.nextstep.edu.calculator.recyclerview.ResultListAdapter


class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding

    private val viewModel: CalculatorViewModel by viewModels { ViewModelFactory() }

    private val resultAdapter = ResultListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()

        setBindingVariables()

        observeViewModel()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView<ActivityCalculatorBinding>(
            this, R.layout.activity_calculator
        ).apply {
            lifecycleOwner = this@CalculatorActivity
            list.adapter = resultAdapter
        }
    }

    private fun setBindingVariables() {
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }

    private fun observeViewModel() {
        viewModel.warning.observe(this) {
            makeToast()
        }

        viewModel.allResults.observe(this) {
            resultAdapter.submitList(it)
        }
    }

    private fun makeToast() {
        Toast.makeText(
            applicationContext,
            R.string.incomplete_expression,
            Toast.LENGTH_SHORT
        ).show()
    }
}
