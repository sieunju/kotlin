package com.hmju.kotlin.dataModels

import java.io.Serializable

/**
 * kotlinStudy
 * Class: BaseStruct
 * Created by jsieu on 2019-07-11.
 *
 * Description: Base Data Model Class
 */
open class BaseStruct : Serializable {
    var type: String = ""
        // Custom getter setter
        get() = if (field.isNotEmpty()) field else ""
        set(value: String) {
            field = if (value.isNotEmpty()) value else ""
        }

    var msg: String = ""
        // Custom getter setter
        get() = if (field.isNotEmpty()) field else ""
        set(value: String) {
            field = if (value.isNotEmpty()) value else ""
        }
}