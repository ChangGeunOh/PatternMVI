package kr.pe.paran.mvi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kr.pe.paran.mvi.domain.model.TodoData

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoData: TodoData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoDataList: List<TodoData>)

    @Query("SELECT * FROM table_todo")
    suspend fun getTodoList(): List<TodoData>

}