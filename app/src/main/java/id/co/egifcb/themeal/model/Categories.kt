package id.co.egifcb.themeal.model

import com.google.gson.annotations.SerializedName

data class Categories (
        @SerializedName("idCategory")
        val idCategory: String? = null,

        @SerializedName("strCategory")
        val strCategory: String? = null,

        @SerializedName("strCategoryThumb")
        val strCategoryThumb: String? = null,

        @SerializedName("strCategoryDescription")
        val strCategoryDescription: String? = null)