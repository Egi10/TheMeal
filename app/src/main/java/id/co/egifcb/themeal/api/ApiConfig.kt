package id.co.egifcb.themeal.api

import android.content.Context
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class ApiConfig {
    companion object {
        fun getApi(context: Context) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15L, TimeUnit.SECONDS)
                    .writeTimeout(15L, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build()

            AndroidNetworking.initialize(context, httpClient)
        }
    }
}