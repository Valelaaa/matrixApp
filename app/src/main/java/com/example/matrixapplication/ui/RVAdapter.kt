package com.example.matrixapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixapplication.databinding.RecycleViewLotofTextviewItemBinding
import com.example.matrixapplication.domain.InputMatrix

class RVAdapter(val matrix: InputMatrix) :
    RecyclerView.Adapter<RVAdapter.ConstraintRowHolder>() {
    inner class ConstraintRowHolder(val binding: RecycleViewLotofTextviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(array: IntArray) {
//            binding.recycleViewTextviewItem.setLine(array)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConstraintRowHolder {
        return ConstraintRowHolder(
            binding = RecycleViewLotofTextviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return matrix.rows
    }

    override fun onBindViewHolder(holder: ConstraintRowHolder, position: Int) {
        holder.bind(matrix.getData()[position])
    }
}