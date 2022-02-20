package com.corneliudascalu.bakerjourney.recipe

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.corneliudascalu.bakerjourney.Step.CheckableStep

class StepsAdapter : ListAdapter<CheckableStep, StepViewHolder>(StepDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        return StepViewHolder(parent)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        holder.bind(getItem(position)) {
            val list = currentList.toMutableList()
            val checkedStep = list[position].copy(checked = it)
            list[position] = checkedStep
            submitList(list)
        }
    }
}

class StepDiffUtilCallback : DiffUtil.ItemCallback<CheckableStep>() {
    override fun areItemsTheSame(oldItem: CheckableStep, newItem: CheckableStep): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CheckableStep, newItem: CheckableStep): Boolean {
        return oldItem.checked == newItem.checked &&
                (oldItem.step.comment == newItem.step.comment) &&
                (oldItem.step.ingredient == newItem.step.ingredient)
    }
}