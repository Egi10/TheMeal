package id.co.egifcb.themeal.ui.daftarmeals

import id.co.egifcb.themeal.model.Meals

interface DaftarMealsView {
    fun showLoading()
    fun onResponse(list: ArrayList<Meals>?)
    fun onError(message: String?)
    fun hideLoading()
}