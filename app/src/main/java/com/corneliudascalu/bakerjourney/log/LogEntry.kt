package com.corneliudascalu.bakerjourney.log

import java.util.*

data class LogEntry(
    val id: String = UUID.randomUUID().toString(),
    val iconUrl: String,
    val name: String,
    val description: String
)