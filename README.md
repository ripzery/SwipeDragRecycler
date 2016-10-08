# An Android Swipable Or Draggable RecyclerView Library

[![device-2016-10-08-114250.png](https://s4.postimg.org/6u0ccwfdp/device_2016_10_08_114250.png)](https://postimg.org/image/4pfzbtdqx/)

<div style='position:relative;padding-bottom:210%'><iframe src='https://gfycat.com/ifr/GrippingGrouchyEnglishpointer' frameborder='0' scrolling='no' width='100%' height='100%' style='position:absolute;top:0;left:0;' allowfullscreen></iframe></div>

Easily make your RecyclerView swipable, draggable, or both.

### How to use

 **1. Add this line to dependencies in build.gradle**

 ```groovy
    compile 'com.ripzery:swipedrag-recycler:1.0'
 ```

 **2. Implement an interface on your Activity/Fragment**

 ```kotlin
class DraggableRecyclerViewFragment : Fragment(), OnStartDragListener {
  override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
       mItemTouchHelper.startDrag(viewHolder)
   }

   //your code
}
 ```

 **3. Implement an interface *ItemTouchHelperAdapter* on your adapter **

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

**4. Implement an interface *ItemTouchHelperViewHolder* on your ViewHolder class**

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

**5. Create an instance of SimpleItemTouchHelperCallback and attach to your RecyclerView**

```kotlin
val callback: ItemTouchHelper.Callback = SimpleItemTouchHelperCallback(draggableRecyclerAdapter, taskList, true, true)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper.attachToRecyclerView(recyclerView)
```

**Done!**

## Advance usage


###Class SimpleItemTouchHelperCallback

| Parameter      | Type  | Description |
| ------------- |:-------------:|
| mAdapter | RecyclerView.Adapter<ViewHolder> |your recyclerView's adapter
| mList      | List<Any>  | your item list
| swipable | Boolean  | swipable boolean [true: swipable, false: can't swipe]
| draggable | Boolean  | draggable boolean [true: draggable, false: can't drag]

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
