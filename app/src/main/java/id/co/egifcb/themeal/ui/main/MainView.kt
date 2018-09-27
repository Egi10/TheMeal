package id.co.egifcb.themeal.ui.main

import id.co.egifcb.themeal.model.Categories

interface MainView {
    fun showLoading()
    fun onResponse(list: ArrayList<Categories>?)
    fun onError(message: String?)
    fun hideLoading()
}