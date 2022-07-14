package ru.vo1d.web.orm.dao.daybook.group

import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.update
import ru.vo1d.web.data.dao.TableTypeDao
import ru.vo1d.web.entities.daybook.group.type.TableTypeModel
import ru.vo1d.web.orm.entities.daybook.group.TableType
import ru.vo1d.web.orm.entities.daybook.group.TableTypes

class TableTypeDaoXp : TableTypeDao {
    override suspend fun create(item: TableTypeModel) = TableTypes.insertAndGetId {
        it[id] = item.id
        it[title] = item.title
    }.value

    override suspend fun read(id: String) = TableType.findById(id)?.toModel()

    override suspend fun update(item: TableTypeModel) = TableTypes.update({ TableTypes.id eq item.id }) {
        it[title] = item.title
    }

    override suspend fun delete(id: String) = TableTypes.deleteWhere { TableTypes.id eq id }

    override suspend fun all() = TableType.all().map(TableType::toModel)
}