package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.CalculationResult

class CalculationHistoryAdapter :
    ListAdapter<CalculationResult, CalculationHistoryViewHolder>(
        DiffUtilItemCallbackForCalculationResult()
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalculationHistoryViewHolder =
        CalculationHistoryViewHolder(
            ItemResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CalculationHistoryViewHolder, position: Int) =
        holder.onBind(getItem(position))
}
