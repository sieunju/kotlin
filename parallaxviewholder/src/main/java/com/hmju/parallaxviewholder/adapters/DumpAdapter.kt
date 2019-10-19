package com.hmju.parallaxviewholder.adapters

import android.content.Context
import android.view.ViewGroup
import com.hmju.parallaxviewholder.BaseViewHolder
import com.hmju.parallaxviewholder.DumpViewHolder
import com.hmju.parallaxviewholder.ParallaxViewHolder
import com.hmju.parallaxviewholder.structs.DumpStruct
import com.hmju.parallaxviewholder.structs.ParallaxStruct

/**
 * kotlinStudy
 * Class: DumpAdapter
 * Created by jsieu on 2019-07-14.
 *
 * Description:
 */
class DumpAdapter(context: Context) : BaseAdapter(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_PARALLAX -> ParallaxViewHolder.newInstance(parent)
            else -> DumpViewHolder.newInstance(parent)
        }
    }

    fun dumpData() {
        for (i in 1..29) {
            if (i % 2 == 0) {
                mItems.add(ItemStruct(ParallaxStruct("시즌$i"), TYPE_PARALLAX))
            } else {
                mItems.add(ItemStruct(DumpStruct("Title$i"), TYPE_DUMP))
            }
        }
    }
}