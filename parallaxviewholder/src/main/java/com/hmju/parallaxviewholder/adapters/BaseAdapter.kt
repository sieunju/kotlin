@file:Suppress("UNREACHABLE_CODE")

package com.hmju.parallaxviewholder.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.hmju.parallaxviewholder.BaseViewHolder
import com.hmju.parallaxviewholder.ParallaxViewHolder
import java.util.*

/**
 * kotlinStudy
 * Class: BaseAdapter
 * Created by jsieu on 2019-07-14.
 *
 * Description: BaseAdapter Class
 */
@Suppress("CAST_NEVER_SUCCEEDS")
abstract class BaseAdapter(protected var mContext: Context) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    protected var mItems: ArrayList<ItemStruct<*>>? = ArrayList()
    protected var mSize: Int = 0

    // [s] row type defin

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
        holder.run { onBindView(pos,mItems?.get(pos) as Nothing) }
    }

    override fun getItemCount(): Int {
        // Null 값 처리
        return if (mItems == null) {
            0
        } else {
            mItems!!.size
        }// Null 값이 아닌경우 Int 절로 size 값을 리턴
    }

    override fun getItemViewType(position: Int): Int {
        return mItems!![position].viewType
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