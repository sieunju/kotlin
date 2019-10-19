package com.hmju.parallaxviewholder.structs

import java.io.Serializable

/**
 * kotlin_github_branch
 * Class: DumpStruct
 * Created by jsieu on 2019-09-16.
 *
 * Description: 테스트용 구조체.
 */
class DumpStruct : Serializable {
    var title: String? = null
    var comment: String? = null

    constructor(title: String){
        this.title = title
        comment = ""
    }
}