package com.example.passwordmanager.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.navigation.Navigation

import com.example.passwordmanager.R
import com.example.passwordmanager.db.Password
import com.example.passwordmanager.db.PasswordDatabase
import com.example.passwordmanager.util.showToast
import kotlinx.android.synthetic.main.fragment_add_password.*
import kotlinx.coroutines.launch

class AddPasswordFragment : BaseFragment() {

    private var passwordArgs: Password? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_password, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            passwordArgs = AddPasswordFragmentArgs.fromBundle(it).password
            et_username_email.editText!!.setText(passwordArgs?.usernameOrEmail)
            et_password.editText!!.setText(passwordArgs?.password)
            et_account_name.editText!!.setText(passwordArgs?.accountName)
        }

        btn_save.setOnClickListener { view ->
            val usernameOrEmail = et_username_email.editText!!.text.toString().trim()
            val password = et_password.editText!!.text.toString().trim()
            val accountName = et_account_name.editText!!.text.toString().trim()

            if (usernameOrEmail.isEmpty()) {
                et_username_email.error = "Username or email required *"
                et_username_email.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                et_password.error = "Password required *"
                et_password.requestFocus()
                return@setOnClickListener
            }

            if (accountName.isEmpty()) {
                et_account_name.error = "Account name required *"
                et_account_name.requestFocus()
                return@setOnClickListener
            }

            //launch is the coroutines scope where we can apply the coroutines scope process
            launch {
                context?.let {
                    val passwordObj = Password(usernameOrEmail, password, accountName)

                    if (passwordArgs == null) {
                        PasswordDatabase(it).getPasswordDao().addPassword(passwordObj)
                        it.showToast("Note saved")
                    } else {
                        passwordObj.id = passwordArgs!!.id
                        PasswordDatabase(it).getPasswordDao().updatePassword(passwordObj)
                        it.showToast("Note updated")
                    }

                    btn_save.hideKeyboard()
                    activity!!.onBackPressed()

                    /*val action = AddPasswordFragmentDirections.actionAddPasswordFragmentToHomeFragment()
                    Navigation.findNavController(view).navigate(action)*/
                }
            }
        }
    }

    private fun View.hideKeyboard() {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}
