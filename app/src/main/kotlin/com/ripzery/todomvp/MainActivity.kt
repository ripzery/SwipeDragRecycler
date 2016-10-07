package com.ripzery.todomvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ripzery.todomvp.fragments.DraggableRecyclerViewFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, DraggableRecyclerViewFragment.newInstance(""))
                .commit()
    }
}