package com.hmju.kotlin.dataModels

import java.io.Serializable
import java.util.*

/**
 * kotlinStudy
 * Class: GradeStruct
 * Created by jsieu on 2019-07-11.
 *
 * Description:
 */
class GradeStruct : Serializable {
    var grade: String = ""
        get() = if (field.isNotEmpty()) field else ""
        set(value) {
            field = if (value.isNotEmpty()) value else ""
        }

    var list: ArrayList<PeopleStruct>? = null
        get() = if (field.isNullOrEmpty()) field else ArrayList()
        set(value: ArrayList<PeopleStruct>?) {
            field = if (value.isNullOrEmpty()) value else ArrayList()
        }
}