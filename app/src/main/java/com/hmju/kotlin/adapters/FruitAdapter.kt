package com.hmju.kotlin.adapters

import android.content.Context
import android.view.ViewGroup
import com.hmju.kotlin.dataModels.BaseResponseData
import com.hmju.kotlin.dataModels.DynamicStruct
import com.hmju.kotlin.utils.Logger
import com.hmju.kotlin.viewholders.*

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
            TYPE_APPLE -> AppleViewHolder.newInstance(parent)
            TYPE_ORANGE -> OrangeViewHolder.newInstance(parent)
            TYPE_GRAPE -> GrapeViewHolder.newInstance(parent)
            TYPE_RESIZE -> ParallaxViewHolder.newInstance(parent)
            else -> EmptyViewHolder.newInstance(parent)
        }
    }

    fun setData(data: BaseResponseData?) {

        val list: ArrayList<DynamicStruct>? = data?.list

        mItems.clear()
        // 타입에 맞는 데이터만 mItems 에 추가.
        if (list != null) {
            for (tmpData: DynamicStruct in list) {
                when (tmpData.type) {
                    ROW_APPLE -> mItems.add(ItemStruct(tmpData.struct_apple, TYPE_APPLE))
                    ROW_ORANGE -> mItems.add(ItemStruct(tmpData.struct_orange, TYPE_ORANGE))
                    ROW_GRAPE -> mItems.add(ItemStruct(tmpData.struct_grape, TYPE_GRAPE))
                    ROW_RESIZE -> mItems.add(ItemStruct(tmpData.struct_resize, TYPE_RESIZE))
                }
            }
        }

        Logger.d("ItemCount\t$itemCount")
    }
}