package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import camp.nextstep.edu.calculator.CalculatorViewModel.EventType
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding
import camp.nextstep.edu.calculator.viewmodel.ViewModelProviderFactory

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    private val resultExpressionAdapter: ResultExpressionAdapter = ResultExpressionAdapter()

    private val viewModel: CalculatorViewModel by viewModels {
        ViewModelProviderFactory.getCalculatorViewModel(this@CalculatorActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initRecyclerAdapter()
        observeEvent()
    }

    private fun observeEvent() {
        viewModel.event.observe(this) { event ->
            when (event) {
                EventType.SHOW_TOAST -> {
                    showIncompleteExpressionError()
                }
            }
        }

        viewModel.resultExpressionItems.observe(this) { items ->
            resultExpressionAdapter.updateItems(items)
        }
    }

    private fun initRecyclerAdapter() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this@CalculatorActivity)
            adapter = resultExpressionAdapter
        }
    }

    private fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
