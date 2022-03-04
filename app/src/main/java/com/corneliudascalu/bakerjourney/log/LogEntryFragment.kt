package com.corneliudascalu.bakerjourney.log

import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
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
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        logEntryId?.also {
            binding.toolbar.title = logRepository[it]?.name
            binding.toolbar.subtitle = logRepository[it]?.description
            binding.name.text =
                logRepository[it]?.name + logRepository[it]?.name + logRepository[it]?.name + logRepository[it]?.name + logRepository[it]?.name + logRepository[it]?.name
        }
    }
}