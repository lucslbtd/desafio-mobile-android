package com.example.desafiomarvel.network

import com.example.desafiomarvel.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class MarvelInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.url

        val url = newRequest.newBuilder()
            .addQueryParameter("ts", ts())
            .addQueryParameter("apikey", apikey())
            .addQueryParameter("hash", hash(ts()))
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    private fun hash(ts: String): String {
        val requestKey = "${ts}${privateKey()}${apikey()}"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(requestKey.toByteArray()))
            .toString(16)
            .padStart(32, '0')
    }

    private fun ts() = Timestamp(System.currentTimeMillis()).time.toString()

    private fun apikey() = BuildConfig.PUBLIC_KEY

    private fun privateKey() = BuildConfig.PRIVATE_KEY
}
