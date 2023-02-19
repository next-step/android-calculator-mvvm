package camp.nextstep.edu.calculator

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val calculatorViewModel: CalculatorViewModel by viewModels {
        ViewModelFactory(this, provideExecutorService())
    }

    private val calculatorResultAdapter: CalculatorResultAdapter by lazy { CalculatorResultAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater).apply {
            setContentView(root)
            lifecycleOwner = this@CalculatorActivity
            viewModel = calculatorViewModel
            recyclerView.adapter = calculatorResultAdapter
        }

        addObserve()
    }

    private fun addObserve() {
        calculatorViewModel.calculatorErrorMessage.observe(this) { errorMessageStringResourceId ->
            Toast.makeText(this, getString(errorMessageStringResourceId), Toast.LENGTH_SHORT).show()
        }

        calculatorViewModel.calculatorResultList.observe(this) {
            calculatorResultAdapter.submitList(it?.toMutableList())
        }
    }

    private fun provideExecutorService(): ExecutorService {
        val threadCount = Runtime.getRuntime().availableProcessors() * 2
        return Executors.newFixedThreadPool(threadCount)
    }
}
