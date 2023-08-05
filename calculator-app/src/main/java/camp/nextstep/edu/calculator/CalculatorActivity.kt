package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {

    private lateinit var viewDataBinding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = ActivityCalculatorBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@CalculatorActivity
            viewmodel = viewModel
        }
        setContentView(viewDataBinding.root)

        observerIncompleteExpressionError()
    }

    private fun observerIncompleteExpressionError() {
        viewModel.inCompleteExpressionError.observe(this) {
            it.consume()?.let {
                Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
