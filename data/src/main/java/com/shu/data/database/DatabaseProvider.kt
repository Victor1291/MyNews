package com.shu.data.database

interface DatabaseProvider {

    fun provideDatabase(): HabitsDatabaseContract

    fun habitsDao(): HabitsDao
}

/*

По сути похож на Даггеровский компонент без анатаций.

У этого провайдера есть компонент. DatabaseComponent,
который лежит в Core Impl

Это интерфейс , контракт , имплиментирует интерфейсу компонента
провижн функции, цель которых показать нижестоящему компоненту каккие то сущности.


 */