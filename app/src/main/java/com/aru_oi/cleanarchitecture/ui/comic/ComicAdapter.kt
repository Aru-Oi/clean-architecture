package com.aru_oi.cleanarchitecture.ui.comic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aru_oi.cleanarchitecture.databinding.ComicItemBinding

class ComicAdapter :
    RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {

    var onItemClickListener: (ComicViewModel) -> Unit = { _ -> }
    private var viewmodels: List<ComicViewModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ComicItemBinding.inflate(inflater, parent, false)
        return ComicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val vm = viewmodels[position]
        holder.bind(vm)
        holder.binding.root.setOnClickListener {
            onItemClickListener(vm)
        }
    }

    override fun getItemCount(): Int = viewmodels.size

    fun update(items: List<ComicViewModel>) {
        this.viewmodels = items
        notifyDataSetChanged()
    }

    class ComicViewHolder(val binding: ComicItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ComicViewModel) {
            binding.apply {
                vm = item
                executePendingBindings()
            }
        }
    }
}
