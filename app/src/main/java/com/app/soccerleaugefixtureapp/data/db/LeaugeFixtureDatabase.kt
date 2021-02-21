package com.app.soccerleaugefixtureapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.soccerleaugefixtureapp.data.model.Fixture

@Database(entities = arrayOf(Fixture::class), version = 1)
abstract class LeaugeFixtureDatabase : RoomDatabase() {
    abstract fun FixtureDao(): LeaugeFixtureDao

    companion object {
        private const val DATABASE_NAME = "Fixture"

        fun buildDatabase(context: Context): LeaugeFixtureDatabase {
            return Room.databaseBuilder(
                context,
                LeaugeFixtureDatabase::class.java,
                DATABASE_NAME
            ).build()
        }

    }
}