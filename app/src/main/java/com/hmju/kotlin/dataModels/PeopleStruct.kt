package com.hmju.kotlin.dataModels

import java.io.Serializable

/**
 * kotlinStudy
 * Class: PeopleStruct
 * Created by jsieu on 2019-07-11.
 *
 * Description: People Struct Class
 */
class PeopleStruct : Serializable {

    var name: String = ""
        get() = if (field.isNotEmpty()) field else ""
        set(value) {
            field = if (value.isNotEmpty()) value else ""
        }
    var age: String = ""
        get() = if (field.isNotEmpty()) field else ""
        set(value) {
            field = if (value.isNotEmpty()) value else ""
        }
}