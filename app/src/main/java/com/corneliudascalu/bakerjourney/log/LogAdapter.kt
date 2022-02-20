package com.corneliudascalu.bakerjourney.log

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class LogAdapter : ListAdapter<ShortLogEntry, LogItemViewHolder>(LogEntryDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogItemViewHolder {
        return LogItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: LogItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class LogEntryDiffUtil : DiffUtil.ItemCallback<ShortLogEntry>() {
    override fun areItemsTheSame(oldItem: ShortLogEntry, newItem: ShortLogEntry): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ShortLogEntry, newItem: ShortLogEntry): Boolean {
        return oldItem.description == newItem.description
    }

}