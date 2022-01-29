package com.corneliudascalu.bakerjourney

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

const val KEY_DOUGH_CHOICES = "doughChoices"

sealed class Ingredient(val quantity: Int) {
    class Flour(quantity: Int) : Ingredient(quantity)
    class Water(quantity: Int) : Ingredient(quantity)
    class Salt(quantity: Int) : Ingredient(quantity)
    class Starter(val flour: Flour, val water: Water) : Ingredient(flour.quantity + water.quantity)
}

data class Step(val ingredient: Ingredient, val comment: String)
data class CheckableStep(val step: Step, val checked: Boolean = false)
data class Recipe(val steps: List<Step>, val description: String)

@Parcelize
data class DoughChoices(
    val flour: Int,
    val waterPercent: Float,
    val fermentedFlourPercent: Float
) : Parcelable