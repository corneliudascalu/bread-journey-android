package com.corneliudascalu.bakerjourney

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.corneliudascalu.bakerjourney.log.LogEntriesFragment
import com.corneliudascalu.bakerjourney.log.LogEntryFragment
import com.corneliudascalu.bakerjourney.recipe.RecipeFragment

fun FragmentManager.navigateToCalculator() {
    commit {
        replace(R.id.container, CalculatorFragment())
        addToBackStack(null)
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
        replace(R.id.container, LogEntriesFragment())
        addToBackStack(null)
    }
}

fun FragmentManager.navigateToLogEntry(id: String) {
    commit {
        replace(R.id.container, LogEntryFragment().apply { arguments = LogEntryFragment.forLogEntryId(id) })
        addToBackStack(null)
    }
}