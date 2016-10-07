package com.ripzery.swipedraggablerecyclerview.util

import android.support.v7.widget.RecyclerView

/**
 * Created by ripzery on 10/6/16.
 */
interface OnStartDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}