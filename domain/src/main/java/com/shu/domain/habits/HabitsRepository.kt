package com.shu.domain.habits

import com.shu.domain.habits.model.IHabit

interface HabitsRepository {

    suspend fun getHabits(): List<IHabit>

}