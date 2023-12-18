package com.example.matrixapplication.ui

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixapplication.R
import com.example.matrixapplication.databinding.RecycleViewLotofTextviewItemBinding
import com.example.matrixapplication.domain.InputMatrix
import com.example.matrixapplication.ui.PrimaryRVAdaptor.ConstraintRowHolder
import java.lang.StringBuilder

private const val NUMBER_SEPARATOR = " "
private val BACKGROUND_COLOR = 0xFFFFFFFF.toInt()
private val FONT_COLOR = 0xFF000000.toInt()
private const val GROUP_SIZE = 5

private const val TAG = "Update Matrix Functions"

private fun SpannableStringBuilder.appendAndSpan(
    what: CharSequence,
    spanStyle: Any,
    spannableFlag: Int = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
): SpannableStringBuilder = this.apply {
    val start = length
    append(what)




    setSpan(spanStyle, start, length, spannableFlag)
}

fun setSpannableString(matrix: IntArray): CharSequence {
    val tempSeparator = 'S'

    val rawString = matrix.joinToString(NUMBER_SEPARATOR) {
        val spStr = SpannableString(it.toString())
        spStr.setSpan(ForegroundColorSpan(FONT_COLOR),0,it.toString().length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        spStr.padStart(5,tempSeparator)
//        it.toString().padStart(5, tempSeparator)
    }
    val sb = SpannableStringBuilder(rawString)
    var p1 = 0 // [p1, p2] s
    var p2 = 0 // [p2, p1] digits
    val backgroundColorSpannable = ForegroundColorSpan(BACKGROUND_COLOR)
    val foregroundColorSpan = ForegroundColorSpan(FONT_COLOR)


    rawString.forEachIndexed { index, c ->
        if (c == tempSeparator) {
            if (p2 >= p1) {
                p2++
            } else {
                // set black color
                sb.setSpan(ForegroundColorSpan(FONT_COLOR), p2, p1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                p2 = p1
            }
        } else {
            if (p2 <= p1) {
                p1++
            } else {
                // set white color
                sb.setSpan(ForegroundColorSpan(BACKGROUND_COLOR), p1, p2, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                p1 = p2
            }
        }

    }


    return sb


//
//

//    val filtered = sb.filter { it == tempSeparator }

    val sb2 = SpannableStringBuilder()

    val coloredSequence = SpannableString(tempSeparator as String)

    val backgroundColorSpan = ForegroundColorSpan(BACKGROUND_COLOR)
    coloredSequence.setSpan(
        backgroundColorSpan,
        0,
        coloredSequence.length,
        Spannable.SPAN_INCLUSIVE_INCLUSIVE
    )
    val blackColorForegroundColorSpan = ForegroundColorSpan(FONT_COLOR)


//    sb2.
    matrix.forEachIndexed { index, i ->
        if (index > 0) {
            sb2.append(NUMBER_SEPARATOR)
        }
        val elementAsString = i.toString()
        var count = 5 - elementAsString.length
//        val startIndexS = sb2.length

        val strBuild = StringBuilder()
        while (count-- > 0) {
//            sb2.append(coloredSequence)
        }
        sb2.appendAndSpan(
            strBuild.toString(),
            backgroundColorSpan,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        sb2.appendAndSpan(
            elementAsString,
            blackColorForegroundColorSpan,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )


//        val startIndex = sb2.length
//
//        sb2.append(elementAsString)
//        sb2.setSpan(
//            blackColorForegroundColorSpan,
//            startIndex,
//            sb2.length,
//            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
//        )

//            .let {
//            SpannableString(it).apply {
//                setSpan(
//                    blackColorForegroundColorSpan,
//                    0,
//                    it.length,
//                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//                )
//            } )

    }
    return sb2
}

private class TextViewImplementation(private val binding: RecycleViewLotofTextviewItemBinding) :
    ConstraintRowHolder(view = binding.root) {

    override fun bind(array: IntArray) {
        binding.recycleViewTextviewItem.text = setSpannableString(array)
    }
}

private val factory = object : ConstraintRowHolder.Factory {
    override fun create(view: ViewGroup, viewType: Int): ConstraintRowHolder =
        TextViewImplementation(
            RecycleViewLotofTextviewItemBinding.inflate(
                LayoutInflater.from(view.context),
                view,
                false
            )
        )
}


internal fun updateMatrixTableView(
    matrix: InputMatrix,
    recyclerView: RecyclerView,
    context: Context
) {

    recyclerView.removeAllViews()
//    val rVAdapter = RVAdapter(matrix)


    val rVAdapter = PrimaryRVAdaptor(matrix, factory = factory)
    val linearLayoutManager = LinearLayoutManager(context)
    recyclerView.layoutManager = linearLayoutManager
    recyclerView.adapter = rVAdapter

}

fun updateMatrixTableView(
    matrix: InputMatrix,
    matrixTable: TableLayout,
    context: Context
) {
    matrixTable.removeAllViews()

    for (i in 0 until matrix.rows) {
        val tableRow = TableRow(context)
        tableRow.id = i
        for (j in 0 until matrix.columns) {
            val textView = TextView(context)
            textView.text = matrix.getData()[i][j].toString()
            textView.setPadding(20, 10, 10, 10)
            textView.setBackgroundResource(R.drawable.border)
            tableRow.addView(textView)
            textView.id = (j * matrix.columns + i)

        }
        matrixTable.addView(tableRow)
    }
}