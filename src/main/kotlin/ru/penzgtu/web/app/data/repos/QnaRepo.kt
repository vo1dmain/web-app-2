package ru.penzgtu.web.app.data.repos

import ru.penzgtu.web.app.data.dao.AnswerDao
import ru.penzgtu.web.app.data.dao.PostDao
import ru.penzgtu.web.app.data.dao.QuestionDao
import ru.penzgtu.web.app.data.entities.qna.Question
import ru.penzgtu.web.app.data.entities.qna.post.PostDto
import ru.penzgtu.web.app.data.entities.qna.post.PostView

abstract class QnaRepo : ListRepo {
    protected abstract val questionDao: QuestionDao
    protected abstract val answerDao: AnswerDao
    protected abstract val postDao: PostDao

    suspend fun posts(page: Int?): List<PostView> {
        return postDao.list(offset(page), limit)
    }

    suspend fun post(id: Int): PostDto? {
        val post = postDao.read(id) ?: return null
        val question = questionDao.read(post.questionId) ?: return null
        val answer = answerDao.read(post.answerId) ?: return null
        return PostDto(post.id!!, question, answer)
    }

    suspend fun newQuestion(question: Question) {
        questionDao.create(question)
    }
}