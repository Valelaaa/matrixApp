package com.example.matrixapplication.ui.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import kotlin.math.max

private const val NUMBER_SEPARATOR = " "
private const val NUMBER_SYMBOL = "9"

private const val HORIZONTAL_MARGIN = 20
private const val VERTICAL_MARGIN = 20
private const val GROUP_COUNT = 6

class FixedTextView : View {
    private val paint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = 0xFF000000.toInt()
            textSize = 32F
            this.strokeWidth = 2F
            textAlign = Paint.Align.LEFT
        }
    }
    private var input: IntArray = IntArray(0)

    private val rect = Rect()

    private val separatorWidth: Int by lazy {
        paint.getTextBounds(NUMBER_SEPARATOR, 0, NUMBER_SEPARATOR.length, rect)
        rect.width()
    }

    private val symbolWidth: Int by lazy {
        paint.getTextBounds(NUMBER_SYMBOL, 0, NUMBER_SYMBOL.length, rect)
        rect.width()
    }

    private val symbolHeight: Int by lazy {
        paint.getTextBounds(NUMBER_SYMBOL, 0, NUMBER_SYMBOL.length, rect)
        val symbolHeight = rect.height()
        paint.getTextBounds(NUMBER_SEPARATOR, 0, NUMBER_SEPARATOR.length, rect)
        val separatorHeight = rect.height()
        max(symbolHeight, separatorHeight)
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun setLine(input: IntArray) {
        this.input = input
        requestLayout()
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = calculateBoundsWidth()
        super.onMeasure(
            MeasureSpec.makeMeasureSpec(
                width + 2 * HORIZONTAL_MARGIN,
                MeasureSpec.EXACTLY
            ),
            MeasureSpec.makeMeasureSpec(
                symbolHeight + 2 * VERTICAL_MARGIN,
                MeasureSpec.EXACTLY
            ),
        )
    }

    private fun calculateBoundsWidth(): Int {
        val numberCount = input.size
        val separatorCounts = max(numberCount - 1, 0)
        return numberCount * GROUP_COUNT * symbolWidth + separatorCounts * separatorWidth
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val aInput = this.input
        var leftOffset = 0f
        aInput.forEach { number ->
            val numberAsString = number.toString()
            val emptySymbols = GROUP_COUNT - numberAsString.length
            val offset = if (emptySymbols > 0) emptySymbols * symbolWidth else 0
            canvas.drawText(numberAsString, leftOffset + offset, height / 2f, paint)
            leftOffset += GROUP_COUNT * symbolWidth + separatorWidth
        }
    }
}