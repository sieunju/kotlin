package com.hmju.parallaxviewholder.structs

import java.io.Serializable

/**
 * kotlin_github_branch
 * Class: ParallaxStruct
 * Created by jsieu on 2019-09-16.
 *
 * Description: ParallaxViewHolder Struct Class
 */
class ParallaxStruct(title: String) : Serializable {
    var title: String? = title
    var comment: String? = null

    init {
        comment = ""
    }
}