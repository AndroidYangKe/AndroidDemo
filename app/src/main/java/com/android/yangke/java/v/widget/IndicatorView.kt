package com.android.yangke.java.v.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.yangke.java.R
import com.android.yangke.java.m.utils.DisplayUtil

/**
 * author : yangke on 2021/2/4
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 横向指示器View，大于四条数据可支持滚动；用法： IndicatorView.withBarView().apply(RecyclerView)
 */
class IndicatorView(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {

    class OrderVo(var state: String, var id: Int, var stateStr: String)

    private lateinit var mData: java.util.ArrayList<OrderVo>
    private val mid = 4               //标识是否可滚动的size
    private var defaultItemWidth = 90 //View可滚动时默认大小

    fun withData(data: ArrayList<OrderVo>): IndicatorView {
        layoutManager = object : LinearLayoutManager(context, RecyclerView.HORIZONTAL, false) {
            override fun canScrollHorizontally(): Boolean = data.size > mid
        }
        mData = data
        return this
    }

    fun withItemWidth(width: Int): IndicatorView {
        defaultItemWidth = width
        return this
    }

    fun apply() {
        adapter = MyAdapter(context, mData)
    }

    inner class MyAdapter(var ctx: Context, var data: ArrayList<OrderVo>) :
            RecyclerView.Adapter<MyViewHolder>() {

        private val colorRefuse = Color.parseColor("#4DFEF5F1") //审批拒绝验颜色
        private val colorApply = Color.parseColor("#FEF5F1")    //审批中颜色
        private val screenWidth = DisplayUtil.getScreenWidth(ctx)        //屏幕宽度
        private val margin = 32                                           //默认外边距

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(
                    LayoutInflater.from(ctx).inflate(R.layout.item_order_state, parent, false)
            )
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val state = data[position].state
            configSizeView(holder) //配置四个item情况居中均分
            holder.textView.text = data[position].stateStr
            holder.icon.setImageResource(getDrawableId(data[position].state))
            updateLine(state, position, holder) //更新横线
            holder.textView.setTextColor(if (state == "4") colorRefuse else colorApply) //“4” 审批拒绝
        }

        private fun configSizeView(holder: MyViewHolder) {
            val itemWidth: Int =
                    if (data.size <= mid) (screenWidth - margin) / data.size else DisplayUtil.dip2px(
                            ctx,
                            defaultItemWidth.toFloat()
                    )
            holder.itemView.layoutParams = ConstraintLayout.LayoutParams(
                    itemWidth,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }

        private fun updateLine(state: String, position: Int, holder: MyViewHolder) {
            //配置线颜色
            if (state == "4") {
                holder.lineRight.setBackgroundColor(colorRefuse)
                holder.lineLeft.setBackgroundColor(colorRefuse)
            } else if (state == "3") {
                holder.lineRight.setBackgroundColor(colorRefuse)
                holder.lineLeft.setBackgroundColor(colorApply)
            } else {
                holder.lineLeft.setBackgroundColor(colorApply)
                holder.lineRight.setBackgroundColor(colorApply)
            }

            //配置线是否展示
            if (position == data.size - 1) { //最后一个
                holder.lineRight.visibility = View.GONE
                holder.lineLeft.visibility = View.VISIBLE
            } else if (position == 0) {        //第一个
                holder.lineLeft.visibility = View.GONE
            } else {                           //中间项
                holder.lineLeft.visibility = View.VISIBLE
                holder.lineRight.visibility = View.VISIBLE
            }
        }

        override fun getItemCount(): Int = data.size

        private fun getDrawableId(status: String): Int {
            return when {
                "1".equals(status, ignoreCase = true) -> R.drawable.order_ic_1//申请中
                "2".equals(status, ignoreCase = true) -> R.drawable.order_ic_2//申请通过
                "3".equals(status, ignoreCase = true) -> R.drawable.order_ic_3//申请拒绝
                else -> R.drawable.order_ic_4                                 //待提交资料
            }
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.text_view)
        var icon: ImageView = view.findViewById(R.id.icon)
        var lineRight: ImageView = view.findViewById(R.id.line_right)
        var lineLeft: ImageView = view.findViewById(R.id.line_left)
    }
}