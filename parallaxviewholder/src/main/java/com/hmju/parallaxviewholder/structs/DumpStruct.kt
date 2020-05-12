package com.hmju.parallaxviewholder.structs

import java.io.Serializable

/**
 * kotlin_github_branch
 * Class: DumpStruct
 * Created by jsieu on 2019-09-16.
 *
 * Description: ParallaxViewHolder 테스트용 DumpStruct.
 */
class DumpStruct(title: String) : Serializable {
    var title: String? = title
    var comment: String? = null

    init {
        comment = ""
    }
}