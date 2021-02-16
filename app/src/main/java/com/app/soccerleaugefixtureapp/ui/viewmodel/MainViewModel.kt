package com.app.soccerleaugefixtureapp.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.soccerleaugefixtureapp.data.model.Team
import com.app.soccerleaugefixtureapp.data.repository.FixtureRepository
import com.app.soccerleaugefixtureapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(private val fixtureRepository: FixtureRepository):ViewModel(){
    var teamList: MutableLiveData<Resource<List<Team>>> = MutableLiveData()


    fun getTeamList(context: Context)=viewModelScope.launch {
       val response=fixtureRepository.getTeamsList()
       teamList.postValue(handleTeamListResponse(response))

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