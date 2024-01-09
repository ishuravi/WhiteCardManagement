package com.example.whitecardmanagement.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.whitecardmanagement.R

import com.example.whitecardmanagement.helper.NavDrawerItem


class NavigationDrawerAdapter(private val context: Context, internal var data: List<NavDrawerItem>) :
    RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder>() {
    private val inflater: LayoutInflater
    var selectedPosition: Int = 0
    lateinit var radio_group: RadioGroup
    lateinit var radio1: RadioButton
    lateinit var radio2: RadioButton

    init {
        inflater = LayoutInflater.from(context)
        selectedPosition = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.nav_drawer_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = data[position]
        holder.title.text = current.title
        holder.side_menu_image.setImageDrawable(context.resources.getDrawable(current.image!!.toInt()))

        // set the animation from letf to Right

        holder.itemBg.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
        }


    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView

        var itemBg: RelativeLayout
        var side_menu_image:ImageView

        init {
            title = itemView.findViewById<View>(R.id.title) as TextView

            itemBg = itemView.findViewById(R.id.itemBg)
            side_menu_image=itemView.findViewById(R.id.side_menu_image)
        }
    }
}
