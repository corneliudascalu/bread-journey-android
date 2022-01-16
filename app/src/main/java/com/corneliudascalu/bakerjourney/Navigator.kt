package com.corneliudascalu.bakerjourney

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

fun FragmentManager.navigateToCalculator(fragmentManager: FragmentManager) {
    fragmentManager.commit {
        replace(R.id.container, CalculatorFragment())
    }
}

fun FragmentManager.navigateToRecipe(fragmentManager: FragmentManager, doughChoices: DoughChoices) {
    fragmentManager.commit {
        val recipeFragment = RecipeFragment()
        recipeFragment.arguments = bundleOf(KEY_DOUGH_CHOICES to doughChoices)
        replace(R.id.container, recipeFragment)
        addToBackStack(null)
    }
}