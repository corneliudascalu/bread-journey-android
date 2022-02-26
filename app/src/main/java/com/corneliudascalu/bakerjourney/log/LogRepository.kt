package com.corneliudascalu.bakerjourney.log

import com.corneliudascalu.bakerjourney.Ingredient
import com.corneliudascalu.bakerjourney.Step
import java.util.*
import kotlin.random.Random

class LogRepository {
    private val list: List<LogEntry>

    init {
        val names = listOf(
            "White Bread", "Whole Wheat Bread",
            "Whole Wheat Seeded Bread",
            "Rye Bread",
            "Pan Loaf",
            "Cozonac",
            "Focaccia"
        )
        val descriptions = listOf(
            "Delicious with butter and honey",
            "Crusty and dark, with a heartwarming aroma",
            "Just perfect for an avocado toast",
            "Filled with seeds, nuts and goodness"
        )
        val textSteps = listOf(
            "Gather all the ingredients",
            "Leave dough in a warm place",
            "Put dough in the fridge for 12 hours",
            "Divide the dough"
        )
        list = mutableListOf()
        for (i in 1..20) {
            LogEntry(
                id = UUID.randomUUID().toString(),
                photoUrl = "https://picsum.photos/600?random=$i",
                name = names.random(),
                description = descriptions.random(),
                steps = listOf(
                    Step.TextStep(textSteps.random()),
                    Step.IngredientStep(Ingredient.Flour(Random.nextInt(1000, 1800)), "g Flour"),
                    Step.IngredientStep(Ingredient.Water(Random.nextInt(600, 1250)), "g Water"),
                    Step.TextStep(textSteps.random()),
                    Step.TextStep(textSteps.random())
                )
            ).also { list.add(it) }
        }
    }

    fun getAll(): List<LogEntry> {
        return list
    }

    operator fun get(key: String): LogEntry? {
        return list.find { it.id == key }
    }
}