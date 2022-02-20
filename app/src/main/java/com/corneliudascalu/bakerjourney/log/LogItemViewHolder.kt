package com.corneliudascalu.bakerjourney.log

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.corneliudascalu.bakerjourney.R
import com.corneliudascalu.bakerjourney.databinding.ListItemLogEntryBinding

// TODO Research a material-ish layout
class LogItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_log_entry, parent, false)) {
    val binding = ListItemLogEntryBinding.bind(itemView)

    fun bind(item: ShortLogEntry) {
        binding.mtrlListItemText.text = item.name
        binding.mtrlListItemSecondaryText.text = item.description
        Glide.with(binding.root)
            .load(item.iconUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_grain_24)
            .into(binding.mtrlListItemIcon)
        binding.root.setOnClickListener {
            Toast.makeText(binding.root.context, item.description, Toast.LENGTH_SHORT).show()
        }
    }
}