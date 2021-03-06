package com.example.room.dashboard

class DashboardPresenterImpl(var screen: DashboardScreen): DashboardPresenter {

    init {
        screen.presenter = this
    }

    override fun onTextviewTapped(title: String) {
        when(title.toLowerCase()) {
            "add" -> screen.showAddForm()
            "edit","delete" -> screen.showSearchTab()
            "logout" -> screen.logout()
        }
    }
}