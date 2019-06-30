package com.example.passwordmanager.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanager.R
import com.example.passwordmanager.db.Password
import kotlinx.android.synthetic.main.item_password.view.*

class PasswordAdapter(private val passwords: List<Password>) : RecyclerView.Adapter<PasswordAdapter.PasswordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordViewHolder {
        return PasswordViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_password, parent, false))
    }

    override fun getItemCount() = passwords.size

    override fun onBindViewHolder(holder: PasswordViewHolder, position: Int) {
        holder.view.item_username_email.text = passwords[position].usernameOrEmail
        holder.view.item_password.text = passwords[position].password
        holder.view.item_account_name.text = passwords[position].accountName

        holder.view.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddPasswordFragment()
            action.password = passwords[position]
            Navigation.findNavController(it).navigate(action)
        }
    }

    class PasswordViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}