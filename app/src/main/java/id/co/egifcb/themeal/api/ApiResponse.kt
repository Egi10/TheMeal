package id.co.egifcb.themeal.api

import com.google.gson.annotations.SerializedName
import id.co.egifcb.themeal.model.Categories
import id.co.egifcb.themeal.model.Meals

data class ApiResponse (
        @SerializedName("categories")
        val categories: ArrayList<Categories>?,

        @SerializedName("meals")
        val meals: ArrayList<Meals>?)