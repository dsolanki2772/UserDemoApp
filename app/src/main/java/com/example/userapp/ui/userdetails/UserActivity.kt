package com.example.userapp.ui.userdetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.userapp.MVVMApplication
import com.example.userapp.data.dao.UserDao
import com.example.userapp.data.db.UserDatabase
import com.example.userapp.data.model.UserDetails
import com.example.userapp.databinding.ActivityUserBinding
import com.example.userapp.di.component.DaggerActivityComponent
import com.example.userapp.di.module.ActivityModule
import com.example.userapp.ui.base.UiState
import com.example.userapp.utils.Commons
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserActivity : AppCompatActivity() {
    @Inject
    lateinit var userViewModel: UserViewModel

    @Inject
    lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityUserBinding
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        val db = UserDatabase.getInstance(applicationContext)
        userDao = db.userDao()
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            GlobalScope.launch { userDao.insertAll(it.data) }
                            binding.recyclerView.visibility = View.VISIBLE
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@UserActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(userList: List<UserDetails>) {
        adapter.addData(userList)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as MVVMApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }
}