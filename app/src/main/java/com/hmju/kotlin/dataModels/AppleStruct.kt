package com.hmju.kotlin.dataModels

import java.io.Serializable

/**
 * kotlinStudy
 * Class: AppleStruct
 * Created by jsieu on 2019-07-11.
 *
 * Description:
 */
class AppleStruct : Serializable {

    var title: String = ""
        get() = if (field.isNotEmpty()) field else ""
        set(value) {
            field = if (value.isNotEmpty()) value else ""
        }

    var tag: String = ""
        get() = if (field.isNotEmpty()) field else ""
        set(value) {
            field = if (value.isNotEmpty()) value else ""
        }

    var test: Boolean = false
        get() = title != null
        set(value) {
            field = value
        }
}