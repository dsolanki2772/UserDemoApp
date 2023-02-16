package com.example.userapp.data.repository

import com.example.userapp.data.api.NetworkService
import com.example.userapp.data.model.UserDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDetailsRepository @Inject constructor(private val networkService: NetworkService) {
    fun getUserDetails(): Flow<List<UserDetails>> {
        return flow {
            emit(networkService.getUserDetails(100))
        }.map {
            it.results
        }
    }
}
