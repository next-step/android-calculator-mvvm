package camp.nextstep.edu.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CalculatorViewModel by viewModels()
    private val historyAdapter: HistoryAdapter by lazy {
        HistoryAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        observeException()
        observeHistory()
    }

    private fun init() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = historyAdapter
    }

    private fun observeException() {
        viewModel.exceptionMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeHistory() {
        viewModel.histories.observe(this) {
            historyAdapter.submitList(it)
        }
    }
}
