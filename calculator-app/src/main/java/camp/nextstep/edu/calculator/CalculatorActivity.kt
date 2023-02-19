package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.calculator.data.db.RecordDatabase
import camp.nextstep.edu.calculator.data.repository.RecordRepositoryImpl
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding
import camp.nextstep.edu.calculator.domain.RecordRepository

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    private lateinit var viewModel: CalculatorViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculatorBinding.inflate(layoutInflater)

        val database = RecordDatabase.getInstance(this)

        val repository: RecordRepository = RecordRepositoryImpl(database)
        val factory = CalculatorViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, factory)[CalculatorViewModel::class.java]

        setContentView(binding.root)
        with(binding) {
            vm = viewModel
            lifecycleOwner = this@CalculatorActivity
        }
        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.showToastMessage.observe(this) {
            showIncompleteExpressionError()
        }
    }

    private fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
