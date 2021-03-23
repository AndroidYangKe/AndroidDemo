package com.android.yangke.java.v.widget

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat.getColor
import com.android.yangke.java.R

/**
 * author : yangke on 2021/2/2
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : 自定义协议View; 例：I agree to “Terms and Conditions” & “Privacy Policy”
 */
class PolicyView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    private var mAgreeSpan: SpannableString? = null
    private var mTermSpan: SpannableString? = null
    private var mAndSpan: SpannableString? = null
    private var mPolicySpan: SpannableString? = null

    fun withAgree(str: String = "I agree to ", color: Int = R.color.purple_200): PolicyView {
        mAgreeSpan = SpannableString(str)
        mAgreeSpan?.setSpan(object : UnderlineSpan() {
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = getColor(resources, color, null)
                ds.isUnderlineText = false
            }
        }, 0, str.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return this
    }

    fun withAnd(str: String = " & ", color: Int = R.color.purple_200): PolicyView {
        mAndSpan = SpannableString(str)
        mAndSpan?.setSpan(object : UnderlineSpan() {
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = getColor(resources, color, null)
                ds.isUnderlineText = false
            }
        }, 0, str.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return this
    }

    fun withClickTerms(str: String = "Terms and Conditions", l: TermListener? = null): PolicyView {
        mTermSpan = SpannableString(str)
        mTermSpan?.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                l?.termClick()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = getColor(resources, R.color.black, null)
                ds.isUnderlineText = false
            }
        }, 0, str.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return this
    }

    fun withClickPolicy(str: String = "Privacy Policy", l: PolicyListener? = null): PolicyView {
        mPolicySpan = SpannableString(str)
        mPolicySpan?.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                l?.policyClick()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = getColor(resources, R.color.black, null)
                ds.isUnderlineText = false
            }
        }, 0, str.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return this
    }

    fun apply() {
        val ssb = SpannableStringBuilder()
        ssb.append(mAgreeSpan).append(mTermSpan).append(mAndSpan).append(mPolicySpan)
        text = ssb
        movementMethod = LinkMovementMethod.getInstance()
        highlightColor = Color.TRANSPARENT
    }

    interface TermListener {
        fun termClick()
    }

    interface PolicyListener {
        fun policyClick()
    }
}