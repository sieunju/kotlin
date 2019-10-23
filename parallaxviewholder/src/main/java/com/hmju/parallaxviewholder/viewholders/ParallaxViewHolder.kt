package com.hmju.parallaxviewholder.viewholders

import android.app.Activity
import android.graphics.Point
import android.util.Log
import android.util.SparseIntArray
import android.view.*
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.hmju.parallaxviewholder.R
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

    // 전체 ViewHolder 높이에 대한 레이아웃.
    private val mClRoot: ConstraintLayout by lazy { mRootView.findViewById<ConstraintLayout>(R.id.cl_root) }
    // 가운데 영역
    private val mClCenter: ConstraintLayout by lazy { mRootView.findViewById<ConstraintLayout>(R.id.cl_center) }
    // 가운데 글씨 TextView
    private val mTvCenterTitle: TextView by lazy { mRootView.findViewById<TextView>(R.id.tv_center_title) }
    // 아래 글씨 표시 및 전체 알파값에 대한 레이아웃.
    private val mClBottom: ConstraintLayout by lazy { mRootView.findViewById<ConstraintLayout>(R.id.cl_bottom) }
    // 아래 글씨 TextView
    private val mTvBottomTitle: TextView by lazy { mRootView.findViewById<TextView>(R.id.tv_bottom_title) }
    // Child ViewHolder Max Height.
    private val mMaxHeight: Float by lazy { mContext.resources.getDimension(R.dimen.parallax_height_max) }
    // Child ViewHolder Min Height.
    private val mMinHeight: Float by lazy { mContext.resources.getDimension(R.dimen.parallax_height_min) }

    // ViewHolder 높이값을 세팅 해주는 LayoutParams
    private val mLayoutParams: ConstraintLayout.LayoutParams by lazy {
        ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private var mDisplayY: Int = 0                  // Device Max Height
    private var mActionStartPos: Double = 0.0       // Action Start Position
    private var mActionEndPos: Double = 0.0         // Action End Position
    private var mCalculation: Float = 0f            // MaxHeight - MinHeight Value (Dpi)
    private var mIsExpands: Boolean = false         // Child ViewHolder Action Status

    private val mViewInfoList: SparseIntArray by lazy { SparseIntArray() }

    companion object {

        fun newInstance(parent: ViewGroup): ParallaxViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_parallax, parent, false)
            return ParallaxViewHolder(view, parent)
        }
    }

    /**
     * init..
     * @author hmju
     */
    init {
        // [s] init Listener
        mViewGroup.viewTreeObserver.removeOnScrollChangedListener { this }
        mViewGroup.viewTreeObserver.addOnScrollChangedListener(this)
        // [e] init Listener

        // [s] init Size
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
        // [e] init Size
    }

    /**
     * Bind Data View
     * @author hmju
     */
    override fun onBindView(pos: Int, data: ParallaxStruct) {
        bindView(data)
    }

    private fun bindView(data: ParallaxStruct) {
        mTvCenterTitle.text = data.title
        mTvBottomTitle.text = String.format("Chapter%s",data.title)
    }

    /**
     * ParallaxViewHolder Enabled Func.
     * @author hmju
     */
    fun onEnabled() {
        mIsExpands = true

        val viewHeight: Int = mViewInfoList.get(adapterPosition, -1)

        if (viewHeight != -1) {
            mLayoutParams.height = viewHeight
            mClRoot.layoutParams = mLayoutParams

            // 줄어든 상태.
            if (viewHeight == mMinHeight.toInt()) {
                initAlpha(true)
            }
            // 늘어난 상태.
            else {
                initAlpha(false)
            }
        }
        // 처음으로 해당 포지션의 ViewHolder 가 활성화 되었을때. 높이값 초기화.
        else {
            mLayoutParams.height = mMinHeight.toInt()
            mClRoot.layoutParams = mLayoutParams

            // 줄어든 상태
            initAlpha(true)
        }
    }

    /**
     * ParallaxViewHolder Disabled Func.
     * @author hmju
     */
    fun onDisabled() {
        mIsExpands = false
        // 해당 포지션의 ViewHolder 가 비활성화 될때 해당 높이값을 Min,Max Height 중 근처 값을 구해서 저장한다.
        val nearHeight: Int = getNearHeight()
        // 줄어든 상태 0.0
        if (nearHeight == mMinHeight.toInt()) {
            initAlpha(true)
        }
        // 늘어난 상태 1.0
        else {
            initAlpha(false)
        }

        mViewInfoList.put(adapterPosition, nearHeight)
    }

    /**
     * View Alpha 값 상태별로 초기화.
     * @param isStart true -> 초기 상태, false -> 늘어난 상태.
     * @author hmju
     */
    private fun initAlpha(isStart: Boolean) {
        // 초기 상태 -> 줄어져 있는 상태.
        if (isStart) {
            mClCenter.alpha = 1F
            mClBottom.alpha = 0F
        }
        // 마지막 상태 -> 늘어난 상태.
        else {
            mClCenter.alpha = 0F
            mClBottom.alpha = 1F
        }
    }

    /**
     * MinHeight Or MaxHeight 값중에서 가장 가까운 값을 구하는 함수.
     * @author hmju
     */
    private fun getNearHeight(): Int {
        var near = 0
        val viewBottom: Int = mClRoot.bottom    // 최대값 최소값 기준은 View Bottom 을 기준으로 한다.
        var min: Int = Int.MAX_VALUE
        // 현재 View Height 과 최소 높이값 및 최대값의 차이로 해당 근처 값을 추출하는 로직.
        var tmpValue: Int = abs(mMinHeight - viewBottom).toInt()
        if (min > tmpValue) {
            min = tmpValue
            near = mMinHeight.toInt()
        }

        tmpValue = abs(mMaxHeight - viewBottom).toInt()
        if (min > tmpValue) {
            near = mMaxHeight.toInt()
        }

        Log.d(TAG, "getNearHeight\t$near")
        return near
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
            // 맨아래 기준 -> 맨 아래 시작 지점 부터 끝나는 지점에서만 이벤트 발생.
            if (current in mActionEndPos..mActionStartPos) {
                // Percentage Calculate.. 0.0 ~ 1.0
                val percent: Double =
                    abs(current - mActionStartPos) / abs(mActionStartPos - mActionEndPos)
                Log.d(TAG, "Percent\t$percent")

                // Bind Height
                bindHeight(percent)

                // Bind Alpha
                bindAlpha(percent)
            }
        }
    }

    /**
     * Bind Height
     * 0.0 -> 1.0
     * 아래에서 위 0.0 ~ 1.0
     *
     * @author hmju
     */
    private fun bindHeight(percent: Double) {
        // Max Height - Min Height (Dpi) 에 대한 값 * Percentage. View 의 최소 높이값 더하기.
        // Tip Double 에서 Int 로 변환 되기 때문에 오차 발생하므로 올림으로 계산함.
        val height: Int = ceil((mCalculation * percent) + mMinHeight).toInt()
        if (mLayoutParams.height != height) {
            mLayoutParams.height = height
            mClRoot.layoutParams = mLayoutParams
        }
    }

    /**
     * Bind Alpha
     * 0.0 -> 0.99
     * 아래에서 위 0.0 ~ 1.0
     *
     * @author hmju
     */
    private fun bindAlpha(percent: Double) {
        if (0.0 <= percent && percent < 0.5) {
            val tmpAlpha: Double = 1.0 - percent * 2
            mClCenter.alpha = tmpAlpha.toFloat()
        }

        mClBottom.alpha = percent.toFloat()
    }
}
