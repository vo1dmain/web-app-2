package ru.penzgtu.web.app.data.timetables.meta

import kotlinx.serialization.Serializable

@Serializable
data class EducationForm(
    val id: Int,
    val title: String
)