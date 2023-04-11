package kr.pe.paran.mvi.domain.use_case

data class UseCases (
    val getTodosUseCase: GetTodosUseCase,
    val saveTodoUseCase: SaveTodoUseCase,
)