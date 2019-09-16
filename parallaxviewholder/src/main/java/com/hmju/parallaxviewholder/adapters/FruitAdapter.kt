package com.hmju.parallaxviewholder.adapters

import android.content.Context
import android.view.ViewGroup
import com.hmju.parallaxviewholder.BaseViewHolder
import com.hmju.parallaxviewholder.DumpViewHolder
import com.hmju.parallaxviewholder.ParallaxViewHolder

/**
 * kotlinStudy
 * Class: FruitAdapter
 * Created by jsieu on 2019-07-14.
 *
 * Description:
 */
class FruitAdapter(context: Context) : BaseAdapter(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_PARALLAX -> ParallaxViewHolder.newInstance(parent)
            else -> DumpViewHolder.newInstance(parent)
        }
    }
}