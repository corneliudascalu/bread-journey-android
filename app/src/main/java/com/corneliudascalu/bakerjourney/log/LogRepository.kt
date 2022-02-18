package com.corneliudascalu.bakerjourney.log

class LogRepository {
    fun getLogEntries(): List<LogEntry> {
        // TODO Load entries from db
        val list = mutableListOf<LogEntry>()
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
            LogEntry(
                iconUrl = "https://picsum.photos/200?random=$i",
                name = names.random(),
                description = descriptions.random()
            ).also { list.add(it) }
        }
        return list
    }
}