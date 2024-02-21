package com.shu.data.habits

import com.shu.data.database.HabitsDao
import com.shu.domain.habits.HabitsRepository
import com.shu.domain.habits.model.IHabit
import javax.inject.Inject

class HabitsRepositoryImpl @Inject constructor(
    private val habitsDao: HabitsDao
): HabitsRepository {
    override suspend fun getHabits(): List<IHabit> {
       return habitsDao.getHabits()
    }
}