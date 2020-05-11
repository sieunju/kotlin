package com.hmju.kotlin.networks.deserialier

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.hmju.kotlin.utils.Logger
import java.lang.reflect.Type

/**
 * Description:
 *
 * Created by juhongmin on 2020/05/11
 */
//class TestDeserializer : JsonDeserializer<AppleStruct> {
////
////    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): AppleStruct? {
////        Logger.d("TEST\t${json.toString()}")
////        val struct: AppleStruct = AppleStruct()
////        return struct
////    }
////}