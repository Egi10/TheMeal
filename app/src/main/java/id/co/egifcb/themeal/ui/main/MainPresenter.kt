package id.co.egifcb.themeal.ui.main

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import id.co.egifcb.themeal.api.ApiResponse

class MainPresenter(private val mainView: MainView) {
    fun getCategories() {
        mainView.showLoading()
        AndroidNetworking.get("https://www.themealdb.com/api/json/v1/1/categories.php")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(ApiResponse::class.java, object : ParsedRequestListener<ApiResponse> {
                    override fun onResponse(response: ApiResponse?) {
                        response?.let {
                            mainView.onResponse(it.categories)
                        }
                        mainView.hideLoading()
                    }

                    override fun onError(anError: ANError?) {
                        mainView.onError(anError?.message)
                    }

                })
    }
}