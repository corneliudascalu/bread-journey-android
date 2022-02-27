package com.corneliudascalu.bakerjourney.log

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.corneliudascalu.bakerjourney.R
import com.corneliudascalu.bakerjourney.UiResult
import com.corneliudascalu.bakerjourney.databinding.FragmentListBinding
import com.corneliudascalu.bakerjourney.navigateToCalculator
import com.corneliudascalu.bakerjourney.navigateToLogEntry
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class LogEntriesFragment : Fragment(R.layout.fragment_list), KoinComponent {
    private val binding by lazy { FragmentListBinding.bind(requireView()) }
    private val viewModel: LogEntriesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = LogAdapter {
            parentFragmentManager.navigateToLogEntry(it)
        }
        binding.refreshLayout.setOnRefreshListener { viewModel.refresh() }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // TODO Investigate if something fancy could be done here
        binding.recyclerView.itemAnimator = null
        binding.newRecipe.setOnClickListener { parentFragmentManager.navigateToCalculator() }

        viewModel.refresh()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.ui.collect { state ->
                when (state) {
                    is UiResult.Failure -> {
                        binding.refreshLayout.isRefreshing = false
                        TODO()
                    }
                    is UiResult.Loading -> {
                        binding.refreshLayout.isRefreshing = true
                    }
                    is UiResult.Success -> {
                        binding.refreshLayout.isRefreshing = false
                        adapter.submitList(state.value.entries)
                    }
                }
            }
        }
    }
}