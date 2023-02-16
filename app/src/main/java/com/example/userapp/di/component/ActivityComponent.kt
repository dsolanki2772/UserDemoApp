package com.example.userapp.di.component

import com.example.userapp.di.ActivityScope
import com.example.userapp.di.module.ActivityModule
import com.example.userapp.ui.userdetails.UserActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: UserActivity)
}