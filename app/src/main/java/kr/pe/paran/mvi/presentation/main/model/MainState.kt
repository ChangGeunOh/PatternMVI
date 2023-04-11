package kr.pe.paran.mvi.presentation.main.model

import javax.annotation.concurrent.Immutable

@Immutable
data class MainState(
    val isLoading: Boolean = true,
    val data: List<MainTodoItem> = emptyList(),
    val isShowAddDialog: Boolean = false,
)