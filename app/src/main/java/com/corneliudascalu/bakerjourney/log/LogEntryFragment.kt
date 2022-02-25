package com.corneliudascalu.bakerjourney.log

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.corneliudascalu.bakerjourney.R
import com.corneliudascalu.bakerjourney.databinding.FragmentLogEntryBinding

class LogEntryFragment : Fragment(R.layout.fragment_log_entry) {

    companion object {
        fun forLogEntryId(id: String): Bundle {
            return bundleOf("id" to id)
        }
    }

    private val logEntryId: String?
        get() = arguments?.getString("id")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLogEntryBinding.bind(view)
        logEntryId?.also {
            binding.name.text = LogRepository()[it]?.name
        }
    }
}