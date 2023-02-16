package com.example.userapp.di.component

import android.content.Context
import com.example.userapp.MVVMApplication
import com.example.userapp.data.api.NetworkService
import com.example.userapp.data.repository.UserDetailsRepository
import com.example.userapp.di.ApplicationContext
import com.example.userapp.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: MVVMApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getUserDetailsRepository(): UserDetailsRepository
}