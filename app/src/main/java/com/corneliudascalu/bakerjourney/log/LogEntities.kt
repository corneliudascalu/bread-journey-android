package com.corneliudascalu.bakerjourney.log

import com.corneliudascalu.bakerjourney.Step
import java.util.*

data class ShortLogEntry(
    val id: String = UUID.randomUUID().toString(),
    val iconUrl: String,
    val name: String,
    val description: String
)

data class LogEntry(
    val id: String = UUID.randomUUID().toString(),
    val type: String = "bread", // TODO Define types
    val photoUrl: String? = null, // TODO Maybe a photo gallery?
    val name: String,
    val description: String = "",
    val steps: List<Step> = emptyList()
)