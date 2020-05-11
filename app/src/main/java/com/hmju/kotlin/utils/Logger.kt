package com.hmju.kotlin.utils

import android.util.Log

/**
 * Description: 로그 클래스.
 *
 * Created by juhongmin on 2020/05/11
 */
class Logger {
    companion object {
        val TAG = "JLogger"

        fun d(msg: String) {
            val ste = Thread.currentThread().stackTrace[4]
            val sb = StringBuilder()
            sb.append("[")
//            sb.append(ste.fileName.replace(".kt", ""))
//            sb.append("::")
            sb.append(ste.methodName)
            sb.append("]")
            Log.d("$TAG:$sb", msg)
        }

        fun e(msg: String) {
            val ste = Thread.currentThread().stackTrace[4]
            val sb = StringBuilder()
            sb.append("[")
            sb.append(ste.fileName.replace(".kt", ""))
            sb.append("::")
            sb.append(ste.methodName)
            sb.append("]")
            Log.e("$TAG:$sb", msg)
        }

        @JvmStatic
        fun D(msg: String) {
            val ste = Thread.currentThread().stackTrace[4]
            val sb = StringBuilder()
            sb.append("[")
//            sb.append(ste.fileName.replace(".kt", ""))
//            sb.append("::")
            sb.append(ste.methodName)
            sb.append("]")
            Log.d("$TAG:$sb", msg)
        }
    }
}