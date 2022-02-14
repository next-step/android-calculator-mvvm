package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import kotlinx.coroutines.flow.collect

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val calculatorViewModel: CalculatorViewModel by viewModels()
    private val recordAdapter by lazy { RecordAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calculator)
        binding.lifecycleOwner = this
        binding.calculatorViewModel = calculatorViewModel

        initRecyclerView()
        recordStatement()
        showError()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = recordAdapter
    }

    private fun showError() {
        lifecycleScope.launchWhenStarted {
            calculatorViewModel.errorString.collect {
                Toast.makeText(this@CalculatorActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun recordStatement() {
        lifecycleScope.launchWhenStarted {
            calculatorViewModel.recordStatement.collect {
                recordAdapter.addStatement(it ?: return@collect)
            }
        }
    }
}
