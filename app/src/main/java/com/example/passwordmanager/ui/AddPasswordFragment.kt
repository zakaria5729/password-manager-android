package com.example.passwordmanager.ui

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo

import com.example.passwordmanager.R
import com.example.passwordmanager.db.Password
import com.example.passwordmanager.db.PasswordDatabase
import com.example.passwordmanager.util.showToast
import kotlinx.android.synthetic.main.fragment_add_password.*
import kotlinx.coroutines.launch

class AddPasswordFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_password, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        et_username_email.requestFocus()

        btn_save.setOnClickListener {
            val usernameOrEmail = et_username_email.editText!!.text.toString().trim()
            val password = et_password.editText!!.text.toString().trim()
            val accountName = et_account_name.editText!!.text.toString().trim()

            if (usernameOrEmail.isEmpty()) {
                et_username_email.error = "Username or email required *"
                et_username_email.requestFocus()
                return@setOnClickListener
            } else {
                et_username_email.error = ""
            }

            if (password.isEmpty()) {
                et_password.error = "Password required *"
                et_password.requestFocus()
                return@setOnClickListener
            } else {
                et_password.error = ""
            }

            if (accountName.isEmpty()) {
                et_account_name.error = "Account name required *"
                et_account_name.requestFocus()
                return@setOnClickListener
            } else {
                et_account_name.error = ""
            }

            //launch is the coroutines scope where we can apply the coroutines scope process
            launch {
                context?.let {
                    PasswordDatabase(it).getPasswordDao().addPassword(Password(usernameOrEmail, password, accountName))
                    it.showToast("Note saved")
                    activity!!.onBackPressed()
                }
            }
        }
    }
}
