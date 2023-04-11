package kr.pe.paran.mvi.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kr.pe.paran.mvi.domain.model.TodoData
import kr.pe.paran.mvi.domain.use_case.UseCases
import kr.pe.paran.mvi.presentation.main.model.MainTodoItem
import kr.pe.paran.mvi.presentation.main.model.MainState
import kr.pe.paran.mvi.presentation.main.model.MainUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private val events = Channel<MainUiEvent>()

    val state = events
        .receiveAsFlow()
        .runningFold(MainState(), ::reduceState)
        .stateIn(viewModelScope, SharingStarted.Eagerly, MainState())

    init {
        viewModelScope.launch {
            val data = useCases.getTodosUseCase.invoke().map {
                MainTodoItem(
                    id = it.id,
                    isChecked = it.isChecked,
                    text = it.text
                )
            }
            events.send(MainUiEvent.ShowData(data))
        }
    }

    fun onUiEvent(event: MainUiEvent) {
        viewModelScope.launch {
            events.send(event)
        }
    }

    private suspend fun reduceState(oldState: MainState, event: MainUiEvent): MainState {
        return when (event) {
            is MainUiEvent.OnChangeDialogState ->
                oldState.copy(isShowAddDialog = event.show)
            is MainUiEvent.ShowData ->
                oldState.copy(isLoading = false, data = event.items)
            is MainUiEvent.DismissDialog ->
                oldState.copy(isShowAddDialog = false)
            is MainUiEvent.AddNewItem -> {
                val todoItem = TodoData(isChecked = false, text = event.text)
                useCases.saveTodoUseCase(todoData = todoItem)
                val list = useCases.getTodosUseCase.invoke().map {
                    it.toMainTodoItem()
                }

                oldState.copy(
                    data = list,
                    isShowAddDialog = false
                )
            }
            is MainUiEvent.OnItemCheckedChanged -> {
                val newList = oldState.data.toMutableList()
                newList[event.index] =
                    newList[event.index].copy(isChecked = event.isChecked)
                oldState.copy(data = newList)
            }
        }
    }

}