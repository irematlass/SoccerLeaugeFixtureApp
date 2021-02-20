package com.app.soccerleaugefixtureapp.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey




@Entity
data class Fixture (
    @PrimaryKey
    val Id: String,
    val homeTeam:String,
    val awayTeam: String,
    val matchWeek: Int
)