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
import com.example.whitecardmanagement.db.WhiteCardRegisterTable


class ViewCardDetailsAdapter(private var listItems: MutableList<UpdateDetailsTable>, private var context: Context, private val mListener: ListAdapterListener
): RecyclerView.Adapter<ViewCardDetailsAdapter.ViewHolder>()  {

    interface ListAdapterListener { // create an interface
        fun onClickButton(position: Int, list: UpdateDetailsTable)
        fun onClickButton_update(position: Int, list: UpdateDetailsTable)
        fun onClickButton_voting_update(position: Int, list: UpdateDetailsTable)
        fun onClickButton_ration_update(position: Int, list: UpdateDetailsTable)


    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.view_card_details_list, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val list = listItems[position]

        viewHolder.id_txt.text= list.white_id.toString()
        viewHolder.card_no_txt.text=list.white_card_no
        viewHolder.name_txt.text=list.name
        viewHolder.type_txt.text=list.department
        viewHolder.pan_ration_txt.text=list.pan_Ration_Voter
        viewHolder.address_txt.text=list.address
        viewHolder.valid_upto_txt.text=list.valid_upto
        viewHolder.dob_txt.text=list.date



        viewHolder.item_layout.setOnClickListener {
            if (position != -1) {
                Log.e("TAG", "POSITION: $position")
                // use callback function to Return the Position
                mListener.onClickButton(position, list)

            }
        }


        viewHolder.update_it_txt.setOnClickListener {
            mListener.onClickButton_update(position, list)
        }

        viewHolder.update_voting_txt.setOnClickListener {
            mListener.onClickButton_voting_update(position, list)
        }

        viewHolder.update_ration_txt.setOnClickListener {
            mListener.onClickButton_ration_update(position, list)
        }



    }

    override fun getItemCount(): Int {
        return listItems.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id_txt: TextView
        var card_no_txt: TextView
        var name_txt: TextView
        var type_txt: TextView
        var pan_ration_txt: TextView
        var address_txt: TextView
        var valid_upto_txt: TextView
        var dob_txt: TextView
        var item_layout: CardView
        var update_it_txt: TextView
        var update_voting_txt: TextView
        var update_ration_txt: TextView


        init {
            name_txt=itemView.findViewById(R.id.name_txt)
            id_txt=itemView.findViewById(R.id.id_txt)
            card_no_txt=itemView.findViewById(R.id.card_no_txt)
            item_layout=itemView.findViewById(R.id.item_layout)
            type_txt=itemView.findViewById(R.id.type_txt)
            pan_ration_txt=itemView.findViewById(R.id.pan_ration_txt)
            address_txt=itemView.findViewById(R.id.address_txt)
            valid_upto_txt=itemView.findViewById(R.id.valid_upto_txt)
            dob_txt=itemView.findViewById(R.id.dob_txt)
            update_it_txt=itemView.findViewById(R.id.update_it_txt)
            update_voting_txt=itemView.findViewById(R.id.update_voting_txt)
            update_ration_txt=itemView.findViewById(R.id.update_ration_txt)

        }
    }


}
