package camp.nextstep.edu.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.calculator.databinding.ItemResultBinding
import camp.nextstep.edu.calculator.domain.Memory

class RecordAdapter : RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {

    private var memoryList = emptyList<Memory>()

    class RecordViewHolder(private var itemResultBinding: ItemResultBinding): RecyclerView.ViewHolder(itemResultBinding.root) {
        fun bind(memory : Memory) {
           itemResultBinding.memory = memory
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecordViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return RecordViewHolder(binding)
    }

    override fun getItemCount() = memoryList.size

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
       holder.bind(memoryList[position])
    }

    fun setMemoryList(memoryList: List<Memory>) {
        this.memoryList = memoryList
    }

    fun setData(newMemoryList: List<Memory>) {
        val diffUtil = RecodeDiffUtill(memoryList, newMemoryList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        memoryList = newMemoryList
        diffResult.dispatchUpdatesTo(this)
    }

    class RecodeDiffUtill(
        private val oldList: List<Memory>,
        private val newList: List<Memory>,
    ): DiffUtil.Callback(){
        override fun getOldListSize()= oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].index == newList[newItemPosition].index
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }
}
