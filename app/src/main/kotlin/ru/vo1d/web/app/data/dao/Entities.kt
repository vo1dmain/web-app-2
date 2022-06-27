package ru.vo1d.web.app.data.dao

import ru.vo1d.web.app.data.filters.daybook.TimetableFilters
import ru.vo1d.web.app.data.filters.news.ArticleFilters
import ru.vo1d.web.app.data.filters.news.CategoryFilters
import ru.vo1d.web.entities.daybook.group.GroupModel
import ru.vo1d.web.entities.daybook.group.form.EducationFormModel
import ru.vo1d.web.entities.daybook.group.level.GraduationLevelModel
import ru.vo1d.web.entities.daybook.group.type.TableTypeModel
import ru.vo1d.web.entities.daybook.timetable.TimetableModel
import ru.vo1d.web.entities.daybook.timetable.TimetableView
import ru.vo1d.web.entities.daybook.timetable.period.TimePeriodModel
import ru.vo1d.web.entities.daybook.timetable.session.SessionTypeModel
import ru.vo1d.web.entities.daybook.timetable.week.WeekOptionModel
import ru.vo1d.web.entities.news.article.ArticleModel
import ru.vo1d.web.entities.news.article.ArticleView
import ru.vo1d.web.entities.news.category.CategoryModel
import ru.vo1d.web.entities.qna.answer.AnswerModel
import ru.vo1d.web.entities.qna.post.PostModel
import ru.vo1d.web.entities.qna.post.PostView
import ru.vo1d.web.entities.qna.question.QuestionModel

//Qna
interface QuestionDao : CrudDao<QuestionModel, Int>, ListDao<QuestionModel>
interface AnswerDao : CrudDao<AnswerModel, Int>, ListDao<AnswerModel>
interface PostDao : CrudDao<PostModel, Int>,
    ListDao<PostView>


//Timetables
interface TimetableDao : CrudDao<TimetableModel, Int>,
    ListDao<TimetableView>,
    FilterDao<TimetableView, TimetableFilters>

interface SessionTypeDao : CrudDao<SessionTypeModel, Int>,
    AllDao<SessionTypeModel>

interface TimePeriodDao : CrudDao<TimePeriodModel, Int>,
    AllDao<TimePeriodModel>

interface WeekOptionDao : CrudDao<WeekOptionModel, Int>,
    AllDao<WeekOptionModel>

//Meta
interface GraduationLevelDao : CrudDao<GraduationLevelModel, String>,
    AllDao<GraduationLevelModel>

interface EducationFormDao : CrudDao<EducationFormModel, String>,
    AllDao<EducationFormModel>

interface GroupDao : CrudDao<GroupModel, String>,
    AllDao<GroupModel>

interface TableTypeDao : CrudDao<TableTypeModel, String>,
    AllDao<TableTypeModel>


//News
interface ArticleDao : CrudDao<ArticleModel, Int>,
    ListDao<ArticleView>,
    FilterDao<ArticleView, ArticleFilters>

interface CategoryDao : CrudDao<CategoryModel, Int>,
    ListDao<CategoryModel>,
    FilterDao<CategoryModel, CategoryFilters>
