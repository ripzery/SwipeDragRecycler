package com.ripzery.todomvp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.MotionEventCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.ripzery.swipedraggablerecyclerview.util.ItemTouchHelperAdapter
import com.ripzery.swipedraggablerecyclerview.util.ItemTouchHelperViewHolder
import com.ripzery.swipedraggablerecyclerview.util.OnStartDragListener
import com.ripzery.swipedraggablerecyclerview.util.SimpleItemTouchHelperCallback
import com.ripzery.todomvp.R
import com.ripzery.todomvp.data.Task
import com.ripzery.todomvp.data.source.Mock
import kotlinx.android.synthetic.main.fragment_draggable_recycler.*
import kotlinx.android.synthetic.main.viewholder_task.view.*

/**
 * Created by ripzery on 7/20/16.
 */

class DraggableRecyclerViewFragment : Fragment(), OnStartDragListener {
    /** Variable zone **/
    lateinit var param1: String
    lateinit private var mItemTouchHelper: ItemTouchHelper

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): DraggableRecyclerViewFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val templateFragment: DraggableRecyclerViewFragment = DraggableRecyclerViewFragment()
            templateFragment.arguments = bundle
            return templateFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            param1 = arguments.getString(ARG_1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_draggable_recycler, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        mItemTouchHelper.startDrag(viewHolder)
    }


    /** Method zone **/

    private fun initInstance() {
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager
        val taskList = Mock.getTasks()
        val draggableRecyclerAdapter = DraggableRecyclerAdapter(taskList)
        recyclerView.adapter = draggableRecyclerAdapter
        recyclerView.setHasFixedSize(true)

        val callback: ItemTouchHelper.Callback = SimpleItemTouchHelperCallback(draggableRecyclerAdapter, taskList, true, true)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper.attachToRecyclerView(recyclerView)
    }

    /* Inner class zone */

    inner class DraggableRecyclerAdapter(var taskList: MutableList<Task>) :
            RecyclerView.Adapter<DraggableRecyclerAdapter.TaskViewHolder>(), ItemTouchHelperAdapter {
        override fun onItemDismissed(position: Int) {
            notifyItemRemoved(position)
        }

        override fun onItemMoved(fromPosition: Int, toPosition: Int): Boolean {
            notifyItemMoved(fromPosition, toPosition)
            return true
        }

        override fun getItemCount(): Int {
            return taskList.size
        }

        override fun onBindViewHolder(holder: TaskViewHolder?, position: Int) {
            holder!!.setModel(taskList[position])


            holder?.itemView.ivReorder.setOnTouchListener { view, motionEvent ->
                if (MotionEventCompat.getActionMasked(motionEvent) == MotionEvent.ACTION_DOWN) {
                    this@DraggableRecyclerViewFragment.onStartDrag(holder)
                }
                false
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TaskViewHolder {
            val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.viewholder_task, parent, false)
            return TaskViewHolder(view)
        }

        inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ItemTouchHelperViewHolder {
            override fun onItemSelected() {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
            }

            override fun onItemClear() {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, android.R.color.darker_gray))
            }

            fun setModel(task: Task) {
                with(task) {
                    itemView.tvTitle.text = title
                    itemView.tvDescription.text = description
                }
            }
        }
    }
}