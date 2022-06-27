package ru.penzgtu.web.app.exposed.orm.qna

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import ru.penzgtu.web.app.exposed.orm.HasModel
import ru.penzgtu.web.entities.qna.question.QuestionModel

object Questions : IntIdTable() {
    val theme = varchar("theme", 64)
    val body = varchar("body", 1024)
    val acceptorId = integer("acceptorId")
    val email = varchar("email", 128).nullable()
    val dateTime = long("dateTime")
}

class Question(id: EntityID<Int>) : IntEntity(id), HasModel<QuestionModel> {
    companion object : IntEntityClass<Question>(Questions)

    val theme by Questions.theme
    val body by Questions.body
    val acceptorId by Questions.acceptorId
    val email by Questions.email
    val dateTime by Questions.dateTime

    override fun toModel() = QuestionModel(id.value, theme, body, acceptorId, email, dateTime)
}