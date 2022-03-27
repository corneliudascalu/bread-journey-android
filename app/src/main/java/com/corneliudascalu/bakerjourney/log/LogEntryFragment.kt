package com.corneliudascalu.bakerjourney.log

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.corneliudascalu.bakerjourney.R
import com.corneliudascalu.bakerjourney.databinding.FragmentLogEntryBinding
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
            binding.toolbar.title = logEntry?.name
            binding.collapsingToolbar.title = logEntry?.description
            binding.toolbar.subtitle = logEntry?.description
            binding.name.text =
                logEntry?.name + logEntry?.name + logEntry?.name + logEntry?.name + logEntry?.name + logEntry?.name
            Glide.with(binding.root)
                .load(logEntry?.photoUrl)
                .centerCrop()
                .into(binding.headerPhoto)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            parentFragmentManager.popBackStack()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}