package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.nextstep.camp.calculator.data.History
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class MainHistoryAdapter :
    ListAdapter<History, MainHistoryViewHolder>(HistoryDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHistoryViewHolder {
        return MainHistoryViewHolder(
            ItemResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
