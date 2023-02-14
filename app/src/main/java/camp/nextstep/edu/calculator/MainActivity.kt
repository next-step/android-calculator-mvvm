package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.example.domain.*

class MainActivity : AppCompatActivity(), CalculateContract.View {
    private lateinit var binding: ActivityMainBinding
    override lateinit var presenter: CalculateContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = CalculatePresenter(this)

        binding.button0.setOnClickListener {
            presenter.addTerm(Operand(0))
        }

        binding.button1.setOnClickListener {
            presenter.addTerm(Operand(1))
        }

        binding.button2.setOnClickListener {
            presenter.addTerm(Operand(2))
        }

        binding.button3.setOnClickListener {
            presenter.addTerm(Operand(3))
        }

        binding.button4.setOnClickListener {
            presenter.addTerm(Operand(4))
        }

        binding.button5.setOnClickListener {
            presenter.addTerm(Operand(5))
        }

        binding.button6.setOnClickListener {
            presenter.addTerm(Operand(6))
        }

        binding.button7.setOnClickListener {
            presenter.addTerm(Operand(7))
        }

        binding.button8.setOnClickListener {
            presenter.addTerm(Operand(8))
        }

        binding.button9.setOnClickListener {
            presenter.addTerm(Operand(9))
        }

        binding.buttonDivide.setOnClickListener {
            presenter.addTerm(Operator.DIVIDE)
        }

        binding.buttonMinus.setOnClickListener {
            presenter.addTerm(Operator.SUBTRACT)
        }

        binding.buttonMultiply.setOnClickListener {
            presenter.addTerm(Operator.MULTIPLY)
        }

        binding.buttonPlus.setOnClickListener {
            presenter.addTerm(Operator.ADD)
        }

        binding.buttonDelete.setOnClickListener {
            presenter.removeTerm()
        }

        binding.buttonEquals.setOnClickListener {
            presenter.calculate()
        }
    }

    override fun showStatement(statement: Statement) {
        binding.textView.text = statement.termsToString()
    }

    override fun showResult(result: Int) {
        binding.textView.text = result.toString()
    }

    override fun showCalculateError(error: Throwable) {
        Toast.makeText(applicationContext, error.message.toString(), Toast.LENGTH_LONG).show()
    }
}
