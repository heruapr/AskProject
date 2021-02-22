package com.example.askproject.presentation.extension

import android.graphics.Typeface
import android.net.Uri
import android.os.FileUtils
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.view.drawToBitmap
import com.example.askproject.R
import java.math.BigInteger
import java.text.DecimalFormat


fun ViewGroup.inflate(@LayoutRes resourceId: Int) =
    LayoutInflater.from(context).inflate(resourceId, this, false)
        ?: throw IllegalStateException("Failed to inflate resource")

fun TextView.addLink(value: String, links: Map<String, () -> Unit>) {
    val joinString = if (links.size == 1) {
        String.format(value, links.keys.first())
    } else {
        String.format(value, links.keys.firstOrNull(), links.keys.lastOrNull())
    }

    val spannable = SpannableString(joinString)

    for (link in links) {
        val start = spannable.indexOf(link.key)
        val end = start + link.key.length

        spannable.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                link.value.invoke()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = this@addLink.currentTextColor
            }
        }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    text = spannable
    movementMethod = LinkMovementMethod.getInstance()
}

fun EditText.applyNumberFormat(onChange: (BigInteger) -> Unit) {
    setText("0")
    addTextChangedListener(object : TextWatcher {
        val current = ""

        override fun afterTextChanged(s: Editable?) {
            onChange(s.toString().replace("\\D".toRegex(), "").ifBlank { "0" }.toBigInteger())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s != current) {
                removeTextChangedListener(this)
                val value = s.toString().replace("\\D".toRegex(), "").ifBlank { "0" }.toBigInteger()
                val result = value.toString()
                setText(result)
                setSelection(result.length)
                addTextChangedListener(this)
            }
        }
    })
}

//fun ShimmerFrameLayout.onLoading(loading: Boolean) {
//    isVisible = loading
//    if (loading) startShimmer() else stopShimmer()
//}
