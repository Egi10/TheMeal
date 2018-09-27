package id.co.egifcb.themeal.ui.detailmeals

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import id.co.egifcb.themeal.api.ApiResponse

class DetailMealsPresenter(private val detailMealsView: DetailMealsView) {
    fun getDetail(id: String?) {
        detailMealsView.showLoading()
        AndroidNetworking.get("https://www.themealdb.com/api/json/v1/1/lookup.php?i={id}")
                .addPathParameter("id", id)
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(ApiResponse::class.java, object : ParsedRequestListener<ApiResponse> {
                    override fun onResponse(response: ApiResponse?) {
                        response?.let {
                            detailMealsView.onResponse(it.meals)
                        }
                        detailMealsView.hideLoading()
                    }

                    override fun onError(anError: ANError?) {
                        detailMealsView.onError(anError?.message)
                        detailMealsView.hideLoading()
                    }
                })
    }
}