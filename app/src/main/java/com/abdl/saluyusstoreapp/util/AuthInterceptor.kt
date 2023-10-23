package com.abdl.saluyusstoreapp.util

import android.util.Log
import com.abdl.saluyusstoreapp.di.LoginSession
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val loginSession: LoginSession
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var token = ""
        runBlocking {
            token = loginSession.getToken().first()
        }

        Log.d("AuthInterceptor", "cek ya $token")

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(request)
    }
}