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

sealed class Step {
    data class TextStep(val comment: String) : Step()
    data class IngredientStep(val ingredient: Ingredient, val comment: String?) : Step()
    data class CheckableStep(val step: IngredientStep, val checked: Boolean = false) : Step()
}

@Parcelize
data class DoughChoices(
    val flour: Int,
    val waterPercent: Float,
    val fermentedFlourPercent: Float
) : Parcelable