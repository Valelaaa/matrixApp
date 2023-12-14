package com.example.matrixapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.example.matrixapplication.R
import com.example.matrixapplication.di.AppComponent
import com.example.matrixapplication.di.DaggerAppComponent
import com.example.matrixapplication.di.matrix.MatrixComponentProvider
import com.example.matrixapplication.di.matrix.MatrixViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MatrixComponentProvider {
    private val tag = "MainActivity"

    private lateinit var appComponent: AppComponent

    init {
        printLog("init $tag $this")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        printLog("onCreate $this")

        appComponent = DaggerAppComponent.builder()
            .dependency(ViewModelStore())
            .build()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        printLog("Before AppComponent")


        printLog( "After AppComponent")

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_frame, MatrixFragment.newInstance())
            .commit()

    }

    override fun provideAppComponent(): AppComponent {
        printLog("Provide AppComponent "

        )

        return appComponent

    }

    private fun printLog(message: String){
        println("!!! $message")
    }

}
