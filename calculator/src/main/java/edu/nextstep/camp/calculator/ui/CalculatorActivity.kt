package edu.nextstep.camp.calculator.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.calculator.R
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.calculator.viewmodel.CalculatorViewModel
import edu.nextstep.camp.data.Injector

class CalculatorActivity : AppCompatActivity() {
    private val memoryAdapter = MemoryAdapter()

    private val viewModel: CalculatorViewModel by viewModelsFactory {
        CalculatorViewModel(
            repository = Injector.provideMemoryRepository(this)
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

        viewModel.memoriesEvent.observe(this) { memories ->
            if (memories != null) memoryAdapter.refreshMemories(memories)
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
