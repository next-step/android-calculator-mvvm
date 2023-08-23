package camp.nextstep.edu.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.calculator.databinding.ItemResultBinding
import camp.nextstep.edu.calculator.domain.Memory

class RecordAdapter : RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {

    private lateinit var memoryList: List<Memory>

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
}
