package com.hmju.kotlin.activity

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hmju.kotlin.R
import com.hmju.kotlin.utils.Logger
import java.util.*
import kotlin.collections.ArrayList

/**
 * Description : 검색 키워드 페이지
 *
 * Created by QTZZ772 on 2020-05-13
 */
class SearchActivity : BaseActivity() {

    private val mEtSearch: EditText by lazy {
        findViewById<EditText>(R.id.et_search)
    }

    private val mRvContents: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_contents)
    }

    private val mAdapter: SearchAdapter by lazy {
        SearchAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        // Dummy Data Set.
        dummyData()

        // RecyclerView init..
        mRvContents.layoutManager = LinearLayoutManager(mContext)
        mRvContents.adapter = mAdapter

        // add Listener
        mEtSearch.removeTextChangedListener(mEditListener)
        mEtSearch.addTextChangedListener(mEditListener)
    }

    private fun dummyData() {

        val dummyList: ArrayList<String> = ArrayList<String>()
        dummyList.add("아")
        dummyList.add("리")
        dummyList.add("아이오")
        dummyList.add("아에에")
        dummyList.add("아보보")
        dummyList.add("아다라")
        dummyList.add("카카카")
        dummyList.add("바바바")
        dummyList.add("다다다")
        dummyList.add("아아")
        dummyList.add("아아")
        dummyList.add("아아")
        dummyList.add("아아")
        dummyList.add("아아")
        dummyList.add("아아1")
        dummyList.add("아1아")
        dummyList.add("아아1")
        dummyList.add("아아7")
        dummyList.add("아아17")
        dummyList.add("아아7")
        dummyList.add("아아6")
        dummyList.add("아아5")
        dummyList.add("아아4")
        dummyList.add("아아3")
        dummyList.add("아아2")
        dummyList.add("아아1")
        dummyList.add("아1보1")
        dummyList.add("아1아아아")
        dummyList.add("ㅂㅂㅂㅂㅂ")


        mAdapter.setData(dummyList)
    }

    // EditText 문자 변경에 대한 Listener
    private val mEditListener = object : TextWatcher {

        override fun beforeTextChanged(str: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(str: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(str: Editable?) {
            Logger.d("afterTextChanged\t $str")
            mAdapter.filter.filter(str?.trim())
        }
    }

    open class SearchViewHolder(val mRootView: View) : RecyclerView.ViewHolder(mRootView) {

        private val mTvTitle: TextView by lazy {
            mRootView.findViewById<TextView>(R.id.tv_search)
        }


        companion object {

            fun newInstance(parent: ViewGroup): SearchViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_search, parent, false)
                return SearchViewHolder(view)
            }
        }

        fun onBindView(keyword: String, data: String) {
            if (data.isEmpty()) return

            if (data.contains(keyword)) {
                Logger.d("onBindView Contain\t$data")
                val builder = SpannableStringBuilder(data)
                val startIndex: Int = data.indexOf(keyword)

                builder.setSpan(
                    ForegroundColorSpan(Color.BLACK), startIndex, keyword.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                mTvTitle.text = builder
            } else {
                Logger.d("onBindView Not Contain\t$data")
                mTvTitle.text = data
            }
        }
    }

    private inner class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>(), Filterable {

        private var mUnFilterList: ArrayList<String>? = null
        private var mFilteredList: ArrayList<String>? = null
        private var mKeyword: String = ""

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
            return SearchViewHolder.newInstance(parent)
        }

        fun setData(dataList: ArrayList<String>) {
            mUnFilterList = dataList
            mFilteredList = dataList
        }

        override fun getItemCount(): Int {
            return if (mFilteredList == null) {
                0
            } else {
                mFilteredList!!.size
            }
        }

        override fun onBindViewHolder(holder: SearchViewHolder, pos: Int) {
            if (pos < itemCount) {
                holder.onBindView(mKeyword, mFilteredList!![pos])
            }
        }

        override fun getFilter(): Filter {
            return object : Filter() {
                override fun performFiltering(constraint: CharSequence?): FilterResults {
                    Logger.d("performFiltering\t${constraint.toString()}")
                    val str: String = constraint.toString()
                    if (str.isEmpty()) {
//                        mFilteredList = mUnFilterList
                        mFilteredList?.clear()
                    } else {
                        val filteringList = ArrayList<String>()

                        mUnFilterList?.forEach {
                            if (it.toLowerCase(Locale.ROOT)
                                    .contains(str.toLowerCase(Locale.ROOT))
                            ) {
                                filteringList.add(it)
                            }
                        }
                        mFilteredList = filteringList
                    }
                    val filterResults = FilterResults()
                    filterResults.values = mFilteredList
                    mKeyword = str
                    return filterResults
                }

                override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                    Logger.d("publishResults\t${constraint.toString()}")
                    mFilteredList = results?.values as ArrayList<String>
                    notifyDataSetChanged()
                }
            }
        }


    }
}