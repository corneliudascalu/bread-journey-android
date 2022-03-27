package com.corneliudascalu.bakerjourney.log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.LayoutInflaterCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.corneliudascalu.bakerjourney.R
import com.corneliudascalu.bakerjourney.Step
import com.corneliudascalu.bakerjourney.databinding.FragmentLogEntryBinding
import com.corneliudascalu.bakerjourney.databinding.ListItemLogEntryBinding
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LogEntryFragment : Fragment(R.layout.fragment_log_entry), KoinComponent {

    companion object {
        fun forLogEntryId(id: String): Bundle {
            return bundleOf("id" to id)
        }
    }

    private val logRepository: LogRepository by inject()
    private val logEntryId: String?
        get() = arguments?.getString("id")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLogEntryBinding.bind(view)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        logEntryId?.also {
            val logEntry = logRepository[it]
            requireNotNull(logEntry)
            binding.toolbar.title = logEntry.name
            binding.collapsingToolbar.title = logEntry.description
            binding.toolbar.subtitle = logEntry.description
            binding.name.text = logEntry.name
            binding.description.text = logEntry.description
            // TODO Implement a proper adapter and recycler view
            for (step in logEntry.steps) {
                val childView = when (step) {
                    is Step.CheckableStep -> {
                        val checkBox = CheckBox(binding.root.context)
                        checkBox.text = step.step.comment
                        checkBox
                    }
                    is Step.IngredientStep -> {
                        val stepBinding = ListItemLogEntryBinding.inflate(LayoutInflater.from(binding.root.context))
                        stepBinding.mtrlListItemText.text = step.ingredient.toString()
                        stepBinding.mtrlListItemSecondaryText.text = step.comment
                        stepBinding.root
                    }
                    is Step.TextStep -> {
                        val textView = TextView(binding.root.context)
                        textView.text = step.comment
                        textView
                    }
                }
                binding.stepsLayout.addView(childView)
            }

            Glide.with(binding.root)
                .load(logEntry.photoUrl)
                .centerCrop()
                .into(binding.headerPhoto)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            parentFragmentManager.popBackStack()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}