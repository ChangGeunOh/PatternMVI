package kr.pe.paran.mvi.data.repository

import kr.pe.paran.mvi.data.database.Database
import kr.pe.paran.mvi.domain.model.TodoData
import kr.pe.paran.mvi.domain.repository.DatabaseSource

class DatabaseSourceImpl(
    private val database: Database
): DatabaseSource {
    override suspend fun getTodoList(): List<TodoData> {
        return database.todoDao().getTodoList()
    }

    override suspend fun saveTodo(todoData: TodoData) {
        database.todoDao().insert(todoData = todoData)
    }

}