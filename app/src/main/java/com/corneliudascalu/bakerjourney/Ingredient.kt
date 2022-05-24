package com.corneliudascalu.bakerjourney

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

const val KEY_DOUGH_CHOICES = "doughChoices"

typealias Grams = Int

@JvmInline
@Parcelize
value class Percentage(val value: Int) : Parcelable {
    init {
        require(value > 0)
        require(value <= 100)
    }
}

@Parcelize
data class IngredientPercentage(val ingredient: Ingredient, val percentage: Percentage) : Parcelable {
    fun fromTotal(quantity: Grams): Grams {
        return quantity * percentage.value / 100
    }
}

sealed class Ingredient(val name: String) : Parcelable {
    @Parcelize
    class Flour(private val type: String) : Ingredient("$type flour")

    @Parcelize
    object Water : Ingredient("Water")

    @Parcelize
    object Salt : Ingredient("Salt")

    @Parcelize
    class Starter(
        private val flourPercentage: Percentage = Percentage(100),
        private val waterPercentage: Percentage = Percentage(100)
    ) : Ingredient("100% Starter") {
        fun flour(total: Grams): Grams = total * flourPercentage.value / 100
        fun water(total: Grams): Grams = total * waterPercentage.value / 100
    }
}

sealed class Step {
    data class TextStep(val comment: String) : Step()
    data class IngredientStep(val ingredient: Ingredient, val quantity: Grams, val comment: String?) : Step()
    data class CheckableStep(val step: IngredientStep, val checked: Boolean = false) : Step()
}

@Parcelize
data class DoughChoices(
    val flour: Grams,
    val waterPercent: Float,
    val fermentedFlourPercent: Float,
    val ratios: List<IngredientPercentage>
) : Parcelable