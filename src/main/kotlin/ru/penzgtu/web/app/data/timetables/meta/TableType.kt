package ru.penzgtu.web.app.data.timetables.meta

import kotlinx.serialization.Serializable

@Serializable
data class TableType(
    val id: Int,
    val title: String
)