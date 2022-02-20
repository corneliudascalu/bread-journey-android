package com.corneliudascalu.bakerjourney.log

class ShortLogRepository {
    fun getLogEntries(): List<ShortLogEntry> {
        // TODO Load entries from db
        val list = mutableListOf<ShortLogEntry>()
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
        for (i in 1..20) {
            ShortLogEntry(
                iconUrl = "https://picsum.photos/200?random=$i",
                name = names.random(),
                description = descriptions.random()
            ).also { list.add(it) }
        }
        return list
    }
}