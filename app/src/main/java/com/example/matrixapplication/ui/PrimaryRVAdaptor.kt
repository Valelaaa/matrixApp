package com.example.matrixapplication.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixapplication.domain.InputMatrix

class PrimaryRVAdaptor(val factory: ConstraintRowHolder.Factory) :
    ListAdapter<IntArray, PrimaryRVAdaptor.ConstraintRowHolder>(object :
        DiffUtil.ItemCallback<IntArray>() {
        override fun areItemsTheSame(oldItem: IntArray, newItem: IntArray): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: IntArray, newItem: IntArray): Boolean {
            return oldItem.contentEquals(newItem)
        }
    }) {
    abstract class ConstraintRowHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        abstract fun bind(array: IntArray)
        interface Factory {
            fun create(view: ViewGroup, viewType: Int): ConstraintRowHolder
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConstraintRowHolder {
        return factory.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: ConstraintRowHolder, position: Int) {
        holder.bind(getItem(position))
    }
}