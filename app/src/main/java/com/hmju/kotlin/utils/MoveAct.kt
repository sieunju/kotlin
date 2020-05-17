package com.hmju.kotlin.utils

import android.app.Activity
import android.content.Intent
import com.hmju.kotlin.activity.ParallaxActivity
import com.hmju.kotlin.activity.SearchActivity
import com.hmju.kotlin.activity.ViewPager2Activity

/**
 * Description : Activity Move 공통 클래스
 *
 * Created by QTZZ772 on 2020-05-12
 */
class MoveAct {
    companion object {
        val instance: MoveAct by lazy { MoveAct() }
    }

    /**
     * 점점 펼쳐지는 ViewHolder 페이지
     * @param act Activity
     * @author hmju
     */
    fun moveParallaxAct(act: Activity) {
        val intent: Intent = Intent(act, ParallaxActivity::class.java)
        act.startActivity(intent)
    }

    /**
     * ViewPager2 페이지
     * @param act Activity
     * @author hmju
     */
    fun moveViewPager2Act(act: Activity) {
        val intent: Intent = Intent(act, ViewPager2Activity::class.java)
        act.startActivity(intent)
    }

    /**
     * 검색 키워드 페이지
     * @param act Activity
     * @author hmju
     */
    fun moveSearchAct(act: Activity) {
        val intent: Intent = Intent(act, SearchActivity::class.java)
        act.startActivity(intent)
    }

}