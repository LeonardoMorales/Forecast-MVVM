package com.leonardo.forecastmvvm.data.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leonardo.forecastmvvm.data.model.CurrentWeatherEntry

@Database(
    entities = [CurrentWeatherEntry::class],
    version = 1
)
abstract class ForecastDatabase (): RoomDatabase() {

    abstract fun currentWeatherDao(): CurrentWeatherDao   // Why the is an abstract function?

    companion object {
        @Volatile private var instance: ForecastDatabase? = null  // The property is notated with "Volatile" because we need that the instance can be accessible by any thread
        private val LOCK = Any()  // It helps to avoide that two thread does the same thing at the same time

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ForecastDatabase::class.java,
            "forecast_db"
        ).build()
    }

}