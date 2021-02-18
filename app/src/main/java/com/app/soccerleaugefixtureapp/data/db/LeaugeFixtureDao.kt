package com.app.soccerleaugefixtureapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.soccerleaugefixtureapp.data.model.Fixture

@Dao
interface LeaugeFixtureDao {

    @Insert
    suspend fun insertAll(fixtures: List<Fixture>): List<Long>

    @Query("SELECT * FROM fixture")
    suspend fun getAllFixture(): List<Fixture>
}