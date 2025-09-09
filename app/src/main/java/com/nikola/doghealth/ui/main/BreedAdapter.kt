package com.nikola.doghealth.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikola.doghealth.data.model.Breed
import com.nikola.doghealth.databinding.ItemBreedBinding

class BreedAdapter(
    private var items: List<Breed>,
    private val onClick: (Breed) -> Unit
) : RecyclerView.Adapter<BreedAdapter.VH>() {

    inner class VH(val b: ItemBreedBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val b = ItemBreedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(b)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.b.tvName.text = item.name
        holder.b.tvGroup.text = item.breed_group ?: "—"
        holder.b.root.setOnClickListener { onClick(item) }
    }

    override fun getItemCount() = items.size

    fun submit(newItems: List<Breed>) {
        items = newItems
        notifyDataSetChanged()
    }
}