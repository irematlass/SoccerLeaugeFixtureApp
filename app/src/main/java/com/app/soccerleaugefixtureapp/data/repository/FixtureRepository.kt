package com.app.soccerleaugefixtureapp.data.repository

import com.app.soccerleaugefixtureapp.data.db.LeaugeFixtureDao
import com.app.soccerleaugefixtureapp.data.model.Fixture
import com.app.soccerleaugefixtureapp.data.service.SoccerTeamsAPI
import javax.inject.Inject

class FixtureRepository @Inject constructor(
    private val FixtureDao: LeaugeFixtureDao,
    private val SoccerTeamsApi: SoccerTeamsAPI
) {
    suspend fun getTeamsList() = SoccerTeamsApi.getTeamsList()
    suspend fun addFixtures(fixtures: List<Fixture>) = FixtureDao.insertAll(fixtures)
    suspend fun getFixtures() = FixtureDao.getAllFixture()
    suspend fun deleteAllFixtures() = FixtureDao.deleteAllFixture()

}