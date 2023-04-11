package kr.pe.paran.mvi.data.database

import androidx.room.RoomDatabase
import kr.pe.paran.mvi.data.database.dao.TodoDao
import kr.pe.paran.mvi.domain.model.TodoData

@androidx.room.Database(entities = [TodoData::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}