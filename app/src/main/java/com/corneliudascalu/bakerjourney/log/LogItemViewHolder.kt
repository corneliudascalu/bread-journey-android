package com.corneliudascalu.bakerjourney.log

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.corneliudascalu.bakerjourney.R
import com.corneliudascalu.bakerjourney.databinding.ListItemLogEntryBinding

// TODO Research a material-ish layout
class LogItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_log_entry, parent, false)) {
    val binding = ListItemLogEntryBinding.bind(itemView)

    fun bind(item: LogEntry) {
        binding.description.text = item.description
    }
}