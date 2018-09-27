package id.co.egifcb.themeal.ui.daftarmeals

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import id.co.egifcb.themeal.api.ApiResponse

class DaftarMealsPresenter(private val daftarMealsView: DaftarMealsView) {
    fun getMeals(category: String?) {
        daftarMealsView.showLoading()
        AndroidNetworking.get("https://www.themealdb.com/api/json/v1/1/filter.php?c={category}")
                .addPathParameter("category", category)
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(ApiResponse::class.java, object : ParsedRequestListener<ApiResponse> {
                    override fun onResponse(response: ApiResponse?) {
                        response?.let {
                            daftarMealsView.onResponse(response.meals)
                        }
                        daftarMealsView.hideLoading()
                    }

                    override fun onError(anError: ANError?) {
                        daftarMealsView.onError(anError?.message)
                        daftarMealsView.hideLoading()
                    }
                })
    }
}