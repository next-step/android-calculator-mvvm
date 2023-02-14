package camp.nextstep.edu.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import camp.nextstep.edu.counter.databinding.ActivityCounterBinding


class CounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCounterBinding

    private val viewModel: CounterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()

        observeViewModel()

        setBindingVariables()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView<ActivityCounterBinding>(
            this, R.layout.activity_counter
        ).apply {
            lifecycleOwner = this@CounterActivity
        }
    }

    private fun observeViewModel() {
        viewModel.warning.observe(this) {
            makeToast()
        }
    }

    private fun setBindingVariables() {
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }

    private fun makeToast() {
        Toast.makeText(
            applicationContext,
            "0 이하로 내릴 수 없습니다",
            Toast.LENGTH_SHORT
        ).show()
    }
}
