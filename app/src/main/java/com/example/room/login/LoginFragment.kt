package com.example.room.login


import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.room.MainActivity
import com.example.room.R
import com.example.room.dashboard.DashboardFragment
import com.example.room.showSnackBar


class LoginFragment : Fragment(), LoginScreen {

    lateinit var loginButton: Button
    lateinit var usernameEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var containerLayout: ConstraintLayout

    override lateinit var presenter: LoginPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.activity_login,container,false)

        with(root) {
            containerLayout = findViewById(R.id.containerLayout)
            usernameEditText = findViewById(R.id.usernameEditText)
            passwordEditText = findViewById(R.id.passwordEditText)

            loginButton = findViewById(R.id.loginButton)
            loginButton.setOnClickListener {
                val username = usernameEditText.text.toString()
                val password = passwordEditText.text.toString()
                presenter.onLoginButtonTapped(username,password)
            }
        }
        return root
    }

    override fun show(message: String) {
        var text = ""
        when(message) {
            "username" -> text = getString(R.string.username_empty)
            "password" -> text = getString(R.string.password_empty)
            "credentials" -> text = getString(R.string.wrong_credentials)
        }
        containerLayout.showSnackBar(text, Snackbar.LENGTH_LONG)
    }

    override fun displayDashboard() {
        activity?.let {
            val dashboardFragment = DashboardFragment.newInstance()
            (it as MainActivity).providePresenterDashboard(dashboardFragment)
            it.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.containerView,dashboardFragment)?.commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                LoginFragment()
    }
}
