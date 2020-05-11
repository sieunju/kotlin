package com.hmju.kotlin.networks.listener

/**
 * Description: 서버간 API 통신시 콜백 받는 리스너
 *
 * Created by juhongmin on 2020/05/11
 */
@Suppress("UNCHECKED_CAST")
interface NetworkCallback<T> {
    fun onSuccess(data: T)
    fun onFail(error: String)
}