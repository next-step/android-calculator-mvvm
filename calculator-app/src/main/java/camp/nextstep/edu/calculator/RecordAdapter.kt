package camp.nextstep.edu.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.calculator.databinding.ItemResultBinding
import camp.nextstep.edu.calculator.domain.RecordResource

class RecordAdapter : ListAdapter<RecordResource, RecordViewHolder>(recordDiffUtil) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
		return RecordViewHolder(ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
	}

	override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	companion object {
		val recordDiffUtil = object : DiffUtil.ItemCallback<RecordResource>() {
			override fun areItemsTheSame(oldItem: RecordResource, newItem: RecordResource): Boolean {
				return oldItem.id == newItem.id
			}

			override fun areContentsTheSame(oldItem: RecordResource, newItem: RecordResource): Boolean {
				return oldItem == newItem
			}
		}
	}
}

class RecordViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {

	fun bind(recorderResource: RecordResource) {
		binding.recordResource = recorderResource
	}
}
