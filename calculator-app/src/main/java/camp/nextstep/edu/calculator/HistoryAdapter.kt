package camp.nextstep.edu.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.calculator.databinding.ItemResultBinding
import camp.nextstep.edu.calculator.domain.repo.History

class HistoryAdapter(private var histories: List<History>) : RecyclerView.Adapter<HistoryAdapter.RecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        val history = histories[position]
        holder.bind(history)
    }

    override fun getItemCount(): Int {
        return histories.size
    }

    inner class RecyclerViewViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) = with(binding) {
            data = history
        }
    }
}
