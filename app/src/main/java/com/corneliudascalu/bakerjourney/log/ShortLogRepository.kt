package com.corneliudascalu.bakerjourney.log

class ShortLogRepository {

    private val list: List<ShortLogEntry>

    init {
        list = LogRepository().getAll().map {
            ShortLogEntry(
                id = it.id,
                iconUrl = it.photoUrl,
                name = it.name,
                description = it.description
            )
        }
    }

    fun getAll(): List<ShortLogEntry> {
        // TODO Load entries from db
        return list
    }
}