package com.hmju.kotlin.dataModels

import java.io.Serializable

/**
 * kotlinStudy
 * Class: TitleOnlyStruct
 * Created by jsieu on 2019-08-25.
 *
 * Description:
 */
class TitleOnlyStruct(title: String) : Serializable {
    var title: String? = ""

    init {
        this.title = title
    }
}