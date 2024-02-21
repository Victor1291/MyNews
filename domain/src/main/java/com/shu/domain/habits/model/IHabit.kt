package com.shu.domain.habits.model

interface IHabit {
    val id: Long
    val title: String
    val description: String?
    val icon: String
    val days: List<Int>
    val repeats: Int
}