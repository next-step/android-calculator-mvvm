package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding
    private val calculatorViewModel: CalculatorViewModel by viewModels { CalculatorViewModelFactory(this) }
    private val resultAdapter: ResultAdapter by lazy { ResultAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initObserver()
    }

    private fun initView() {
        with(binding) {
            viewModel = calculatorViewModel
            lifecycleOwner = this@CalculatorActivity
            recyclerView.layoutManager = LinearLayoutManager(this@CalculatorActivity)
            recyclerView.adapter = resultAdapter
        }
    }

    private fun initObserver() {
        with(calculatorViewModel) {
            resultList.observe(this@CalculatorActivity) { resultList ->
                resultAdapter.submitList(resultList)
            }
            showWarningMessageEvent.observe(this@CalculatorActivity) {
                showToast(it)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
