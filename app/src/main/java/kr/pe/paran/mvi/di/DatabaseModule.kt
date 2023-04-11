package kr.pe.paran.mvi.di

import android.content.Context
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import kr.pe.paran.mvi.common.utils.Constants
import kr.pe.paran.mvi.data.database.Database
import kr.pe.paran.mvi.data.repository.DatabaseSourceImpl
import kr.pe.paran.mvi.domain.repository.DatabaseSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): Database {
        val database = Room.databaseBuilder(
            context = context,
            klass = Database::class.java,
            name = "db_todo"
        )
            .fallbackToDestructiveMigration()
            .addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Constants.initDatabaseList.forEach {
                        db.insert("table_todo", OnConflictStrategy.REPLACE, it.toContentValues())
                    }
                }
            })
            .build()

        return database
    }


    @Provides
    @Singleton
    fun provideDatabaseSource(
        database: Database,
    ): DatabaseSource {
        return DatabaseSourceImpl(database)
    }

}