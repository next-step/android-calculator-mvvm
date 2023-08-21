package camp.nextstep.edu.calculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.calculator.domain.Memory

class Adpter(): RecyclerView.Adapter<Adpter.ViewHolder>() {

    private lateinit var memoryList: List<Memory>

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvExpression: TextView
        val tvResult: TextView

        init {
            tvExpression = view.findViewById(R.id.tv_expression)
            tvResult = view.findViewById(R.id.tv_result)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Adpter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_result, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = memoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvExpression.text = memoryList[position].expression
        holder.tvResult.text = memoryList[position].result
    }

    fun setMemoryList(memoryList: List<Memory>) {
        this.memoryList = memoryList
    }
}
