package com.corneliudascalu.bakerjourney.log

class ShortLogRepository(private val repository: LogRepository) {

    private val list: List<ShortLogEntry> = repository.getAll().map {
        ShortLogEntry(
            id = it.id,
            iconUrl = it.photoUrl,
            name = it.name,
            description = it.description
        )
    }

    fun getAll(): List<ShortLogEntry> {
        // TODO Load entries from db
        return list
    }
}