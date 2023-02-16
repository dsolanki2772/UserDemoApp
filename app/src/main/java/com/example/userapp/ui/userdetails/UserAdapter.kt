package com.example.userapp.ui.userdetails

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.userapp.data.model.UserDetails
import com.example.userapp.databinding.UserItemLayoutBinding

class UserAdapter(private val context: Context, private val userList: ArrayList<UserDetails>) :
    RecyclerView.Adapter<UserAdapter.DataViewHolder>() {
    class DataViewHolder(private val binding: UserItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, userDetails: UserDetails) {
            val name =
                "${userDetails.name.title} ${userDetails.name.first} ${userDetails.name.last}"
            val address =
                "${userDetails.location.street.number}, ${userDetails.location.street.name}," +
                        "${userDetails.location.city}, ${userDetails.location.state}, ${userDetails.location.country}" +
                        "- ${userDetails.location.postcode}"
            binding.tvName.text = userDetails.login.username
            binding.tvPhone.text = userDetails.phone
            binding.tvEmail.text = userDetails.email
            Glide.with(binding.ivPic.context)
                .load(userDetails.picture.large)
                .into(binding.ivPic)
            itemView.setOnClickListener {
                val intent = Intent(context, UserDetailsActivity::class.java)
                intent.putExtra("Profile", userDetails.picture.large)
                intent.putExtra("Uname", userDetails.login.username)
                intent.putExtra("Name", name)
                intent.putExtra("Email", userDetails.email)
                intent.putExtra("Address", address)
                intent.putExtra("Age", userDetails.dob.age)
                intent.putExtra("Phone", userDetails.phone)
                intent.putExtra("Gender", userDetails.gender)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            UserItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(context, userList[position])
    }

    fun addData(list: List<UserDetails>) {
        userList.addAll(list)
    }

}