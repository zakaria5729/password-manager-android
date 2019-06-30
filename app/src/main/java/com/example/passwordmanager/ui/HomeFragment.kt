package com.example.passwordmanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.example.passwordmanager.R
import com.example.passwordmanager.db.PasswordDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_view.setHasFixedSize(true)
        //recycler_view.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.layoutManager = LinearLayoutManager(context)

        launch {
            context?.let {
                val passwords = PasswordDatabase(it).getPasswordDao().getAllPasswords()
                recycler_view.adapter = PasswordAdapter(passwords)
            }
        }

        btn_add_password.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddPasswordFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}
