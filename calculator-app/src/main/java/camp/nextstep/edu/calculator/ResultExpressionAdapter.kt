package camp.nextstep.edu.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import camp.nextstep.edu.calculator.databinding.ItemResultBinding
import camp.nextstep.edu.calculator.domain.data.ResultExpression

class ResultExpressionAdapter(
    items: List<ResultExpression> = emptyList(),
    private var adapterItems: List<ResultExpression> = items
) : RecyclerView.Adapter<ResultExpressionAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(ItemResultBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return adapterItems.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(adapterItems[position])
    }

    fun updateItems(items: List<ResultExpression>) {
        adapterItems = items
    }

    inner class ItemViewHolder(private val binding: ItemResultBinding) : ViewHolder(binding.root) {
        fun bind(item: ResultExpression) {
            binding.memory = item
            binding.executePendingBindings()
        }
    }
}
