@file:Suppress("UNREACHABLE_CODE")

package com.hmju.parallaxviewholder.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.hmju.parallaxviewholder.BaseViewHolder
import com.hmju.parallaxviewholder.DumpViewHolder
import com.hmju.parallaxviewholder.ParallaxViewHolder
import com.hmju.parallaxviewholder.structs.DumpStruct
import com.hmju.parallaxviewholder.structs.ParallaxStruct
import java.util.*

/**
 * kotlinStudy
 * Class: BaseAdapter
 * Created by jsieu on 2019-07-14.
 *
 * Description: BaseAdapter Class
 */
abstract class BaseAdapter(protected var mContext: Context) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    protected val mItems: ArrayList<ItemStruct<*>> = ArrayList()

    // [s] row type define

    // [e] row type define

    // [s] View Type Define
    protected val TYPE_DUMP = 1
    protected val TYPE_PARALLAX = 2
    // [e] View Type Define

    class ItemStruct<ITEM>(data: ITEM, type: Int) {
        internal var data: ITEM = data
        internal var viewType: Int = type
    }

    @SuppressWarnings("unchecked")
    override fun onBindViewHolder(holder: BaseViewHolder<*>, pos: Int) {
        val data: Any? = mItems[pos].data
        when (holder) {
            is ParallaxViewHolder -> holder.onBindView(pos, data as ParallaxStruct)
            is DumpViewHolder -> holder.onBindView(pos, data as DumpStruct)
            else -> throw IllegalArgumentException("Invalid ViewHolder. Add BaseAdapter onBindViewHolder")
        }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return mItems[position].viewType
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<*>) {
        // Parallax ViewHolder
        if (holder is ParallaxViewHolder) {
            holder.onEnabled()
        }
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<*>) {
        // Parallax ViewHolder
        if (holder is ParallaxViewHolder) {
            holder.onDisabled()
        }
        super.onViewDetachedFromWindow(holder)
    }
}