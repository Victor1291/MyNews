package com.shu.domain.habits

import com.shu.domain.news.NewsListRepository

class GetAllHabitsUseCase (
    private val habitsRepository: HabitsRepository
) {

    suspend fun execute () = habitsRepository.getHabits()

}