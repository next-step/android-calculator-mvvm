package edu.nextstep.camp.calculator

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.calculator.data.CalculatorRepositoryProvider
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.calculator.domain.repository.History

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private lateinit var  viewModel: CalculatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calculator)
        binding.lifecycleOwner = this

        viewModel = createViewModel()
        binding.viewModel = viewModel
        initViewModelObserves(viewModel)
    }

    private fun initViewModelObserves(viewModel: CalculatorViewModel) {
        viewModel.calculateFailed.observe(this) {
            Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
        }

        viewModel.calculateHistory.observe(this) {
            it?.let {
                AlertDialog.Builder(this)
                    .setMessage(it.joinToString(separator = "\n\n") { history ->
                        getStringForDisplay(history)
                    })
                    .show()
            }
        }
    }

    private fun createViewModel(): CalculatorViewModel {
        return ViewModelProvider(
            this,
            CalculatorViewModelFactory(
                calculateRepository = CalculatorRepositoryProvider.getInstance(applicationContext)
            )
        ).get(CalculatorViewModel::class.java)
    }

    private fun getStringForDisplay(history: History): String {
        return "${history.expression}\n= ${history.result}"
    }
}
