package com.hmju.kotlin.dataModels

import java.io.Serializable

/**
 * kotlinStudy
 * Class: BaseResponseData
 * Created by jsieu on 2019-08-25.
 *
 * Description:
 */
class BaseResponseData : Serializable {

    // Data List
    var list: ArrayList<DynamicStruct>? = null
}