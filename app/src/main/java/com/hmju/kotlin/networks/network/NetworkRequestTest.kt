package com.hmju.kotlin.networks.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.hmju.kotlin.networks.controllers.ClientUtil
import com.hmju.kotlin.networks.listener.APIService
import com.hmju.kotlin.networks.listener.NetworkCallback
import com.hmju.kotlin.utils.Logger
import retrofit2.Call
import retrofit2.Response

/**
 * Description:
 *
 * Created by juhongmin on 2020/05/11
 */
class NetworkRequestTest : BaseNetwork<JsonElement> {
    constructor(ctx: Context, listener: NetworkCallback<JsonElement>) {
        mContext = ctx
        mActionListener = listener
    }

    private val mCallBack: BaseCallBack = object : BaseCallBack() {
        override fun onResponse(call: Call<JsonElement>?, response: Response<JsonElement>?) {
            super.onResponse(call, response)
            if (response != null) {
                Logger.d("TEST\t${response.body().asJsonArray}")
            }
            when (response?.code()) {
                ClientUtil.REST.OK.code -> {
                    mActionListener?.onSuccess(response.body().asJsonArray)
                }
            }
        }

        override fun onFailure(call: Call<JsonElement>?, t: Throwable?) {
            super.onFailure(call, t)
        }
    }

    override fun actionDone(type: TYPE, msg: String) {

    }

    override fun run() {

//        val gson: Gson = GsonBuilder()
//            .registerTypeAdapter(AppleStruct::class.java, TestDeserializer())
//            .create()

        ClientUtil.instance.mRetrofit.create(APIService::class.java).requestMemo(1).enqueue(mCallBack)
//        ClientUtil.instance.getConverterRetrofit().addConverterFactory(GsonConverterFactory.create(gson)).build().create(APIService::class.java).requestComments().enqueue(mCallBack)
    }
}