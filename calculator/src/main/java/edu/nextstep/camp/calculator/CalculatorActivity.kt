package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.calculator.data.HistoryDatabase
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    private val viewModel by viewModels<CalculatorViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CalculatorViewModel(
                    historyDao = HistoryDatabase.getInstance(this@CalculatorActivity).historyDao()
                ) as T
            }

        }
    }

    private val adapter by lazy { MainHistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)

        initViewModel()
        initRecyclerView()

    }

    private fun initViewModel() {
        viewModel.expressionError.observe(this) {
            if (it.consumed) return@observe
            Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
            it.consume()
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
    }

}
