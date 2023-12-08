package com.example.matrixapplication

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @Inject
    private lateinit var matrixFragment: MatrixFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentFrame = findViewById<FrameLayout>(R.id.fragment_frame)

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_frame, matrixFragment)
        fragmentTransaction.commit()
    }
}
