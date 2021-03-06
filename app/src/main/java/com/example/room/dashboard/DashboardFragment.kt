package com.example.room.dashboard


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.room.MainActivity
import com.example.room.R
import com.example.room.add.AddDataActivity

class DashboardFragment : Fragment(), DashboardScreen {

    lateinit var addLabel: TextView
    lateinit var editLabel: TextView
    lateinit var deleteLabel: TextView
    lateinit var logoutLabel: TextView

    override lateinit var presenter: DashboardPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        with(root) {
            addLabel = findViewById(R.id.addLabel)
            addLabel.setOnClickListener {
                presenter.onTextviewTapped("add")
            }

            editLabel = findViewById(R.id.editLabel)
            editLabel.setOnClickListener {
                presenter.onTextviewTapped("edit")
            }

            deleteLabel = findViewById(R.id.deleteLabel)
            deleteLabel.setOnClickListener {
                presenter.onTextviewTapped("delete")
            }
            logoutLabel = findViewById(R.id.logoutLabel)
            logoutLabel.setOnClickListener {
                presenter.onTextviewTapped("logout")
            }
        }
        return root
    }

    override fun showAddForm() {
        val i = Intent(activity, AddDataActivity::class.java)
        startActivity(i)

    }

    override fun showSearchTab() {
        activity?.let {
            (it as MainActivity).navigation.selectedItemId = R.id.navigation_search
        }
    }

    override fun logout() {
        activity?.let {
            (it as MainActivity).preferences.edit().clear().apply()
            it.navigation.selectedItemId = R.id.navigation_home
        }
    }

    override fun show(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    companion object {
        @JvmStatic
        fun newInstance() = DashboardFragment()
    }
}
