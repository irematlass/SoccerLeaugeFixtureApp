package com.app.soccerleaugefixtureapp.utils

import com.app.soccerleaugefixtureapp.data.model.Fixture
import com.app.soccerleaugefixtureapp.data.model.Team

class Utils {
    companion object{
            fun drawFixture(myTeamList:ArrayList<Team>):List<String>{
               val fixtureList= mutableListOf<String>()
                if (myTeamList.size%2!=0) myTeamList.add(Team(0,"Bye"))

                var countOfWeek=myTeamList.size
                var countOfDay=myTeamList.size/2
                var tempTeams= mutableListOf<Team>()
                tempTeams.addAll(myTeamList.drop(countOfDay).take(countOfDay))
                tempTeams.addAll(myTeamList.drop(1).take(countOfDay-1).toList().reversed())
                var tempSize=tempTeams.size

                for (day in 1 downTo countOfWeek ){
                    var teamIdx=day%tempSize
                    fixtureList.add(tempTeams[teamIdx].name + myTeamList[0])
                    for (idx in 1 downTo countOfDay ) {
                        var firstTeam = (day + idx) % tempSize
                        var secondTeam = (day + tempSize - idx) % tempSize
                        fixtureList.add(tempTeams[firstTeam].name+tempTeams[secondTeam].name)
                    }
                    }

            return fixtureList
        }
    }
}