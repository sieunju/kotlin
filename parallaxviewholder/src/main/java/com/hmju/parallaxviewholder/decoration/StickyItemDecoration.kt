package com.hmju.parallaxviewholder.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.hmju.parallaxviewholder.R

/**
 * kotlin_github_branch
 * Class: StickyItemDecoration
 * Created by jsieu on 2019-10-23.
 *
 * Description: RecyclerView.ItemDecoration 기반의 특정 View 가 플로팅 처럼
 * 보이게 하는 클래스
 */
class StickyItemDecoration(@NonNull private val mContext: Context) : RecyclerView.ItemDecoration() {

    private val mDivider: Drawable by lazy {
        ContextCompat.getDrawable(
            mContext,
            R.drawable.divider_vertical_size_1
        ) as Drawable
    }
    private val mBounds: Rect = Rect()

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        c.save()
        val left = 0
        val right = parent.width

        val childCnt = parent.childCount

        for (i in 0 until childCnt) {
            val child = parent.getChildAt(i)

            val pos = parent.getChildAdapterPosition(child)

            // 첫번째 포지션이나, 맨마지막 포지션 아래 로직 Pass
            if (pos == 0 || pos == state.itemCount - 1) {
                continue
            }

            // Divider Rect + ChildView Height 의 좌표값 -> mBounds
            parent.getDecoratedBoundsWithMargins(child, mBounds)

            // 구분선 기준은 위에서 칠한다.
            val top = mBounds.top
            val bottom = top + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
        c.restore()
    }

    override fun getItemOffsets(
        rect: Rect,
        v: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val pos: Int = parent.getChildAdapterPosition(v)

        // onDraw 함수에 맞게 첫번쨰와 맨 마지막 View 는 구분선을 그리지 않는다.
        if (pos == NO_POSITION || pos == 0 || pos == state.itemCount - 1) {
            rect.setEmpty()
        }
        // 나머지 경우 구분선을 그린다.
        else {
            // 구부선은 위에서 칠하도록 함.
            rect.set(0, mDivider.intrinsicHeight, 0, 0)
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }
}