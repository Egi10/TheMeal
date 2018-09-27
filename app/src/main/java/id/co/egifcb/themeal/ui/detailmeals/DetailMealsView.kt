package id.co.egifcb.themeal.ui.detailmeals

import id.co.egifcb.themeal.model.Meals

interface DetailMealsView {
    fun showLoading()
    fun onResponse(list: ArrayList<Meals>?)
    fun onError(message: String?)
    fun hideLoading()
}