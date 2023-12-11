package com.example.matrixapplication

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var matrixFragment: MatrixFragment
    val appComponent by lazy{
        DaggerMatrixComponent.factory().create(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentFrame = findViewById<FrameLayout>(R.id.fragment_frame)
        appComponent.inject(this)

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_frame, matrixFragment)
        fragmentTransaction.commit()
    }
}
