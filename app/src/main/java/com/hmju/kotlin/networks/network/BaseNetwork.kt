package com.hmju.kotlin.networks.network

import android.content.Context
import com.hmju.kotlin.networks.controllers.NetworkController
import com.hmju.kotlin.networks.listener.NetworkCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Description:
 *
 * Created by juhongmin on 2020/05/11
 */
abstract class BaseNetwork<T> : Runnable {
    enum class TYPE {
        NEXT, NOT_RESPONSE
    }

    fun actionDone(type: TYPE) {
        // NEXT type 인경우 다음 API 호출.
        if (TYPE.NEXT == type) {
            NetworkController.instance.runNext()
        }
        // 아닌경우 나머지 ActionDone 함수 호출.
        else {
            actionDone(type, "")
        }
    }

    abstract fun actionDone(type: TYPE, msg: String)

    protected var mContext: Context? = null
    protected var mActionListener: NetworkCallback<T>? = null

    open inner class BaseCallBack : Callback<T> {

        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            actionDone(TYPE.NEXT)
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {

        }
    }
}