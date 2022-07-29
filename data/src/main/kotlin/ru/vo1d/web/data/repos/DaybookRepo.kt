package ru.vo1d.web.data.repos

import kotlinx.datetime.*
import ru.vo1d.web.data.filters.daybook.DatedSessionFilters
import ru.vo1d.web.data.filters.daybook.RegularSessionFilters
import ru.vo1d.web.data.filters.daybook.TimetableFilters
import ru.vo1d.web.entities.daybook.Meta
import ru.vo1d.web.entities.daybook.group.GroupDto
import ru.vo1d.web.entities.daybook.group.GroupModel
import ru.vo1d.web.entities.daybook.group.degree.GradDegreeModel
import ru.vo1d.web.entities.daybook.group.form.EduFormModel
import ru.vo1d.web.entities.daybook.group.level.GradLevelModel
import ru.vo1d.web.entities.daybook.group.type.TableTypeModel
import ru.vo1d.web.entities.daybook.timetable.TimetableDto
import ru.vo1d.web.entities.daybook.timetable.TimetableModel
import ru.vo1d.web.entities.daybook.timetable.session.*
import ru.vo1d.web.entities.daybook.timetable.time.StartTimeModel
import ru.vo1d.web.entities.daybook.timetable.week.WeekModel

interface DaybookRepo : ListRepo {
    suspend fun meta(): Meta

    suspend fun levels(): List<GradLevelModel>

    suspend fun degrees(): List<GradDegreeModel>

    suspend fun forms(): List<EduFormModel>

    suspend fun tableTypes(): List<TableTypeModel>

    suspend fun groups(): List<GroupDto>

    suspend fun addGroups(vararg groups: GroupModel): Int

    suspend fun startTimes(): List<StartTimeModel>

    suspend fun sessionTypes(): List<SessionTypeModel>

    suspend fun weekNumber(): WeekModel {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val thisYearS1 = LocalDate(today.year, Month.SEPTEMBER, 1)

        if (today == thisYearS1) return WeekModel.FIRST

        val lastYearS1 = when(today < thisYearS1) {
            true -> LocalDate(today.year - 1, Month.SEPTEMBER, 1)
            else -> thisYearS1
        }

        val weeksBetween = lastYearS1.daysUntil(today) / 7

        return when(weeksBetween % 2) {
            0 -> WeekModel.FIRST
            else -> WeekModel.SECOND
        }
    }


    suspend fun timetables(page: Int?, filtersBuilder: TimetableFilters.Builder.() -> Unit): List<TimetableModel>

    suspend fun timetable(id: Int): TimetableDto<SessionModel>?

    suspend fun timetableBase(id: Int): TimetableModel?

    suspend fun addTimetable(item: TimetableModel): Int?

    suspend fun timetableExists(code: String, type: String): Boolean

    suspend fun regularSessions(
        page: Int?,
        filtersBuilder: RegularSessionFilters.Builder.() -> Unit
    ): List<RegularSessionModel>

    suspend fun datedSessions(
        page: Int?,
        filtersBuilder: DatedSessionFilters.Builder.() -> Unit
    ): List<DatedSessionModel>


    suspend fun addRegularSession(item: RegularSessionModel): Int?

    suspend fun addDatedSession(item: DatedSessionModel): Int?


    suspend fun addRegularJunction(item: TimetableSessionModel): Unit?

    suspend fun addDatedJunction(item: TimetableSessionModel): Unit?
}