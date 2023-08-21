package camp.nextstep.edu.calculator

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding
import com.example.calculator.data.CalculatorDatabase

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private lateinit var adapter: Adpter
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseContext
        initBinding()
        initViewModelObserve()
        initRecyclerView()
        setContentView(binding.root)
        viewModel.setCalculatorDao(CalculatorDatabase.getInstance(this)!!)
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Adpter()
        binding.recyclerView.adapter = adapter
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
            adapter.setMemoryList(it)
            adapter.notifyDataSetChanged()
        }

        viewModel.showHistory.observe(this) { isSwhoHistory ->
            if(isSwhoHistory) {
                binding.recyclerView.visibility = View.VISIBLE
                binding.textView.visibility = View.INVISIBLE
            } else {
                binding.recyclerView.visibility = View.GONE
                binding.textView.visibility = View.VISIBLE
            }
        }
     }
}
