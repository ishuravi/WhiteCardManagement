package com.example.whitecardmanagement.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.whitecardmanagement.R

import com.example.whitecardmanagement.db.OfficerRegisterTable


class ViewOfficerAdapter(private var listItems: MutableList<OfficerRegisterTable>, private var context: Context, private val mListener: ListAdapterListener
): RecyclerView.Adapter<ViewOfficerAdapter.ViewHolder>()  {

    interface ListAdapterListener { // create an interface
        fun onClickButton(position: Int, list: OfficerRegisterTable)  // create callback function


    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.view_officer_list, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val list = listItems[position]
        viewHolder.name_txt.text=list.username
        viewHolder.id_txt.text= list.id.toString()
        viewHolder.phone_txt.text=list.phone
        viewHolder.email_txt.text=list.email
        viewHolder.Designation_txt.text=list.designation
        viewHolder.department_txt.text=list.designation_spinner




        viewHolder.item_layout.setOnClickListener {
            if (position != -1) {
                Log.e("TAG", "POSITION: $position")
                // use callback function to Return the Position
                mListener.onClickButton(position, list)

            }
        }



    }

    override fun getItemCount(): Int {
        return listItems.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         var name_txt: TextView
         var id_txt: TextView
          var item_layout: CardView
         var phone_txt: TextView
         var email_txt:TextView
         var Designation_txt:TextView
         var department_txt:TextView

        init {
            name_txt=itemView.findViewById(R.id.name_txt)
            id_txt=itemView.findViewById(R.id.id_txt)
            phone_txt=itemView.findViewById(R.id.phone_txt)
            item_layout=itemView.findViewById(R.id.item_layout)
            email_txt=itemView.findViewById(R.id.email_txt)
            Designation_txt=itemView.findViewById(R.id.Designation_txt)
            department_txt=itemView.findViewById(R.id.department_txt)
        }
    }


}
