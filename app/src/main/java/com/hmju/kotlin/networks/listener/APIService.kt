package com.hmju.kotlin.networks.listener

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Description: API에 대한 정의 인터페이스
 *
 * Created by juhongmin on 2020/05/11
 */
interface APIService {
    // 테스트 API
    @GET("/photos")
    fun requestPhotos(
        @Query("albumId") albumId: String
    ): Call<JsonArray>

    @GET("/comments")
    fun requestComments(
        @Query(value = "postId", encoded = true) postId: String
    ): Call<JsonElement>

    @GET("/comments")
    fun requestComments(): Call<JsonElement>

    @GET("/api/jschoi/fighting")
    fun requestMemo(
        @Query(value = "pageNo") pageNo: Int
    ): Call<JsonElement>
}