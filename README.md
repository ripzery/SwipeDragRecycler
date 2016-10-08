# An Android Swipable Or Draggable RecyclerView Library

[![device-2016-10-08-122941.png](https://s21.postimg.org/903t4agyf/device_2016_10_08_122941.png)](https://postimg.org/image/qq5hpbuj7/)

Easily make your RecyclerView swipable, draggable, or both.

> Note : This library and example are written in kotlin, but you can also use with java

### How to use

 **1. Add this line to dependencies in build.gradle**

 ```groovy
    compile 'com.ripzery:swipedrag-recycler:1.0'
 ```

 **2. Implement an interface *OnStartDragListener* on your Activity/Fragment**

 ```kotlin
class DraggableRecyclerViewFragment : Fragment(), OnStartDragListener {
  override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
       mItemTouchHelper.startDrag(viewHolder)
   }

   //your code
}
 ```

 **3. Implement an interface *ItemTouchHelperAdapter* on your adapter**

```kotlin
inner class DraggableRecyclerAdapter(var taskList: MutableList<Task>) :
            RecyclerView.Adapter<DraggableRecyclerAdapter.TaskViewHolder>(), ItemTouchHelperAdapter {
        override fun onItemDismissed(position: Int) {
            notifyItemRemoved(position)
        }

        override fun onItemMoved(fromPosition: Int, toPosition: Int): Boolean {
            notifyItemMoved(fromPosition, toPosition)
            return true
        }
        //your code
}
```

**4. Set *onTouchListener* on your target view which you want use to drag (Required if you want draggable feature)**

```kotlin
override fun onBindViewHolder(holder: TaskViewHolder?, position: Int) {
            // your code

            holder?.itemView.ivReorder.setOnTouchListener { view, motionEvent ->
                if (MotionEventCompat.getActionMasked(motionEvent) == MotionEvent.ACTION_DOWN) {
                    this@DraggableRecyclerViewFragment.onStartDrag(holder)
                }
                false
            }
        }
```



**5. Implement an interface *ItemTouchHelperViewHolder* on your ViewHolder class**

```kotlin
inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ItemTouchHelperViewHolder {
            override fun onItemSelected() {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
            }

            override fun onItemClear() {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, android.R.color.darker_gray))
            }

            //your code
          }
```

**6. Create an instance of *SimpleItemTouchHelperCallback* and attach to your RecyclerView**

```kotlin
val callback: ItemTouchHelper.Callback = SimpleItemTouchHelperCallback(draggableRecyclerAdapter, taskList, true, true)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper.attachToRecyclerView(recyclerView)
```

**Done!**

## Advance usage


###Class SimpleItemTouchHelperCallback

| Parameter     | Type                             | Description   |
| ------------- |:--------------------------------:|:-------------:|
| mAdapter      | RecyclerView.Adapter<ViewHolder> | recyclerView's adapter
| mList         | List<Any>                        | item list in the adapter
| swipable      | Boolean                          | swipable boolean [true: swipable, false: can't swipe]
| draggable     | Boolean                          | draggable boolean [true: draggable, false: can't drag]

Apache License
==============
    Copyright 2016 Ripzery

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
