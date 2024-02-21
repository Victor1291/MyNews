package com.shu.mynews.ui.repository


import com.shu.domain.habits.model.IHabit

interface HabitsMemoryCache {

    fun saveHabit(day: Int, habits: List<IHabit>)

    fun getHabits(day: Int): List<IHabit>

    fun clearCache()
}