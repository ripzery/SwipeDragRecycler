package com.ripzery.todomvp.data.source

import com.ripzery.todomvp.data.Task

/**
 * Created by ripzery on 10/6/16.
 */

object Mock {
    fun getTasks(): MutableList<Task> {
        return mutableListOf(
                Task(1, "1", "One"),
                Task(2, "2", "Two"),
                Task(3, "3", "Three"),
                Task(4, "4", "Four"),
                Task(5, "5", "Five")
        )
    }
}