package kr.pe.paran.mvi.presentation.main.model

import javax.annotation.concurrent.Immutable

@Immutable
sealed class MainUiEvent {
    data class ShowData(val items: List<MainTodoItem>) : MainUiEvent()
    data class OnChangeDialogState(val show: Boolean) : MainUiEvent()
    data class AddNewItem(val text: String) : MainUiEvent()
    data class OnItemCheckedChanged(val index: Int, val isChecked: Boolean) : MainUiEvent()
    object DismissDialog : MainUiEvent()
}