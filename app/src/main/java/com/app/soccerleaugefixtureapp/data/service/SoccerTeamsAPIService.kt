package com.app.soccerleaugefixtureapp.data.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SoccerTeamsAPIService {
    companion object {
        //private val BASE_URL = "http://10.0.2.2:3000/"
        private val BASE_URL = "https://my-json-server.typicode.com/irematlass/SoccerLeaugeFixtureApp/"

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
        val api: SoccerTeamsAPI by lazy {
            retrofit.create(SoccerTeamsAPI::class.java)
        }
    }
}