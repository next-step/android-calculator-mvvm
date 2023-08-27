package camp.nextstep.edu.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding
import com.example.calculator.data.CalculatorDatabase

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private lateinit var recordAdapter: RecordAdapter
    private lateinit var viewModel: CalculatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initBinding()
        initViewModelObserve()
        initRecyclerView()

        setContentView(binding.root)
    }

    private fun initViewModel() {
        val calculatorDao = CalculatorDatabase.getInstance(this)!!.CalculatorDao()
        val viewModelFactory = CalculatorViewModelFactory(calculatorDao = calculatorDao)
        viewModel = ViewModelProvider(this, viewModelFactory)[CalculatorViewModel::class.java]
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        recordAdapter = RecordAdapter()
        binding.recyclerView.adapter = recordAdapter
    }

    private fun initBinding() {
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModelObserve() {
        viewModel.toastEvent.observe(this) { strRes ->
            val msg = getString(strRes)
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        viewModel.memoryList.observe(this) {
            recordAdapter.setData(it)
        }

        viewModel.showHistory.observe(this) { isSwhoHistory ->
            if (isSwhoHistory) {
                binding.recyclerView.visibility = View.VISIBLE
                binding.textView.visibility = View.INVISIBLE
            } else {
                binding.recyclerView.visibility = View.GONE
                binding.textView.visibility = View.VISIBLE
            }
        }
    }
}
