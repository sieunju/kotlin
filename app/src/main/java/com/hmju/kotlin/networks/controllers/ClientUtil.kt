package com.hmju.kotlin.networks.controllers

import com.hmju.kotlin.BuildConfig
import com.hmju.kotlin.utils.Logger
import com.hmju.kotlin.utils.RetrofitLogger
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Description: Retrofit2 Client SingleTon Class
 *
 * Created by juhongmin on 2020/05/11
 */
class ClientUtil {
    private val BASE_URL: String = "https://127.0.0.1:1001"
    private val BASE_SSL_URL: String = "https://127.0.0.1:1001"

    val mRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getBaseClient())
            .build()
    }
    val mSSLRetrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_SSL_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getBaseClient())
            .build()
    }

    private val TIME_OUT_CONNECT: Long = 5      // 연결 시간 제한 (초단위)
    private val TIME_OUT_READ: Long = 5         // 읽기 시간 제한 (초단위)
    private val TIME_OUT_WRITE: Long = 5        // 쓰기 시간 제한 (초단위)

    private val MAX_CONNECTION: Int = 5         // Thread Pool 개수 제한
    private val CONNECTION_DURATION: Long = 1   // Connection Pool 간격 (초단위)

    // [s] Define Response Http Code (REST API Http Code 기반)
    enum class REST(val code: Int) {
        OK(200),
        BAD_REQUEST(400),
        FORBIDDEN(403),
        NOT_FOUND(404),
        SERVER_ERROR(500),
        SERVER_BAD_GATEWAY(502)
    }
    // [e] Define Response Http Code

    companion object {
        // Single Ton Class
        val instance: ClientUtil by lazy { ClientUtil() }
    }

    fun getConverterRetrofit(): Retrofit.Builder{
        return getConverterRetrofit(BASE_URL)
    }

    fun getConverterRetrofit(baseUrl: String): Retrofit.Builder{
        return Retrofit.Builder().baseUrl(baseUrl).client(getBaseClient())
    }

    /**
     * API 통신시 Header 세팅 하는 Interceptor
     * @author hmju
     */
    class HeaderInterceptor : Interceptor {

        val REQUEST_ACCEPT: String = "accept"
        val VALUE_ACCEPT: String = "application/json"

        override fun intercept(chain: Interceptor.Chain): Response {
            val origin: Request = chain.request()

            val request: Request = origin.newBuilder()
                .header(REQUEST_ACCEPT, VALUE_ACCEPT)
                .method(origin.method(), origin.body())
                .build()
            return chain.proceed(request)
        }
    }

    /**
     * 서버 에러등 Not Found Error 발생시 처리하는 Interceptor
     * @author hmju
     */
    class ForbiddenInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val response: Response = chain.proceed(chain.request())

            when (response.code()) {
                REST.SERVER_ERROR.code,
                REST.SERVER_BAD_GATEWAY.code -> {
                    // 서버 에러..
                    Logger.d("서버 에러 발생! 에러 코드\t${response.code()}")
                }
                else -> {
                }
            }
            return response
        }
    }

    /**
     * Base OkHttpClient getter
     *
     * @author hmmju
     */
    private fun getBaseClient(): OkHttpClient {
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_READ, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_WRITE, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(MAX_CONNECTION, CONNECTION_DURATION, TimeUnit.SECONDS))
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(ForbiddenInterceptor())

        // Retrofit Logger -> Debug Mode 인경우 에만 활성화.
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(RetrofitLogger().setLevel(RetrofitLogger.Level.BODY))
        }

        return httpClient.build()
    }
}