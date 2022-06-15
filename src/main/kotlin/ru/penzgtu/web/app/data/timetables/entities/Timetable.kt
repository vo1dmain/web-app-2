package ru.penzgtu.web.app.data.timetables.entities

import kotlinx.serialization.Serializable

@Serializable
data class Timetable(
    val id: Int,
    val groupCode: String,
    val typeId: Int,
    val days: List<Day>,
    val sessions: List<Session>,
)
