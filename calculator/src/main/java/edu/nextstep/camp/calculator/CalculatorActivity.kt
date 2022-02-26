package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.dodobest.data.AppDatabase
import com.github.dodobest.data.DataHandler
import com.github.dodobest.data.ResultRecord
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels { CalculatorViewModelFactory(this) }
    private lateinit var dataHandler: DataHandler
    private lateinit var resultAdapter: ResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataHandler = DataHandler(AppDatabase.getInstance(this))
        resultAdapter = ResultAdapter(dataHandler.getAllMemoryRecord())

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = resultAdapter

        viewModel.showErrorMessage.observe(this) {
            if (it.consumed) return@observe
            Toast.makeText(this, "완성되지 않은 수식입니다", Toast.LENGTH_LONG).show()
            it.consume()
        }

        viewModel.result.observe(this) {
            if (it.consumed) return@observe
            val statement = viewModel.statement.value!!
            val result = it.peek()
            dataHandler.insertRecord(statement, "= $result")
            resultAdapter.addResult(ResultRecord(statement, "= $result"))
            it.consume()
        }
    }
}