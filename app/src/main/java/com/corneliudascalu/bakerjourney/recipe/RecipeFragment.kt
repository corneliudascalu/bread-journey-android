package com.corneliudascalu.bakerjourney.recipe

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.corneliudascalu.bakerjourney.*
import com.corneliudascalu.bakerjourney.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment(R.layout.fragment_recipe) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRecipeBinding.bind(view)
        arguments?.get(KEY_DOUGH_CHOICES)?.also {
            val doughChoices = it as DoughChoices
            val fermentedFlour = doughChoices.flour * doughChoices.fermentedFlourPercent
            val starter: Ingredient.Starter = Ingredient.Starter(
                Ingredient.Flour(fermentedFlour.toInt()),
                Ingredient.Water(fermentedFlour.toInt())
            )
            val flour = Ingredient.Flour(doughChoices.flour - starter.flour.quantity)
            val water =
                Ingredient.Water((doughChoices.flour * doughChoices.waterPercent - starter.water.quantity).toInt())
            val salt = Ingredient.Salt((doughChoices.flour * 0.02).toInt())
            val waterQty = water.quantity.toString()
            val saltQty = salt.quantity.toString()
            val starterQty = starter.quantity.toString()
            val flourQty = flour.quantity.toString()
            val builder = SpannableStringBuilder()

            // TODO Get the steps from a repository
            val steps = mutableListOf<CheckableStep>()
            steps.add(CheckableStep(Step(water, "${water.quantity}g water")))
            steps.add(CheckableStep(Step(salt, "${salt.quantity}g salt")))
            steps.add(CheckableStep(Step(starter, "${starter.quantity}g starter")))
            steps.add(CheckableStep(Step(flour, "${flour.quantity}g flour")))

            val stepsAdapter = StepsAdapter()
            binding.stepList.adapter = stepsAdapter
            binding.stepList.itemAnimator = null
            binding.stepList.layoutManager = LinearLayoutManager(requireContext())
            stepsAdapter.submitList(steps)

            addStep(builder, R.string.water_step, waterQty)
            addStep(builder, R.string.salt_step, saltQty)
            addStep(builder, R.string.starter_step, starterQty)
            addStep(builder, R.string.flour_step, flourQty)

        }

        binding.startLogEntryButton.setOnClickListener { Toast.makeText(requireContext(), "TODO Start a new log entry", Toast.LENGTH_SHORT).show() }
    }

    // TODO Create a full fledged data class to hold recipe step information
    private fun addStep(builder: SpannableStringBuilder, @StringRes text: Int, value: String) {
        builder.append(getString(text).replace("{value}", value)).append("\n")
        val indexOf = builder.indexOf(value)
        builder.setSpan(StyleSpan(Typeface.BOLD), indexOf, indexOf + value.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
    }
}