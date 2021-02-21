package com.app.soccerleaugefixtureapp.di

import android.content.Context
import com.app.soccerleaugefixtureapp.data.db.LeaugeFixtureDao
import com.app.soccerleaugefixtureapp.data.db.LeaugeFixtureDatabase
import com.app.soccerleaugefixtureapp.data.repository.FixtureRepository
import com.app.soccerleaugefixtureapp.data.service.SoccerTeamsAPI
import com.app.soccerleaugefixtureapp.data.service.SoccerTeamsAPIService
import com.app.soccerleaugefixtureapp.ui.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
class MyModule {
    @Singleton
    @Provides
    fun provideSoccerTeamApi(): SoccerTeamsAPI = SoccerTeamsAPIService.api

    @Singleton
    @Provides
    fun provideFixtureDatabase(@ApplicationContext appContext: Context) =
        LeaugeFixtureDatabase.buildDatabase(appContext)

    @Singleton
    @Provides
    fun provideFixtureDao(fixtureDatabase: LeaugeFixtureDatabase) = fixtureDatabase.FixtureDao()

    @Singleton
    @Provides
    fun provideFixtureRepository(
        fixtureDao: LeaugeFixtureDao,
        soccerTeamsApi: SoccerTeamsAPI
    ) = FixtureRepository(fixtureDao, soccerTeamsApi)

    @Singleton
    @Provides
    fun provideMainViewModel(fixtureRepository: FixtureRepository) =
        MainViewModel(fixtureRepository)
}


