package com.example.matrixapplication

import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MatrixViewModel @Inject constructor(var matrixRepository:MatrixRepository) {
    val matrixLiveData = matrixRepository.getMatrix()
    var rows:Int = 0
    var columns:Int = 0

    var logsText:StringBuilder = StringBuilder()
    fun refreshMatrix(){
        matrixRepository.refresh()
    }
