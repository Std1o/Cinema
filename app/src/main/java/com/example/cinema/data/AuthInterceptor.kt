package com.example.cinema.data

import com.example.cinema.common.Constants
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter("token", Constants.TOKEN).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}