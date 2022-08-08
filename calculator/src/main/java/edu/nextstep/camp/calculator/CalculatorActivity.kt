package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.data.historyStorage.HistoryDatabase
import edu.nextstep.camp.calculator.data.historyStorage.HistoryManager
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.calculator.event.Event

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    private val viewModel: CalculatorViewModel by viewModels {
        val db = HistoryDatabase.getInstance(this)

        ViewModelFactory(
            HistoryManager(db)
        )
    }

    private val historyAdapter by lazy { ExpressionHistoryListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.recyclerView.adapter = historyAdapter

        observeData()
    }

    private fun observeData() {
        viewModel.showEvent.observe(this) {
            handleEvent(it)
        }

        viewModel.history.observe(this) {
            historyAdapter.submitList(it)
        }
    }

    private fun handleEvent(event: Event) {
        when (event) {
            is Event.Error -> Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
        }
    }
}
