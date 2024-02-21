package com.shu.mynews.ui.repository

import com.shu.data.dto.Habit
import com.shu.domain.habits.model.IHabit
import com.shu.mynews.ui.repository.HabitsMemoryCache
import java.util.*
import javax.inject.Inject

class HabitsMemoryCacheImpl
@Inject constructor() : HabitsMemoryCache {

    private val storage = Collections.synchronizedMap(mutableMapOf<Int, List<IHabit>>())

    override fun saveHabit(day: Int, habits: List<IHabit>) {
        storage[day] = habits
    }

    override fun getHabits(day: Int): List<IHabit> {
        return storage[day] ?: emptyList()
    }

    override fun clearCache() {
        storage.clear()
    }
}