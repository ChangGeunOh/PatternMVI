package kr.pe.paran.mvi.presentation.main.model

import java.util.UUID

data class MainTodoItem(
    val id: String = UUID.randomUUID().toString(),
    val isChecked: Boolean,
    val text: String,
)
