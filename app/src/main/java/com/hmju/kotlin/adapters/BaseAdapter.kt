package com.hmju.kotlin.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.hmju.kotlin.dataModels.AppleStruct
import com.hmju.kotlin.dataModels.GrapeStruct
import com.hmju.kotlin.dataModels.OrangeStruct
import com.hmju.kotlin.dataModels.ResizeStruct
import com.hmju.kotlin.viewholders.*
import java.lang.IllegalArgumentException
import java.util.ArrayList

/**
 * Description:
 *
 * Created by juhongmin on 2020/05/11
 */
abstract class BaseAdapter(protected var mContext: Context) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    protected val mItems: ArrayList<ItemStruct<*>> = ArrayList()
    protected var mSize: Int = 0

    // [s] row type define
    protected val ROW_RESIZE = "expands"
    protected val ROW_APPLE = "apple"
    protected val ROW_ORANGE = "orange"
    protected val ROW_GRAPE = "grape"
    // [e] row type define

    // [s] View Type Define
    protected val TYPE_APPLE = 1
    protected val TYPE_GRAPE = 2
    protected val TYPE_ORANGE = 3
    protected val TYPE_RESIZE = 4
    protected val TYPE_PINTEREST = 5
    // [e] View Type Define

    class ItemStruct<ITEM>(data: ITEM, type: Int) {
        internal val data: ITEM = data
        internal val viewType: Int = type
    }

    @SuppressWarnings("unchecked")
    override fun onBindViewHolder(holder: BaseViewHolder<*>, pos: Int) {
        val data = mItems[pos].data
        when (holder) {
            is AppleViewHolder -> holder.onBindView(pos, data as AppleStruct)
            is OrangeViewHolder -> holder.onBindView(pos, data as OrangeStruct)
            is GrapeViewHolder -> holder.onBindView(pos, data as GrapeStruct)
            is ResizeViewHolder -> holder.onBindView(pos, data as ResizeStruct)
            is ParallaxViewHolder -> holder.onBindView(pos, data as ResizeStruct)
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