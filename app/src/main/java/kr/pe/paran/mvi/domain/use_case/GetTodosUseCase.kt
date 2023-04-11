package kr.pe.paran.mvi.domain.use_case

import kr.pe.paran.mvi.domain.model.TodoData
import kr.pe.paran.mvi.data.repository.Repository
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(
    private val todoRepository: Repository
) {
    suspend fun invoke(): List<TodoData> {
        return todoRepository.getTodos()
    }
}