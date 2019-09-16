package com.hmju.parallaxviewholder

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import com.hmju.parallaxviewholder.structs.ParallaxStruct
import kotlin.math.abs
import kotlin.math.ceil

/**
 * kotlin_github_branch
 * Class: ParallaxViewHolder
 * Created by jsieu on 2019-09-12.
 *
 * Description: 사용자가 지정한 위치에 따라서 Percentage 로 표현할수 있는 ViewHolder Class.
 */
class ParallaxViewHolder(itemView: View, private val mViewGroup: ViewGroup) :
    BaseViewHolder<ParallaxStruct>(itemView),
    ViewTreeObserver.OnScrollChangedListener {

    private val TAG: String = "ParallaxViewHolder"

    private val mMaxHeight: Float =
        mContext.resources.getDimension(R.dimen.parallax_height_max)    // Child ViewHolder Max Height.
    private val mMinHeight: Float =
        mContext.resources.getDimension(R.dimen.parallax_height_min)    // Child ViewHolder Min Height.
    private val mLayoutParams: ConstraintLayout.LayoutParams =
        ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

    private var mDisplayY: Int = 0                  // Device Max Height
    private var mActionStartPos: Double = 0.0       // Action Start Position
    private var mActionEndPos: Double = 0.0         // Action End Position
    private var mCalculation: Float = 0f            // MaxHeight - MinHeight Value (Dpi)
    private var mIsExpands: Boolean = false         // Child ViewHolder Action Status
    private var mPosition: Int = 0                  // Current Position

    private var mClRoot: ConstraintLayout? = null   // 전체 ViewHolder 높이에 대한 레이아웃.
    private var mTvCenterTitle: TextView? = null    // 가운데 글씨 TextView
    private var mClBottom: ConstraintLayout? = null // 아래 글씨 표시 및 전체 알파값에 대한 레이아웃.
    private var mTvBottomTitle: TextView? = null    // 아래 글씨 TextView

    companion object {

        fun newInstance(parent: ViewGroup): ParallaxViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_parallax, parent, false)
            return ParallaxViewHolder(view, parent)
        }
    }

    init {
        initView()
        initSize()
    }

    /**
     * init View
     * @author hmju
     */
    private fun initView() {
        mClRoot = mRootView.findViewById(R.id.cl_root)
        mTvCenterTitle = mRootView.findViewById(R.id.tv_center_title)
        mClBottom = mRootView.findViewById(R.id.cl_bottom)
        mTvBottomTitle = mRootView.findViewById(R.id.tv_bottom_title)

        mViewGroup.viewTreeObserver.removeOnScrollChangedListener(this)
        mViewGroup.viewTreeObserver.addOnScrollChangedListener(this)
    }

    /**
     * init.. Size.
     * @author hmju
     */
    private fun initSize() {
        val display: Display = (mContext as Activity).windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        mDisplayY = size.y
        mActionStartPos = mDisplayY.toDouble() - mDisplayY * (4F / 10F)   // 4/10 지점
        mActionEndPos = mDisplayY.toDouble() * (2F / 10F)                 // 8/10 지점

        // Action 을 시작하는 지점이 Action 끝나는 지점보다 작으면 IllegalArgumentException 던짐.
        require(mActionStartPos >= mActionEndPos) { "The starting point cannot be lower than the ending point." }

        Log.d(TAG, "initSize mEvtStartPos\t$mActionStartPos mEvtEndPos\t$mActionEndPos")

        mCalculation = mMaxHeight - mMinHeight
    }

    /**
     * Bind Data View
     * @author hmju
     */
    override fun onBindView(pos: Int, data: ParallaxStruct) {
        mPosition = pos
    }

    /**
     * ParallaxViewHolder Enabled Func.
     * @author hmju
     */
    fun onEnabled() {
        mIsExpands = true

//        if (mClRoot != null) {
//            mClRoot?.layoutParams = mLayoutParams
//        }
    }

    /**
     * ParallaxViewHolder Disabled Func.
     * @author hmju
     */
    fun onDisabled() {
        mIsExpands = false
        // TODO 여기가 뷰를 비활성화 하는 부분인데 여기서 빠르게 스크롤시 생기는 약간의 오차를 해결 해야함.
    }

    /**
     * RecyclerView Scroll Changed Listener CallBack.
     *
     * @author hmju
     */
    override fun onScrollChanged() {
        if (mIsExpands) {
            val view: View = mViewGroup.getChildAt(mViewGroup.indexOfChild(mRootView))
            // Child View Middle Pos get.
            val current: Double = (view.bottom + view.top).toDouble() / 2f
            Log.d(TAG, "View Y\t$current")
            // 맨아래 기준 -> 맨 아래 시작 지점 부터 끝나는 지점에서만 이벤트 발생.
            if (current in mActionEndPos..mActionStartPos) {
                // Percentage Calculate.. 0.0 ~ 1.0
                val percent: Double =
                    abs(current - mActionStartPos) / abs(mActionStartPos - mActionEndPos)
                Log.d(TAG, "Percent\t$percent")
                // Max Height - Min Height (Dpi) 에 대한 값 * Percentage. View 의 최소 높이값 더하기.
                // Tip Double 에서 Int 로 변환 되기 때문에 오차 발생하므로 올림으로 계산함.
                val height: Int = ceil((mCalculation * percent) + mMinHeight).toInt()
                Log.d(TAG, "Height\t$height")
                if (mLayoutParams.height != height) {
                    mLayoutParams.height = height
                    mClRoot?.layoutParams = mLayoutParams
                }

                // Bind Alpha
                bindAlpha(percent)
            }
        }
    }

    /**
     * Bind Alpha
     * 0.0 -> 0.99
     *
     * @author hmju
     */
    private fun bindAlpha(percent: Double) {
        // 가운데 텍스트 알파값 셋팅. 1.0 -> 0.0
        mTvCenterTitle?.alpha = (1f - percent).toFloat()

        // 아래 레이아웃 알파값 셋팅.
        mClBottom?.alpha = percent.toFloat()
    }

    /**
     * String -> Dp Cast Func
     * @param ctx Context
     * @param dp 변환 하고 싶은 Dpi 숫자.
     * @author hmju
     */
    private fun castStringToDp(@NonNull ctx: Context, dp: String): Float {
        dp.replace("dp", "")
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            ctx.resources.displayMetrics
        )
    }
}
