package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val calculatorViewModel: CalculatorViewModel by viewModels { ViewModelFactory(this) }

    private val historyAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initObserve()
        initView()
    }

    private fun initViewModel() {
        binding.lifecycleOwner = this
        binding.viewModel = calculatorViewModel
    }

    private fun initObserve() {
        calculatorViewModel.errorToast.observe(this) {
            if (it) {
                Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        calculatorViewModel.historyDataList.observe(this) {
            historyAdapter.submitList(it)
        }
    }

    private fun initView() {
        binding.recyclerView.adapter = historyAdapter
    }

}
