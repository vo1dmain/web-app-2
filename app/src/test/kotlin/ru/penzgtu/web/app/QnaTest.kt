package ru.penzgtu.web.app

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import org.junit.Test
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton
import ru.penzgtu.web.app.data.repos.QnaRepo
import ru.penzgtu.web.app.plugins.configureSerialization
import ru.penzgtu.web.app.plugins.configureStatusPages
import ru.penzgtu.web.app.repos.QnaRepoTest
import ru.penzgtu.web.app.routing.qnaRouting
import ru.penzgtu.web.entities.qna.post.PostModel
import ru.penzgtu.web.entities.qna.post.PostView
import ru.penzgtu.web.entities.qna.question.QuestionModel
import kotlin.test.assertEquals

class QnaTest {
    @Test
    fun testPosts() = testApplication {
        application {
            qnaTest()
        }

        val client = jsonClient()

        client.get("/qna/posts").apply {
            println(bodyAsText())
            val list = body<List<PostView>>()
            assertEquals(0, list[0].id)
        }

        client.get("/qna/posts?page=-1").apply {
            assertEquals(HttpStatusCode.BadRequest, call.response.status)
        }
    }

    @Test
    fun testPost() = testApplication {
        application {
            qnaTest()
        }

        val client = jsonClient()

        client.get("/qna/posts/0").apply {
            println(bodyAsText())
            val item = body<PostModel>()
            assertEquals(0, item.id)
        }

        client.get("/qna/posts/a").apply {
            assertEquals(HttpStatusCode.BadRequest, status)
        }
    }

    @Test
    fun testQuestions() = testApplication {
        application {
            qnaTest()
        }

        val client = jsonClient()

        client.get("/qna/questions").apply {
            println(bodyAsText())
            val list = body<List<QuestionModel>>()
            assertEquals(0, list[0].id)
        }

        client.get("/qna/questions?page=-1").apply {
            assertEquals(HttpStatusCode.BadRequest, call.response.status)
        }
    }


    private fun Application.qnaTest() {
        configureSerialization()
        configureStatusPages()

        di {
            bind<QnaRepo> { singleton { QnaRepoTest() } }
        }

        routing {
            qnaRouting()
        }
    }
}