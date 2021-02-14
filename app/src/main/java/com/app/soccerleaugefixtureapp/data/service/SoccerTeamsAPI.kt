package com.app.soccerleaugefixtureapp.data.service

import com.app.soccerleaugefixtureapp.data.model.Team
import retrofit2.Response
import retrofit2.http.GET

interface SoccerTeamsAPI {
    @GET("teams")
    suspend fun getTeamsList(): Response<List<Team>>
}