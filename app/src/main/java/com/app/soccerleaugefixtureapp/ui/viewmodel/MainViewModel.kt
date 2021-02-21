package com.app.soccerleaugefixtureapp.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.soccerleaugefixtureapp.data.model.Fixture
import com.app.soccerleaugefixtureapp.data.model.Team
import com.app.soccerleaugefixtureapp.data.repository.FixtureRepository
import com.app.soccerleaugefixtureapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(private val fixtureRepository: FixtureRepository) :
    ViewModel() {
    var teamList: MutableLiveData<Resource<List<Team>>> = MutableLiveData()
    var fixturesList: MutableLiveData<Resource<List<Long>>> = MutableLiveData()
    var getfixtureList: MutableLiveData<Resource<List<Fixture>>> = MutableLiveData()
    fun insertFixture(fixtureList: List<Fixture>) = viewModelScope.launch {
        fixtureRepository.deleteAllFixtures()
        fixturesList.postValue(Resource.Loading())
        try {
            val result = fixtureRepository.addFixtures(fixtureList)
            if (result.isNotEmpty())
                fixturesList.postValue(Resource.Success(result))
            else
                fixturesList.postValue(Resource.Error("An error occurred, please try again"))
        } catch (t: Throwable) {
            fixturesList.postValue(Resource.Error("An error occurred, please try again: ${t.message}"))
        }
    }

    fun getFixtureList(context: Context) = viewModelScope.launch {
        getfixtureList.postValue(Resource.Loading())
        try {
            val response = fixtureRepository.getFixtures()
            getfixtureList.postValue(Resource.Success(response))
        } catch (t: Throwable) {
            getfixtureList.postValue(
                Resource.Error("An error occurred, please try again", emptyList())
            )
        }
    }

    fun getTeamList(context: Context) = viewModelScope.launch {
        teamList.postValue(Resource.Loading())
        try {
            val response = fixtureRepository.getTeamsList()
            teamList.postValue(handleTeamListResponse(response))
        } catch (t: Throwable) {
            teamList.postValue(
                Resource.Error("An error occurred, please try again", emptyList())
            )
        }

    }

    private fun handleTeamListResponse(response: Response<List<Team>>): Resource<List<Team>> {
        if (response.isSuccessful && response.body()?.isNotEmpty()!!) {
            response.body()?.let {
                return Resource.Success(it)
            }
        } else {
            response.errorBody()?.let {
                return Resource.Error(it.string(), emptyList())
            }
        }
        return Resource.Error("An error occurred, please try again", emptyList())
    }


}