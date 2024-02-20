package com.green.habits.home.repository

import com.shu.data.dto.Habit

interface HabitsMemoryCache {

    fun saveHabit(day: Int, habits: List<Habit>)

    fun getHabits(day: Int): List<Habit>

    fun clearCache()
}