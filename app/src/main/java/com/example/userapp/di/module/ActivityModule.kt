package com.example.userapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.userapp.data.repository.UserDetailsRepository
import com.example.userapp.di.ActivityContext
import com.example.userapp.ui.base.ViewModelProviderFactory
import com.example.userapp.ui.userdetails.UserAdapter
import com.example.userapp.ui.userdetails.UserViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {
    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideTopHeadlineViewModel(userDetailsRepository: UserDetailsRepository): UserViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(UserViewModel::class) {
                UserViewModel(activity.applicationContext,userDetailsRepository)
            })[UserViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlineAdapter() = UserAdapter(activity,ArrayList())
}