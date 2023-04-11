package kr.pe.paran.mvi.domain.use_case

import kr.pe.paran.mvi.data.repository.Repository
import kr.pe.paran.mvi.domain.model.TodoData

class SaveTodoUseCase(
    val repository: Repository
) {
    suspend operator fun invoke(todoData: TodoData) {
        repository.saveTodo(todoData = todoData)
    }
}