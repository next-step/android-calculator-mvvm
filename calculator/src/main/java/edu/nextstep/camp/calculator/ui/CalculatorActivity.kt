package edu.nextstep.camp.calculator.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.calculator.viewmodel.CalculatorViewModel
import edu.nextstep.camp.calculator.ExpressionView
import edu.nextstep.camp.calculator.MemoryView
import edu.nextstep.camp.calculator.R
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.data.AppDataBase
import edu.nextstep.camp.data.Memory

class CalculatorActivity : AppCompatActivity() {

    private val memoryAdapter = MemoryAdapter()

    private val viewModel: CalculatorViewModel by viewModelsFactory {
        CalculatorViewModel(
            memoryDao = AppDataBase.getInstance(this@CalculatorActivity).memoryDao()
        )
    }

    private lateinit var binding: ActivityCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = memoryAdapter

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.errorEvent.observe(this) {
            Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
        }

        viewModel.viewTypeEvent.observe(this) {
            when (it) {
                is ExpressionView -> setExpressionView()
                is MemoryView -> setMemoryView(it.memories)
            }
        }
    }

    private fun setExpressionView() {
        binding.textView.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.INVISIBLE
        setButtonEnabled(true)
    }

    private fun setMemoryView(memories: List<Memory>?) {
        binding.textView.visibility = View.INVISIBLE
        binding.recyclerView.visibility = View.VISIBLE
        memories?.let { memoryAdapter.refreshMemories(it) }

        setButtonEnabled(false)
    }

    private fun setButtonEnabled(enabled: Boolean) {
        binding.run {
            button0.isEnabled = enabled
            button1.isEnabled = enabled
            button2.isEnabled = enabled
            button3.isEnabled = enabled
            button4.isEnabled = enabled
            button5.isEnabled = enabled
            button6.isEnabled = enabled
            button7.isEnabled = enabled
            button8.isEnabled = enabled
            button9.isEnabled = enabled
            buttonPlus.isEnabled = enabled
            buttonMinus.isEnabled = enabled
            buttonMultiply.isEnabled = enabled
            buttonDivide.isEnabled = enabled
            buttonDelete.isEnabled = enabled
            buttonEquals.isEnabled = enabled
        }
    }

    private inline fun <reified T : ViewModel> viewModelsFactory(crossinline viewModelInitialization: () -> T): Lazy<T> {
        return viewModels {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return viewModelInitialization.invoke() as T
                }
            }
        }
    }
}
