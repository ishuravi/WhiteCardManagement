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
import com.example.whitecardmanagement.db.UpdateDetailsTable
import com.example.whitecardmanagement.db.UpdateIT_Voting_Ration_Table
import com.example.whitecardmanagement.db.WhiteCardRegisterTable


class OfficerVerifyAdapter(private var listItems: MutableList<UpdateIT_Voting_Ration_Table>, private var context: Context, private val mListener: ListAdapterListener
): RecyclerView.Adapter<OfficerVerifyAdapter.ViewHolder>()  {

    interface ListAdapterListener { // create an interface
        fun onClickButton(position: Int, list: UpdateIT_Voting_Ration_Table)
        fun onClickButtonapprove(position: Int, list: UpdateIT_Voting_Ration_Table)
        fun onClickButtonrejected(position: Int, list: UpdateIT_Voting_Ration_Table)

    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.view_card_status_list, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val list = listItems[position]

        viewHolder.id_txt.text= list.white_id.toString()
        viewHolder.card_no_txt.text=list.white_card_no
        viewHolder.name_txt.text=list.name
        viewHolder.type_txt.text=list.type
        viewHolder.id_no_txt.text=list.pan_Ration_Voter
        viewHolder.year_txt.text=list.year
        viewHolder.status_txt.text=list.department

        viewHolder.approve_txt.visibility=View.VISIBLE
        viewHolder.rejected_txt.visibility=View.VISIBLE




        viewHolder.item_layout.setOnClickListener {
            if (position != -1) {
                Log.e("TAG", "POSITION: $position")
                // use callback function to Return the Position
                mListener.onClickButton(position, list)

            }
        }

        viewHolder.approve_txt.setOnClickListener {
            mListener.onClickButtonapprove(position, list)
        }

        viewHolder.rejected_txt.setOnClickListener {
            mListener.onClickButtonrejected(position, list)
        }





    }

    override fun getItemCount(): Int {
        return listItems.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id_txt: TextView
        var card_no_txt: TextView
        var name_txt: TextView

        var id_no_txt: TextView
        var year_txt: TextView
        var status_txt: TextView
        var approve_txt: TextView
        var rejected_txt: TextView
        var type_txt: TextView
        var item_layout: CardView



        init {
            name_txt=itemView.findViewById(R.id.name_txt)
            id_txt=itemView.findViewById(R.id.id_txt)
            card_no_txt=itemView.findViewById(R.id.card_no_txt)
            item_layout=itemView.findViewById(R.id.item_layout)
            type_txt=itemView.findViewById(R.id.type_txt)
            id_no_txt=itemView.findViewById(R.id.id_no_txt)
            year_txt=itemView.findViewById(R.id.year_txt)
            status_txt=itemView.findViewById(R.id.status_txt)
            rejected_txt=itemView.findViewById(R.id.rejected_txt)
            approve_txt=itemView.findViewById(R.id.approve_txt)


        }
    }


}
