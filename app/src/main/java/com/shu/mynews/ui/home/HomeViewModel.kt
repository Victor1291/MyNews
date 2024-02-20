package com.shu.mynews.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import com.green.habits.home.repository.HabitsMemoryCache
import com.shu.data.database.HabitsDao
import com.shu.data.dto.Habit
import com.shu.mynews.R
import com.shu.mynews.ui.habits.MainHabitModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel
constructor(
    private val habitsMemoryCache: HabitsMemoryCache,
    private val habitsDao: HabitsDao,
) : ViewModel() {


    private val _todayHabits = MutableStateFlow<List<Habit>>(emptyList())
    val todayHabits: Flow<List<MainHabitModel>> = _todayHabits.asStateFlow().map {
        //Заглушка
        beginState()
    }

    init {
        Log.d("homeviemodel", "init viewmodel")
    }

    /*todo здесь вычитается один день тк апи считает дни начиная с воскресенья. нужно поставить
    галочку в настройках на выбор точки отсчета дней недели
    */
    fun getDayOfWeek(): Int {
        val c = Calendar.getInstance(Locale("ru", "RU"))
        c.firstDayOfWeek = Calendar.MONDAY
        c.timeInMillis = System.currentTimeMillis()
        return c.get(Calendar.DAY_OF_WEEK) - 1
    }

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Default + viewModelJob)

    fun getHabits(day: Int) {
        uiScope.launch {
            _todayHabits.value = habitsMemoryCache.getHabits(day).ifEmpty {
                habitsDao.getHabits()
            }.also {
                habitsMemoryCache.saveHabit(day, it)
            }
        }
    }

  /*  fun openCreateHabitScreen(context: Context) {
        createHabitMediator.openCreateHabitScreen(context)
    }*/

    private fun beginState(): List<MainHabitModel> {
        return  listOf(
            MainHabitModel(
                1L, "Eat some vegetables", R.drawable.emoji_leafy, 3, 4, false
            ),
            MainHabitModel(
                1L, "Go for a walk with Doggie", R.drawable.emoji_dog_face, 3, 4, false
            ),
            MainHabitModel(
                1L, "Fell in love ", R.drawable.emoji_hearts_eyes, 3, 4, false
            )
        )
    }
}

class HomeViewModelFactory @Inject constructor(
    private val habitsMemoryCache: HabitsMemoryCache,
    private val habitsDao: HabitsDao,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(habitsMemoryCache, habitsDao) as T
    }
}