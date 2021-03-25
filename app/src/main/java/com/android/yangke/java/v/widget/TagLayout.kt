package com.android.yangke.java.v.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.android.yangke.java.R
import java.util.*

/**
 * author : yangke on 2021/2/9
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 标签视图；类似搜索标签、单选；
 */
class TagLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val mChildren: MutableList<IntArray> = ArrayList()
    private val mMapView = HashMap<Int, TextView>()         //存放选中的View
    private val mMapData: MutableList<TagVo> = ArrayList() //存放选中View对应的数据
    private var mIndexPre = 0                               //记录上一次点击的位置
    private var mNormalColor = "#a5a7b7"                    //未选中字体颜色
    private var mSelectedColor = "#008577"                  //选择字体颜色
    private var mNormalBg = R.drawable.tag_bg_normal          //未选中背景
    private var mSelectedBg = R.drawable.tag_bg_highlight   //选择背景
    private var mSingleLis: SingleSelectListener? = null      //单选择监听器
    private var mMultipleLis: MultipleSelectListener? = null//多选择监听器
    private var isMultipleChoice: Boolean = false           //是否是多选

    /**
     * 配置未选中/选中 -- 文本颜色/背景
     */
    fun withDefault(normalColor: String, selectedColor: String, normalBg: Int, selectedBg: Int) {
        mNormalColor = normalColor
        mSelectedColor = selectedColor
        mNormalBg = normalBg
        mSelectedBg = selectedBg
    }

    /**
     * 配置多选
     */
    fun withMultipleChoice(isMultiple: Boolean): TagLayout {
        isMultipleChoice = isMultiple
        return this
    }

    /**
     * 添加选择监听
     */
    fun addSingleSelectListener(l: SingleSelectListener): TagLayout {
        mSingleLis = l
        return this
    }

    fun addMultipleListener(l: MultipleSelectListener): TagLayout {
        mMultipleLis = l
        return this
    }

    /**
     * 多选 选中数据
     */
    fun getMultipleData() = mMapData

    /**
     * 应用
     */
    @SuppressLint("CutPasteId")
    fun apply(list: ArrayList<TagVo>) {
        for (index in 0 until list.size) {
            val sonItemView: View = View.inflate(context, R.layout.tag_search_history, null)
            val tagView = sonItemView.findViewById<TextView>(R.id.tag)
            mMapView[index] = tagView

            tagView.setOnClickListener {
                if (isMultipleChoice) { //多选
                    configMultipleChoice(list, index)
                } else {               //单选
                    configSingleChoice(index)
                }
            }
            tagView.text = list[index].tag
            addView(sonItemView)
        }
    }

    private fun configMultipleChoice(
            list: ArrayList<TagVo>,
            index: Int
    ) {
        var currentIsSelected = !list[index].isSelected
        if (currentIsSelected) {
            mMapData.add(list[index])
        } else {
            mMapData.remove(list[index])
        }

        if (list[index].isSelected) {
            mMapView[index]?.setTextColor(Color.parseColor(mNormalColor))
            mMapView[index]?.setBackgroundResource(mNormalBg)
            list[index].isSelected = false
        } else {
            mMapView[index]?.setTextColor(Color.parseColor(mSelectedColor))
            mMapView[index]?.setBackgroundResource(mSelectedBg)
            list[index].isSelected = true
        }
        mMultipleLis?.onCheck(mMapData)
    }

    private fun configSingleChoice(index: Int) {
        if (index == mIndexPre) {
            return
        }

        mMapView[mIndexPre]?.setTextColor(Color.parseColor(mNormalColor))
        mMapView[mIndexPre]?.setBackgroundResource(mNormalBg)

        mMapView[index]?.setTextColor(Color.parseColor(mSelectedColor))
        mMapView[index]?.setBackgroundResource(mSelectedBg)

        mSingleLis?.onCheck(index)
        mIndexPre = index
    }

    interface SingleSelectListener {
        fun onCheck(pos: Int)
    }

    interface MultipleSelectListener {
        fun onCheck(list: MutableList<TagVo>)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        var left = 0 // 当前的左边距离
        var top = 0 // 当前的上边距离
        var totalHeight = 0 // WRAP_CONTENT时控件总高度
        var totalWidth = 0  // WRAP_CONTENT时控件总宽度
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val params = child.layoutParams as LayoutParams
            if (i == 0) {  // 第一行的高度
                totalHeight = params.topMargin + child.measuredHeight + params.bottomMargin
            }
            if (left + params.leftMargin + child.measuredWidth + params.rightMargin > measuredWidth) { // 换行
                left = 0
                top += params.topMargin + child.measuredHeight + params.bottomMargin // 每个TextView的高度都一样，随便取一个都行
                totalHeight += params.topMargin + child.measuredHeight + params.bottomMargin
            }
            val element = intArrayOf(
                    left + params.leftMargin,
                    top + params.topMargin,
                    left + params.leftMargin + child.measuredWidth,
                    top + params.topMargin + child.measuredHeight
            )
            mChildren.add(element)
            left += params.leftMargin + child.measuredWidth + params.rightMargin
            if (left > totalWidth) { // 当宽度为WRAP_CONTENT时，取宽度最大的一行
                totalWidth = left
            }
        }
        val height =
                if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) MeasureSpec.getSize(
                        heightMeasureSpec
                ) else totalHeight
        val width =
                if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) MeasureSpec.getSize(
                        widthMeasureSpec
                ) else totalWidth
        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            val position = mChildren[i]
            getChildAt(i).layout(position[0], position[1], position[2], position[3])
        }
    }
}

class TagVo(isSelected: Boolean, tag: String) {
    var isSelected: Boolean = isSelected
    var tag: String = tag
}