package ru.vo1d.web.orm.entities.qna

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption.CASCADE
import ru.vo1d.web.entities.qna.post.PostDto
import ru.vo1d.web.entities.qna.post.PostView
import ru.vo1d.web.orm.entities.HasModel
import ru.vo1d.web.orm.entities.HasView

object Posts : IntIdTable() {
    val questionId = reference("questionId", Questions, CASCADE, CASCADE)
    val answerId = reference("answerId", Answers, CASCADE, CASCADE)
}

class Post(id: EntityID<Int>) : IntEntity(id), HasModel<PostDto>, HasView<PostView> {
    companion object : IntEntityClass<Post>(Posts)

    val question by Question referencedOn Posts.questionId
    val answer by Answer referencedOn Posts.answerId

    override fun toModel() = PostDto(id.value, question.toModel(), answer.toModel())

    override fun toView() = PostView(
        id.value,
        question.id.value,
        question.dateTime,
        question.theme,
        answer.dateTime
    )
}