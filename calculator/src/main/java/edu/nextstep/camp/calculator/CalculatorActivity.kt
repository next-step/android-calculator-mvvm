package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import kotlinx.coroutines.launch

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val calculatorViewModel: CalculatorViewModel by viewModels { ViewModelFactory(this) }
    private val recordAdapter = RecordAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calculator)
        binding.lifecycleOwner = this
        binding.calculatorViewModel = calculatorViewModel

        initRecyclerView()
        getStatement()
        showError()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = recordAdapter
    }

    private fun showError() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                calculatorViewModel.errorMessage.collect {
                    Toast.makeText(this@CalculatorActivity, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getStatement() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                calculatorViewModel.getStatements().collect {
                    recordAdapter.submitList(it)
                    binding.recyclerView.scrollToPosition(recordAdapter.itemCount)
                }
            }
        }
    }
}
