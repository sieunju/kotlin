package com.hmju.kotlin.dataModels

import java.io.Serializable

/**
 * kotlinStudy
 * Class: GrapeStruct
 * Created by jsieu on 2019-08-25.
 *
 * Description:
 */
class GrapeStruct : Serializable {
    var tree: String? = null
        protected set
    var seed: String? = null
        protected set
}