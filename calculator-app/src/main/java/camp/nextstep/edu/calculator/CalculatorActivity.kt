package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CalculatorActivity : AppCompatActivity() {

    private lateinit var viewDataBinding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels { CalculatorViewModelFactory(context = this) }
    private val historyAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = ActivityCalculatorBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@CalculatorActivity
            viewmodel = viewModel
        }
        setContentView(viewDataBinding.root)

        initAdapter()
        observerHistories()
        observerIncompleteExpressionError()
    }

    private fun initAdapter() {
        viewDataBinding.recyclerView.adapter = historyAdapter
    }

    private fun observerHistories() {
        viewModel.histories.observe(this) {
            historyAdapter.submitList(it)
        }
    }

    private fun observerIncompleteExpressionError() {
        viewModel.inCompleteExpressionError.observe(this) {
            it.consume()?.let { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
