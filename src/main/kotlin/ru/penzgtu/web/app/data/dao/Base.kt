package ru.penzgtu.web.app.data.dao

import ru.penzgtu.web.app.data.util.FilterParams

sealed interface CrudDao<I, PK> {
    suspend fun create(item: I): PK
    suspend fun read(id: PK): I?
    suspend fun update(item: I)
    suspend fun delete(id: PK)
}

sealed interface ListDao<I> {
    suspend fun list(offset: Int, limit: Int): List<I>
}

sealed interface FilterDao<I> {
    suspend fun filter(params: FilterParams, offset: Int, limit: Int): List<I>
}


