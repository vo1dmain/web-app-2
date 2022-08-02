package ru.vo1d.web.orm.entities.daybook.group

import org.jetbrains.exposed.dao.id.EntityID
import ru.vo1d.web.entities.daybook.group.form.EducationForm
import ru.vo1d.web.orm.entities.HasModel
import ru.vo1d.web.orm.utils.tables.StringEntity
import ru.vo1d.web.orm.utils.tables.StringEntityClass
import ru.vo1d.web.orm.utils.tables.StringIdTable

object EducationForms : StringIdTable(idColLength = 16) {
    val title = varchar("title", 32)
}

class EducationFormEntity(id: EntityID<String>) : StringEntity(id), HasModel<EducationForm> {
    companion object : StringEntityClass<EducationFormEntity>(EducationForms)

    val title by EducationForms.title

    override fun toModel() = EducationForm(id.value, title)
}