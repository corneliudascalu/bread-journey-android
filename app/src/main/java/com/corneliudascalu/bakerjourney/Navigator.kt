package com.corneliudascalu.bakerjourney

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.corneliudascalu.bakerjourney.log.LogFragment
import com.corneliudascalu.bakerjourney.recipe.RecipeFragment

fun FragmentManager.navigateToCalculator() {
    commit {
        replace(R.id.container, CalculatorFragment())
    }
}

fun FragmentManager.navigateToRecipe(doughChoices: DoughChoices) {
    commit {
        val recipeFragment = RecipeFragment()
        recipeFragment.arguments = bundleOf(KEY_DOUGH_CHOICES to doughChoices)
        replace(R.id.container, recipeFragment)
        addToBackStack(null)
    }
}

fun FragmentManager.navigateToLog() {
    commit {
        replace(R.id.container, LogFragment())
        addToBackStack(null)
    }
}