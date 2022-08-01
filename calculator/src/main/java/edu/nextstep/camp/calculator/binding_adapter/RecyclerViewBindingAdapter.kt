package edu.nextstep.camp.calculator.binding_adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("submitList")
internal fun <T, VH: RecyclerView.ViewHolder> RecyclerView.submitList(itemList: List<T>?) {
    if (itemList == null) return
    (adapter as? ListAdapter<T, VH>)?.submitList(itemList)
}
