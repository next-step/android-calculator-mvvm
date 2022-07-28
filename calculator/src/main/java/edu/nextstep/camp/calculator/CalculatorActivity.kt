package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import edu.nextstep.camp.calculator.CalculatorViewModel.ViewEvent
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCalculatorBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<CalculatorViewModel>()
    private val adapter by lazy { ExpressionHistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initRecyclerView()

        viewModel.viewEvent.observe(this) { handleViewEvent(it) }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = this@CalculatorActivity.adapter
            itemAnimator = null
            layoutManager = LinearLayoutManager(this@CalculatorActivity)
        }

        viewModel.expressionHistories.observe(this) { adapter.data = it }
    }

    private fun handleViewEvent(viewEvent: ViewEvent) {
        when (viewEvent) {
            ViewEvent.IncompleteExpressionError ->
                Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
        }
    }
}
