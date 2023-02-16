package com.example.userapp.ui.userdetails

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userapp.data.dao.UserDao
import com.example.userapp.data.db.UserDatabase
import com.example.userapp.data.model.UserDetails
import com.example.userapp.data.repository.UserDetailsRepository
import com.example.userapp.ui.base.UiState
import com.example.userapp.utils.Commons
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UserViewModel(
    private val context: Context,
    private val userDetailsRepository: UserDetailsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<UserDetails>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<UserDetails>>> = _uiState
    private lateinit var userDao: UserDao
    init {
        fetchUserDetails()
    }

    private fun fetchUserDetails() {
        if (Commons.checkForInternet(context)) {
            viewModelScope.launch {
                userDetailsRepository.getUserDetails()
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                    }.collect {
                        _uiState.value = UiState.Success(it)
                    }
            }
        }else{
            GlobalScope.launch {
                val db = UserDatabase.getInstance(context)
                userDao = db.userDao()
                _uiState.value = UiState.Success(userDao.getAllUsers())
            }
        }
    }
}