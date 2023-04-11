package kr.pe.paran.mvi.common.utils

import kr.pe.paran.mvi.domain.model.TodoData

object Constants {
    val initDatabaseList = listOf(
        TodoData(
            text = "Make a breakfast",
            isChecked = false
        ),
        TodoData(
            text = "Clean the room",
            isChecked = false
        ),
        TodoData(
            text = "Create the MVI sample",
            isChecked = true
        ),
        TodoData(
            text = "Upload it to medium",
            isChecked = true
        ),
    )

}