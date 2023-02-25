package camp.nextstep.edu.calculator.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import camp.nextstep.edu.calculator.R
import camp.nextstep.edu.calculator.domain.model.CalculatorResult


class ResultListAdapter : ListAdapter<CalculatorResult, ResultViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) =
        ResultViewHolder.newInstance(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_result,
                parent,
                false
            )
        )

    override fun onBindViewHolder(
        holder: ResultViewHolder,
        position: Int
    ) {
        holder.bind(currentList[position])
    }

    companion object {

        private val diffUtilCallback = object : DiffUtil.ItemCallback<CalculatorResult>() {
            override fun areItemsTheSame(
                oldItem: CalculatorResult,
                newItem: CalculatorResult,
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: CalculatorResult,
                newItem: CalculatorResult,
            ) = oldItem == newItem
        }
    }
}
