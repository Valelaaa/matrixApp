package com.example.matrixapplication.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

private const val DEFAULT_HIGHLIGHT_POSITION_OFF = -1

class PrimaryRVAdaptor(val factory: ConstraintRowHolder.Factory) :
    ListAdapter<IntArray, PrimaryRVAdaptor.ConstraintRowHolder>
        (object :
        DiffUtil.ItemCallback<IntArray>() {
        override fun areItemsTheSame(oldItem: IntArray, newItem: IntArray): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: IntArray, newItem: IntArray): Boolean {
            return oldItem.contentEquals(newItem)
        }
    }) {

    var highLightRowPosition: Int = DEFAULT_HIGHLIGHT_POSITION_OFF
        set(value) {
            val oldPosition = field
            field = value
            if (oldPosition != DEFAULT_HIGHLIGHT_POSITION_OFF)
                notifyItemChanged(oldPosition)
            if (field != DEFAULT_HIGHLIGHT_POSITION_OFF)
                notifyItemChanged(field)
        }

    private var row = -1
    private var col = -1

    fun highLightElementByPosition(row: Int, col: Int) {
//        val oldRow = this.row
//        val oldCol = this.col
//        if (row != oldRow) {
//            this.row = row
//            notifyItemChanged(oldRow)
//        }
//        if (col != oldCol) {
//            this.col = col
//            notifyItemChanged(oldCol)
//        }
        this.row = row
        this.col = col
        notifyDataSetChanged()
    }

    abstract class ConstraintRowHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        abstract fun bind(array: IntArray, position: Int = -1, colPosition: Int = -1)

        interface Factory {
            fun create(view: ViewGroup, viewType: Int): ConstraintRowHolder
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConstraintRowHolder {
        return factory.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: ConstraintRowHolder, position: Int) {
        if (position == row)
            holder.bind(getItem(position),row, col)
        else
            holder.bind(getItem(position), -1, col)
    }

    fun getColumnsCount() = getItem(0).size
}