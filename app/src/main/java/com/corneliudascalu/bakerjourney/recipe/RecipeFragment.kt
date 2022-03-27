package com.corneliudascalu.bakerjourney.recipe

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.View
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
            // TODO This bunch of calculations should be extracted and made generic
            val doughChoices = it as DoughChoices

            val flourAmount = doughChoices.flour

            val starterPercentage = doughChoices.ratios.first { it.ingredient is Ingredient.Starter }
            val starter = starterPercentage.ingredient as Ingredient.Starter
            val flourInStarter = starter.flour(starterPercentage.fromTotal(flourAmount))
            val waterInStarter = starter.water(starterPercentage.fromTotal(flourAmount))

            // TODO Handle multiple types of flour
            val flourPercentage = doughChoices.ratios.first { it.ingredient is Ingredient.Flour }
            val waterPercentage = doughChoices.ratios.first { it.ingredient is Ingredient.Water }
            val saltPercentage = doughChoices.ratios.first { it.ingredient is Ingredient.Salt }

            val waterQty = waterPercentage.fromTotal(flourAmount) - waterInStarter
            val saltQty = saltPercentage.fromTotal(flourAmount)
            val starterQty = starterPercentage.fromTotal(flourAmount)
            val flourQty = flourPercentage.fromTotal(flourAmount) - flourInStarter
            val builder = SpannableStringBuilder()

            // TODO Get the steps from a repository
            val steps = mutableListOf<Step.CheckableStep>()
            steps.add(Step.CheckableStep(Step.IngredientStep(Ingredient.Water, waterQty, "${waterQty}g water")))
            steps.add(Step.CheckableStep(Step.IngredientStep(Ingredient.Salt, saltQty, "${saltQty}g salt")))
            steps.add(Step.CheckableStep(Step.IngredientStep(Ingredient.Starter(), starterQty, "${starterQty}g starter")))
            steps.add(Step.CheckableStep(Step.IngredientStep(Ingredient.Flour("white"), flourQty, "${flourQty}g flour")))

            val stepsAdapter = StepsAdapter()
            binding.stepList.adapter = stepsAdapter
            binding.stepList.itemAnimator = null
            binding.stepList.layoutManager = LinearLayoutManager(requireContext())
            stepsAdapter.submitList(steps)

            addStep(builder, R.string.water_step, waterQty.toString())
            addStep(builder, R.string.salt_step, saltQty.toString())
            addStep(builder, R.string.starter_step, starterQty.toString())
            addStep(builder, R.string.flour_step, flourQty.toString())

        }

        binding.startLogEntryButton.setOnClickListener {
            parentFragmentManager.navigateToLog()
        }
    }

    // TODO Create a full fledged data class to hold recipe step information
    private fun addStep(builder: SpannableStringBuilder, @StringRes text: Int, value: String) {
        builder.append(getString(text).replace("{value}", value)).append("\n")
        val indexOf = builder.indexOf(value)
        builder.setSpan(StyleSpan(Typeface.BOLD), indexOf, indexOf + value.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
    }
}