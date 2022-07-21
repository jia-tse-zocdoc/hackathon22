package com.example.hackathon22.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request()
            .newBuilder()
            .addHeader("Authorization", "Basic M2kxdjZmajUxYjZzbXI2dmpuNW9mYzY4dnY6MTc3bm5za242dWo5azEyYnFzZW9vbzR1Y2o1NGhubHRsYTlrMXM4dWhqdjhpaXYxZDdmdQ==")
            .build()
            .let(chain::proceed)
    }
}