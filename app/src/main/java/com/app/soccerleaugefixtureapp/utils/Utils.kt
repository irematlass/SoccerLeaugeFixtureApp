package com.app.soccerleaugefixtureapp.utils

import com.app.soccerleaugefixtureapp.data.model.Fixture
import com.app.soccerleaugefixtureapp.data.model.Team
import java.util.*
import kotlin.collections.ArrayList

class Utils {
    companion object {
        fun drawFixture(myTeamList: ArrayList<Team>): List<Fixture> {
            val fixtureList = mutableListOf<Fixture>()
            if (myTeamList.size % 2 != 0) myTeamList.add(Team(0, "-"))

            var countOfWeek = (myTeamList.size-1) * 2
            var countOfDay = myTeamList.size / 2
            var tempTeams = mutableListOf<Team>()
            tempTeams.addAll(myTeamList.drop(countOfDay).take(countOfDay))
            tempTeams.addAll(myTeamList.drop(1).take(countOfDay - 1).toList().reversed())
            var tempSize = tempTeams.size

            for (day in 1.. countOfWeek) {
                var teamIdx = day % tempSize
                fixtureList.add(
                    Fixture(
                        UUID.randomUUID().toString(),
                        tempTeams[teamIdx].name,
                        myTeamList[0].name,
                        day
                    )
                )
                for (idx in 1 until countOfDay) {
                    var firstTeam = (day + idx) % tempSize
                    var secondTeam = (day + tempSize - idx) % tempSize
                    fixtureList.add(
                        Fixture(
                            UUID.randomUUID().toString(),
                            tempTeams[firstTeam].name,
                            tempTeams[secondTeam].name,
                            day
                        )
                    )
                }
            }

            return fixtureList
        }
    }
}

