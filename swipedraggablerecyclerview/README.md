# An Android Swipable Or Draggable RecyclerView Library

# How to use

 **1. Add compiled library in build.gradle **

 ```groovy
    compile 'com.ripzery:swipedrag-recycler:1.0'
 ```

 **2. Implement an interface ItemTouchHelperAdapter**

```kotlin
inner class DraggableRecyclerAdapter(var taskList: MutableList<Task>) :
          RecyclerView.Adapter<DraggableRecyclerAdapter.TaskViewHolder>(), ItemTouchHelperAdapter {
```
