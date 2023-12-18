package com.example.matrixapplication.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixapplication.domain.InputMatrix

class PrimaryRVAdaptor(val matrix: InputMatrix,val factory: ConstraintRowHolder.Factory) :
    RecyclerView.Adapter<PrimaryRVAdaptor.ConstraintRowHolder>() {
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


    override fun getItemCount(): Int {
        return matrix.rows
    }

    override fun onBindViewHolder(holder: ConstraintRowHolder, position: Int) {
        holder.bind(matrix.getData()[position])
    }

}