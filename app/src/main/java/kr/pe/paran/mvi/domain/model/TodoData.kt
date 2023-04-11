package kr.pe.paran.mvi.domain.model

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.pe.paran.mvi.presentation.main.model.MainTodoItem
import java.util.UUID


@Entity(tableName = "table_todo")
data class TodoData(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val isChecked: Boolean = false,
    val text: String = "",
) {
    fun toContentValues(): ContentValues {
        return ContentValues().apply {
            put("id", id)
            put("isChecked", isChecked)
            put("text", text)
        }
    }

    fun toMainTodoItem(): MainTodoItem {
        return MainTodoItem(id = id, isChecked = isChecked, text = text)
    }
}
