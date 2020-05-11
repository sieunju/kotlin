package com.hmju.kotlin.dataModels

import java.io.Serializable

/**
 * kotlinStudy
 * Class: OrangeStruct
 * Created by jsieu on 2019-07-18.
 *
 * Description:
 */
class OrangeStruct : Serializable {
    var origin: String = ""
        get() = if (field.isNotEmpty()) field else ""
        set(value) {
            field = if (value.isNotEmpty()) value else ""
        }
    var price: String = ""
        get() = if (field.isNotEmpty()) field else ""
        set(value) {
            field = if (value.isNotEmpty()) field else ""
        }
}