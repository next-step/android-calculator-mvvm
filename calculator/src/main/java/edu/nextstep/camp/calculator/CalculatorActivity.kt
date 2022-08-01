package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity(){

    private val binding by lazy { ActivityCalculatorBinding.inflate(layoutInflater) }

    private val calculatorViewModel by viewModels<CalculatorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        observeData()
    }

    private fun initViews() {
        binding.calculatorViewModel = calculatorViewModel
        binding.lifecycleOwner = this
    }

    private fun observeData() {
        calculatorViewModel.failInfo.observe(this) {
            if (!it) return@observe
            Toast.makeText(applicationContext, "완성되지 않은 수식입니다.", Toast.LENGTH_SHORT).show()
        }
    }

}
