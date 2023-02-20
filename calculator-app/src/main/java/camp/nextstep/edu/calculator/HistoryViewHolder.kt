package camp.nextstep.edu.calculator

import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.calculator.databinding.ItemResultBinding
import com.example.domain.models.History

class HistoryViewHolder(private val binding: ItemResultBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(history: History) {
        binding.history = history
    }
}
