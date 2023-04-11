package kr.pe.paran.mvi.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.pe.paran.mvi.R
import kr.pe.paran.mvi.presentation.main.model.MainTodoItem
import kr.pe.paran.mvi.presentation.main.model.MainUiEvent

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController,
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar {
                Text(

                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                        .padding(start = 16.dp),
                    text = stringResource(id = R.string.main_screen_title),
                    color = Color.White,
                    fontSize = 18.sp,
                    style = TextStyle(textAlign = TextAlign.Center, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Start,
                )

            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onUiEvent(MainUiEvent.OnChangeDialogState(true))
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Button")
            }
        }
    ) {
        Column {
            when {
                state.isLoading -> ContentWithProgress()
                state.data.isNotEmpty() -> MainScreenContent(
                    state.data,
                    state.isShowAddDialog,
                    onUiEvent = viewModel::onUiEvent
                )
            }
        }
    }
}

/*

 */

@Composable
private fun MainScreenContent(
    todos: List<MainTodoItem>,
    isShowAddDialog: Boolean,
    onUiEvent: (MainUiEvent) -> Unit,
) {
    Box {
        LazyColumn(content = {
            itemsIndexed(todos) { index, item ->
                TodoListItem(item = item, index = index) { index1, isChecked ->
                    onUiEvent(MainUiEvent.OnItemCheckedChanged(index1, isChecked))
                }
            }
        })

        // onDialogDismissClick, onDialogOkClick
        if (isShowAddDialog) {
            AddNewItemDialog(
                onDialogDismissClick = {
                    onUiEvent(MainUiEvent.OnChangeDialogState(false))
                },
                onDialogOkClick = {
                    onUiEvent(MainUiEvent.AddNewItem(text = it))
                }
            )
        }
    }
}

@Composable
private fun TodoListItem(
    item: MainTodoItem,
    index: Int,
    onItemCheckedChanged: (Int, Boolean) -> Unit,
) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            colors = CheckboxDefaults.colors(Color.Blue),
            checked = item.isChecked,
            onCheckedChange = {
                onItemCheckedChanged(index, !item.isChecked)
            }
        )
        Text(
            text = item.text,
            modifier = Modifier.padding(start = 16.dp),
            style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp
            )
        )
    }
}

@Composable
private fun AddNewItemDialog(
    onDialogDismissClick: () -> Unit,
    onDialogOkClick: (String) -> Unit,
) {
    var text by remember { mutableStateOf("") }
    AlertDialog(onDismissRequest = { },
        text = {
            TextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Blue,
                    disabledIndicatorColor = Color.Blue,
                    unfocusedIndicatorColor = Color.Blue,
                    backgroundColor = Color.LightGray,
                )
            )
        },
        confirmButton = {
            Button(
                onClick = { onDialogOkClick(text) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
            ) {
                Text(text = "Ok", style = TextStyle(color = Color.White, fontSize = 12.sp))
            }
        }, dismissButton = {
            Button(
                onClick = onDialogDismissClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
            ) {
                Text(text = "Cancel", style = TextStyle(color = Color.White, fontSize = 12.sp))
            }
        }
    )
}

@Composable
private fun ContentWithProgress() {
    Surface(color = Color.LightGray) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

