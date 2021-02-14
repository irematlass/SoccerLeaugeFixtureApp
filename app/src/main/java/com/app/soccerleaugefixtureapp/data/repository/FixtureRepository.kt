package com.app.soccerleaugefixtureapp.data.repository

import com.app.soccerleaugefixtureapp.data.db.LeaugeFixtureDao
import com.app.soccerleaugefixtureapp.data.service.SoccerTeamsAPI
import javax.inject.Inject

class FixtureRepository @Inject constructor(
    private val FixtureDao:LeaugeFixtureDao,
    private val SoccerTeamsApi:SoccerTeamsAPI
){
    suspend fun getTeamsList()=SoccerTeamsApi.getTeamsList()
}