package com.ripzery.swipedraggablerecyclerview.util

import android.support.v7.widget.RecyclerView

/**
 * Created by ripzery on 10/6/16.
 */

interface ItemTouchHelperAdapter{
    fun onItemMoved(fromPosition: Int, toPosition: Int): Boolean

    fun onItemDismissed(position: Int)
}