package ru.vo1d.web.app.resources.daybook

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import ru.vo1d.web.app.extensions.failIfNegative
import ru.vo1d.web.app.resources.ListResource

@Serializable
@Resource("/timetables")
data class Timetables(
    override val page: Int? = null,
    val group: String? = null,
    val type: String? = null
) : ListResource {
    init {
        page?.failIfNegative()
    }

    @Serializable
    @Resource("/timetables/{id}")
    data class Id(val id: Int) {
        init {
            id.failIfNegative()
        }

        @Serializable
        @Resource("/sessions")
        data class Sessions(
            val parent: Id,
            override val page: Int? = null,
            val type: Int? = null,
            val day: Int? = null,
            val time: Int? = null,
            val weekOption: Int? = null
        ) : ListResource {
            init {
                page?.failIfNegative()
                type?.failIfNegative()
                day?.failIfNegative()
                time?.failIfNegative()
                weekOption?.failIfNegative()
            }
        }
    }
}
