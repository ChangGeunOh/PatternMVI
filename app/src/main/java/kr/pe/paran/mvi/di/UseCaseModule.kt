package kr.pe.paran.mvi.di

import kr.pe.paran.mvi.data.repository.Repository
import kr.pe.paran.mvi.domain.use_case.GetTodosUseCase
import kr.pe.paran.mvi.domain.use_case.SaveTodoUseCase
import kr.pe.paran.mvi.domain.use_case.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            getTodosUseCase = GetTodosUseCase(repository),
            saveTodoUseCase = SaveTodoUseCase(repository),
        )
    }
}


