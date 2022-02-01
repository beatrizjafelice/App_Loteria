package com.jafan.loteria

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jafan.loteria.databinding.NumberItemBinding

class NumberAdapter: ListAdapter<String, NumberAdapter.NumberViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NumberAdapter.NumberViewHolder {
        return NumberViewHolder(NumberItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NumberAdapter.NumberViewHolder, position: Int) {
        val number = getItem(position)
        holder.bind(number)
    }

    class NumberViewHolder(private var binding: NumberItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(num: String) {
            binding.number.text = num
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }


}