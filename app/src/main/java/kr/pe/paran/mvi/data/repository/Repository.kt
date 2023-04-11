package kr.pe.paran.mvi.data.repository

import kr.pe.paran.mvi.domain.model.TodoData
import kr.pe.paran.mvi.domain.repository.DatabaseSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val database: DatabaseSource,
) {

    suspend fun getTodos(): List<TodoData> {
        return database.getTodoList()
    }

    suspend fun saveTodo(todoData: TodoData) {
        database.saveTodo(todoData = todoData)
    }

}