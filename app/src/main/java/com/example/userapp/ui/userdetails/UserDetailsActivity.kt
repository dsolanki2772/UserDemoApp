package com.example.userapp.ui.userdetails

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.userapp.databinding.UserDetailsActivityBinding


class UserDetailsActivity : AppCompatActivity() {

    private lateinit var binding: UserDetailsActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserDetailsActivityBinding.inflate(layoutInflater)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(binding.root)
        setUI()
    }

    private fun setUI() {
        Glide.with(binding.imageViewBanner.context)
            .load(intent.getStringExtra("Profile"))
            .into(binding.imageViewBanner)
        binding.textName.text = "Name: ${intent.getStringExtra("Name")}"
        binding.tvUname.text = "Username: ${intent.getStringExtra("Uname")}"
        binding.tvEmail.text = "Email: ${intent.getStringExtra("Email")}"
        binding.tvAddress.text = "Address: ${intent.getStringExtra("Address")}"
        binding.tvAge.text = "Age: ${intent.getStringExtra("Age")}"
        binding.tvPhone.text = "Phone: ${intent.getStringExtra("Phone")}"
        binding.tvGender.text = "Gender: ${intent.getStringExtra("Gender")}"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}