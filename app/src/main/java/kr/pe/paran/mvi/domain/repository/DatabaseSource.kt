package kr.pe.paran.mvi.domain.repository

import kr.pe.paran.mvi.domain.model.TodoData

interface DatabaseSource {
    suspend fun getTodoList(): List<TodoData>
    suspend fun saveTodo(todoData: TodoData)
}