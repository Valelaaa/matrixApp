package com.example.matrixapplication.ui.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.compose.ui.graphics.PathEffect
import java.util.regex.Pattern


private const val NUMBER_SEPARATOR = " "

private const val HORIZONTAL_MARGIN = 20
private const val VERTICAL_MARGIN = 20

class FixedTextView : View {
    private val paint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = 0xFF000000.toInt()
            textSize = 32F
            this.strokeWidth = 2F
            textAlign = Paint.Align.LEFT
        }
    }
    private var textLine: String = ""
    private val textBounds = Rect()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun setLine(input: IntArray) {
        textLine = input.joinToString(separator = NUMBER_SEPARATOR) {
            it.toString().padStart(5, 'S')
        }
        requestLayout()
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val mode = MeasureSpec.getMode(widthMeasureSpec)
        val size = MeasureSpec.getSize(widthMeasureSpec)

        calculateTextBounds()

        super.onMeasure(
            MeasureSpec.makeMeasureSpec(
                textBounds.width() + 2 * HORIZONTAL_MARGIN,
                MeasureSpec.EXACTLY
            ),
            MeasureSpec.makeMeasureSpec(
                textBounds.height() + 2 * VERTICAL_MARGIN,
                MeasureSpec.EXACTLY
            ),
        )
    }

    private fun calculateTextBounds() {
        paint.getTextBounds(textLine, 0, textLine.length - 1, textBounds)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawText(
            textLine,
            HORIZONTAL_MARGIN.toFloat(),
            (height - textBounds.height()) / 1f,
            paint
        )
    }
}