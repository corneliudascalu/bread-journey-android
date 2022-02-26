package com.corneliudascalu.bakerjourney.log

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.corneliudascalu.bakerjourney.R
import com.corneliudascalu.bakerjourney.databinding.FragmentListBinding
import com.corneliudascalu.bakerjourney.navigateToCalculator
import com.corneliudascalu.bakerjourney.navigateToLogEntry
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LogEntriesFragment : Fragment(R.layout.fragment_list), KoinComponent {
    private val repository: ShortLogRepository by inject()
    private val binding by lazy { FragmentListBinding.bind(requireView()) }
    private val adapter = LogAdapter {
        parentFragmentManager.navigateToLogEntry(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // TODO Investigate if something fancy could be done here
        binding.recyclerView.itemAnimator = null

        adapter.submitList(repository.getAll())

        binding.newRecipe.setOnClickListener { parentFragmentManager.navigateToCalculator() }
    }
}