package com.hmju.parallaxviewholder.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

    private val TAG: String = javaClass.simpleName

    // [s] Divider Variable Info
    private val mDivider: Drawable by lazy {
        ContextCompat.getDrawable(
            mContext,
            R.drawable.divider_vertical_size_1
        ) as Drawable
    }
    private val mBounds: Rect = Rect()
    // [e] Divider Variable Info

    // [s] Sticky Variable Info
    private var mStickyId: Int = -1 // Required Variable.
    private var mStickyRoot: View? = null
    private var mTvStickyTitle: TextView? = null
    private val mStickyHeight: Int by lazy {
        mContext.resources.getDimensionPixelOffset(R.dimen.parallax_height_title_bottom)
    }
    private val mStickyLocation: Int by lazy {
        (mContext.resources.getDimensionPixelOffset(R.dimen.parallax_height_title_bottom)
                - mContext.resources.getDimensionPixelOffset(R.dimen.parallax_height_max))
    }
    // [e] Sticky Variable Info

    /**
     * set Func.
     * 스티커로 표현하고 싶은 View Id
     * @param id View Id
     * @author hmju
     */
    fun setStickyViewId(id: Int): StickyItemDecoration {
        mStickyId = id
        return this
    }

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
        // initStickyView
        if (mStickyRoot == null) {
            mStickyRoot = initStickyView(parent)
            fixLayoutSize(mStickyRoot, parent)
        }

        var isMove = false                  // StickyView 이동 유무에 대한 변수.
        val cnt: Int = parent.childCount    // View Count.

        // 0 ~ cnt 까지.
        for (i in 0 until cnt) {
            // Null 인경우 다음 반복문
            val view: View = parent.getChildAt(i) ?: continue

            // 스티커 ID를 가진 View 인경우.
            if (view.id == mStickyId) {
                mTvStickyTitle?.text = view.tag.toString()

                /**
                 *  아래 로직 범위 설명.                 ↑
                 *  |||||||||||||||||||||||||||||||||| ↑
                 *  |||||||ParallaxViewHolder||||||||| ↕
                 *  |||||||||||||||||||||||||||||||||| ↓
                 *  ■■■■■■■ Sticky Bottom Area ■■■■■■■ X
                 *  고정으로 하고 싶은 범위 부터 ParallaxViewHolder 맨 위까지.
                 *  AS-IS if(mStickyLocation < view.top && view.top <= mStickyHeight)
                 */
                if (view.top in (mStickyLocation + 1)..mStickyHeight) {
                    isMove = true
                    Log.d(TAG, "View Tag\t${view.tag} \t V Top\t${view.top}")
                    moveSticky(c, view)
                    break
                }

            }
        }

        // Sticky Not Move.
        if (!isMove) {
            drawSticky(c, mStickyRoot)
        }
    }

    /**
     * init StickyView
     * @param parent RecyclerView ViewGroup.
     * @author hmju
     */
    private fun initStickyView(parent: RecyclerView): View {
        val rootView: View =
            LayoutInflater.from(mContext).inflate(R.layout.view_parallax_sticky, parent, false)
        mTvStickyTitle = rootView.findViewById(R.id.tv_sticky_title)
        return rootView
    }

    /**
     * Sticky Layout Fix Func.
     * RecyclerView 위에 StickyView 를 플로팅처럼 띄우기 위해 Layout 크기를 고정으로 해야함.
     * StickyView init 할때만 실행하면 된다.
     * @param view -> StickyView
     * @param parent -> RecyclerView
     * @author hmju
     */
    private fun fixLayoutSize(view: View?, parent: ViewGroup) {
        if (view == null) return

        val widthSpec: Int =
            View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
        val heightSpec: Int =
            View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)
        val childWidth: Int = ViewGroup.getChildMeasureSpec(
            widthSpec,
            parent.paddingLeft + parent.paddingRight,
            view.layoutParams.width
        )
        val childHeight: Int = ViewGroup.getChildMeasureSpec(
            heightSpec,
            parent.paddingTop + parent.paddingBottom,
            view.layoutParams.height
        )

        view.measure(childWidth, childHeight)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
    }

    /**
     * Move Sticky Func.
     * @param c -> RecyclerView Canvas.
     * @param parallaxView -> ParallaxViewHolder View.
     * @author hmju
     */
    private fun moveSticky(c: Canvas, @NonNull parallaxView: View?) {
        if (parallaxView == null || mStickyRoot == null) return
        c.save()
        c.translate(0F, (parallaxView.top - mStickyHeight).toFloat())
        mStickyRoot?.draw(c)
        c.restore()
    }

    /**
     * Canvas Top Draw func.
     * @param c -> RecyclerView Canvas
     * @param view -> StickyView
     * @author hmju
     */
    private fun drawSticky(c: Canvas, view: View?) {
        if (view == null) return
        c.save()
        c.translate(0F, 0F)
        view.draw(c)
        c.restore()
    }
}