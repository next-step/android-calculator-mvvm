package camp.nextstep.edu.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    private lateinit var viewDataBinding: ActivityCounterBinding

    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = ActivityCounterBinding.inflate(layoutInflater).apply {
            this.lifecycleOwner = this@CounterActivity
            this.viewmodel = viewModel
        }
        setContentView(viewDataBinding.root)
        observeUnderZeroToastMessage()
    }

    private fun observeUnderZeroToastMessage() {
        viewModel.countUnderZero.observe(this) {
            it.consume()?.let {
                Toast.makeText(this, getString(R.string.under_zero_message), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
