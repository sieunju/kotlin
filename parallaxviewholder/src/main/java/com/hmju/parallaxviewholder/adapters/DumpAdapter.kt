package com.hmju.parallaxviewholder.adapters

import android.content.Context
import android.view.ViewGroup
import com.hmju.parallaxviewholder.viewholders.BaseViewHolder
import com.hmju.parallaxviewholder.viewholders.DumpViewHolder
import com.hmju.parallaxviewholder.viewholders.ParallaxViewHolder
import com.hmju.parallaxviewholder.structs.DumpStruct
import com.hmju.parallaxviewholder.structs.ParallaxStruct

/**
 * kotlin_github_branch
 * Class: DumpAdapter
 * Created by jsieu on 2019-07-14.
 *
 * Description: Parallax ViewHolder 테스트용 DumpAdapter Class
 */
class DumpAdapter(context: Context) : BaseAdapter(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_PARALLAX -> ParallaxViewHolder.newInstance(parent)
            else -> DumpViewHolder.newInstance(parent)
        }
    }

    fun dumpData() {
        var cnt = 1
        for (i in 1..29) {
            if (i != 0 && i % 2 == 0) {
                mItems.add(ItemStruct(ParallaxStruct("시즌${cnt++}"), TYPE_PARALLAX))
            } else {
                mItems.add(ItemStruct(DumpStruct("Title"), TYPE_DUMP))
            }
        }
    }
}