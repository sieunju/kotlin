package com.hmju.kotlin.dataModels

import java.io.Serializable

/**
 * kotlinStudy
 * Class: ResizeStruct
 * Created by jsieu on 2019-07-18.
 *
 * Description:
 */
class ResizeStruct : Serializable {
    var title: String? = null
        protected set
    var color: String? = null
        protected set
}