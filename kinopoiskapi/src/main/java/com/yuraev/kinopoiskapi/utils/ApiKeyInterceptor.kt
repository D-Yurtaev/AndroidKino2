package com.yuraev.kinopoiskapi.utils

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        print("AAAAAAAAAAAAA")
        print(chain.request())
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("X-Api-Key",apiKey)
                .build()
        )
    }
}
