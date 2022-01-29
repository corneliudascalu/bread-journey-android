package com.corneliudascalu.bakerjourney.recipe

import android.text.Spannable
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.clearSpans
import androidx.core.text.toSpannable
import androidx.recyclerview.widget.RecyclerView
import com.corneliudascalu.bakerjourney.CheckableStep
import com.corneliudascalu.bakerjourney.R
import com.corneliudascalu.bakerjourney.databinding.ListItemRecipeStepBinding

class StepViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_recipe_step, parent, false)) {
    fun bind(step: CheckableStep, checkListener: (isChecked: Boolean) -> Unit) {
        val binding = ListItemRecipeStepBinding.bind(itemView)
        binding.stepCheckbox.setOnCheckedChangeListener(null)
        binding.stepCheckbox.isChecked = step.checked
        binding.stepCheckbox.setOnCheckedChangeListener { _, isChecked -> checkListener(isChecked) }
        val comment: Spannable = step.step.comment.toSpannable()
        if (step.checked) {
            val span = StrikethroughSpan()
            comment.setSpan(span, 0, comment.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        } else {
            comment.clearSpans()
        }
        binding.stepCheckbox.text = comment
    }
}