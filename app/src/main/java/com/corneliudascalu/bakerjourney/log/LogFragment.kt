package com.corneliudascalu.bakerjourney.log

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.corneliudascalu.bakerjourney.R
import com.corneliudascalu.bakerjourney.databinding.FragmentLogBinding
import java.lang.StringBuilder
import kotlin.random.Random

class LogFragment : Fragment(R.layout.fragment_log) {
    private val adapter = LogAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLogBinding.bind(view)
        binding.log.adapter = adapter
        binding.log.layoutManager = LinearLayoutManager(requireContext())

        // TODO Investigate if something fancy could be done here
        binding.log.itemAnimator = null

        adapter.submitList(ShortLogRepository().getLogEntries())
    }

    private fun randomString(length: Int): String {
        val chars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        val stringBuilder = StringBuilder()
        for (i in 0..length) {
            val index = Random.nextInt(chars.size)
            stringBuilder.append(chars[index])
        }
        return stringBuilder.toString()
    }
}