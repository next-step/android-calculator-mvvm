package camp.nextstep.edu.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.calculator.databinding.ItemResultBinding
import camp.nextstep.edu.calculator.domain.model.History

class HistoryAdapter : ListAdapter<History, HistoryAdapter.ViewHolder>(HistoryDiffCallback) {

    init { setHasStableIds(true) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding: ItemResultBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_result,
            parent,
            false
        )
        return ViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_result

    class ViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: History) {
            binding.model = item
            binding.executePendingBindings()
        }
    }

    internal object HistoryDiffCallback : DiffUtil.ItemCallback<History>() {
        override fun areItemsTheSame(oldItem: History, newItem: History) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: History, newItem: History) =
            oldItem == newItem
    }
}
