package com.ripzery.swipedraggablerecyclerview.util

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.View
import java.util.*

/**
 * Created by ripzery on 10/6/16.
 */


class SimpleItemTouchHelperCallback<out T>(val mAdapter: ItemTouchHelperAdapter, val mList: MutableList<out T>, val mSwipeable: Boolean = true, val mDraggable: Boolean = true) : ItemTouchHelper.Callback() {

    private val FULL_ALPHA = 1.0f

    override fun isItemViewSwipeEnabled(): Boolean {
        return mSwipeable
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        val dragFlags: Int = ItemTouchHelper.UP or ItemTouchHelper.DOWN and if (mDraggable) 0xff else 0x00
        val swipeFlags: Int = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(recyclerView: RecyclerView?, source: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        if (source!!.itemViewType != target!!.itemViewType) {
            return false
        }
        Log.d(this.javaClass.simpleName, "movePosition: ${source.adapterPosition} to ${target.adapterPosition}")

        Collections.swap(mList, source.adapterPosition, target.adapterPosition)
        mAdapter.onItemMoved(source.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        Log.d(this.javaClass.simpleName, "adapterPosition : ${viewHolder!!.adapterPosition}")
        mList.removeAt(viewHolder.adapterPosition)
        mAdapter.onItemDismissed(viewHolder.adapterPosition)
    }

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val alpha = FULL_ALPHA - Math.abs(dX).toFloat().div(viewHolder!!.itemView!!.width.toFloat())
            viewHolder.itemView.alpha = alpha
            viewHolder.itemView.translationX = dX
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder is ItemTouchHelperViewHolder) {
                viewHolder.onItemSelected()
            }
        }

        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?) {
        super.clearView(recyclerView, viewHolder)

        if (viewHolder is ItemTouchHelperViewHolder) {
            viewHolder.onItemClear()
        }
    }
}