package com.corneliudascalu.bakerjourney

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.corneliudascalu.bakerjourney.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment(R.layout.fragment_calculator) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCalculatorBinding.bind(view)
        binding.waterSlider.addOnChangeListener { slider, value, fromUser ->
            binding.hydrationPercentage.text = "${value.toInt()}%"
        }
        binding.starterSlider.addOnChangeListener { slider, value, fromUser ->
            binding.starterPercentage.text = "${value.toInt()}%"
        }
        binding.submitButton.setOnClickListener {
            if (binding.flourQty.text.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Please enter the flour quantity", Toast.LENGTH_SHORT).show()
            } else {
                val breadPreferences = DoughChoices(
                    binding.flourQty.text.toString().toInt(),
                    (binding.waterSlider.value.toInt() / 100.0).toFloat(),
                    binding.starterSlider.value / 100
                )
                parentFragmentManager.navigateToRecipe(breadPreferences)
            }
        }

        // TODO Bottom nav
        binding.notebookButton.setOnClickListener { parentFragmentManager.navigateToLog() }
    }
}