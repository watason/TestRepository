package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.opencv.android.OpenCVLoader

class MainActivity : AppCompatActivity(),RowAdapter.ViewHolder.itemClickListener {
    override fun OnItemClick(v: View, pos: Int) {
        Toast.makeText(applicationContext,"test message !! $pos",Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rc : RecyclerView = findViewById(R.id.my_recycler_view)
        val data : MutableList<String> = arrayListOf()
        val item : ItemTouchHelper
        rc.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)

            for (i in 0..20) {
                data.add("test2")
            }

            adapter = RowAdapter(data,this@MainActivity)

        }
        item = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition

                rc.adapter!!.notifyItemMoved(fromPosition,toPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewHolder.let{
                    rc.adapter!!.notifyItemRemoved(viewHolder.adapterPosition)
                }
            }

        })
        item.attachToRecyclerView(rc)

    }

    private fun opencvString(arg : Boolean) : String {
        when(arg){
            true -> return "Opencv Version :" + OpenCVLoader.OPENCV_VERSION
            false -> return "FAILED OpenCV"
        }
    }
}
