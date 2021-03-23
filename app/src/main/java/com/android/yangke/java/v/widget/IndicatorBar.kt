package com.android.yangke.java.v.widget

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

/**
 * author : yangke on 2021/2/8
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 金刚位指示器
 */
class IndicatorBar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private var mDefaultFrontBGWidth: Float = 15f //默认高亮指示器宽度
    private lateinit var mBarFront: ImageView      //高亮指示View
    private lateinit var mBarBG: ImageView         //背景指示View
    private var mRadius = 5f                       //默认背景圆角
    private var mStartColor: String = "#39B9FF"
    private var mEndColor: String = "#4080E8"

    fun initDefaultValues(
            radius: Float,
            startColor: String,
            endColor: String,
            width: Float
    ): IndicatorBar {
        mRadius = radius
        mStartColor = startColor
        mEndColor = endColor
        mDefaultFrontBGWidth = width
        return this
    }

    fun withBarView(): IndicatorBar {
        mBarBG = ImageView(context)
        mBarBG.layoutParams = layoutParams
        mBarBG.background = getConnerDrawable(Color.parseColor("#EEEEEE"), mRadius)

        mBarFront = ImageView(context)
        mBarFront.layoutParams =
                LayoutParams(dp2px(mDefaultFrontBGWidth), LayoutParams.MATCH_PARENT)
        val radialDrawable = GradientDrawable()
        val colors = intArrayOf(
                Color.parseColor(mStartColor),
                Color.parseColor(mEndColor)
        )
        radialDrawable.colors = colors
        radialDrawable.cornerRadius = mRadius
        radialDrawable.orientation = GradientDrawable.Orientation.LEFT_RIGHT
        mBarFront.background = radialDrawable

        addView(mBarBG)
        addView(mBarFront)
        return this
    }

    fun apply(rcy: RecyclerView) {
        rcy.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //当前RcyclerView显示区域的高度。水平列表屏幕从左侧到右侧显示范围
                val extent = recyclerView.computeHorizontalScrollExtent()
                //整体的高度，注意是整体，包括在显示区域之外的。
                val range = recyclerView.computeHorizontalScrollRange()
                //已经滚动的距离，为0时表示已处于顶部。
                val offset = recyclerView.computeHorizontalScrollOffset()
                if (extent == range) {
                    mBarFront.visibility = View.GONE
                } else {
                    mBarFront.visibility = View.VISIBLE
                    //计算出溢出部分的宽度，即屏幕外剩下的宽度
                    val maxEndX = (range - extent).toFloat()
                    //计算比例
                    val proportion = offset / maxEndX
                    val layoutWidth = mBarBG.width
                    val indicatorViewWidth: Int = mBarFront.width
                    //可滑动的距离
                    val scrollableDistance = layoutWidth - indicatorViewWidth
                    //设置滚动条移动
                    mBarFront.translationX = scrollableDistance * proportion
                }
            }
        })
    }

    /**
     * 创建Shape 圆角矩形
     *
     * @param color  背景颜色
     * @param radius 圆角半径
     */
    private fun getConnerDrawable(color: Int, radius: Float): Drawable {
        val drawable = GradientDrawable()
        drawable.cornerRadius = radius
        drawable.setColor(color)
        return drawable
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private fun dp2px(dpValue: Float): Int {
        return ((0.5f + dpValue * Resources.getSystem().displayMetrics.density).toInt())
    }

}