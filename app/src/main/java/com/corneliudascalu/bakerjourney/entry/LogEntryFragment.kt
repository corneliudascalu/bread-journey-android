package com.corneliudascalu.bakerjourney.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.corneliudascalu.bakerjourney.R
import com.corneliudascalu.bakerjourney.Step
import com.corneliudascalu.bakerjourney.databinding.FragmentLogEntryBinding
import com.corneliudascalu.bakerjourney.databinding.ListItemLogEntryBinding
import com.corneliudascalu.bakerjourney.log.LogRepository
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

            Glide.with(binding.root)
                .load(logEntry.photoUrl)
                .centerCrop()
                .into(binding.headerPhoto)

            val logEntryAdapter = LogEntryAdapter()
            binding.recyclerView.adapter = logEntryAdapter
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            logEntryAdapter.submitList(
                listOf(
                    LogEntryDetail.Text(logEntry.name),
                    LogEntryDetail.Text(logEntry.description)
                ).plus(
                    logEntry.steps.map { step ->
                        when (step) {
                            is Step.CheckableStep -> LogEntryDetail.Text(step.step.comment ?: "Empty")
                            is Step.IngredientStep -> LogEntryDetail.AddIngredient(step.ingredient, step.comment ?: "Empty")
                            is Step.TextStep -> LogEntryDetail.Text(step.comment)
                        }
                    }
                )
            )
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            parentFragmentManager.popBackStack()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
