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
import com.example.whitecardmanagement.db.WhiteCardRegisterTable


class ViewWhiteCardUserAdapter(private var listItems: MutableList<WhiteCardRegisterTable>, private var context: Context, private val mListener: ListAdapterListener
): RecyclerView.Adapter<ViewWhiteCardUserAdapter.ViewHolder>()  {

    interface ListAdapterListener { // create an interface
        fun onClickButton(position: Int, list: WhiteCardRegisterTable)  // create callback function
        fun onClickButton_update(position: Int, list: WhiteCardRegisterTable)  // create callback function


    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.view_whitecard_user_list, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val list = listItems[position]

        viewHolder.id_txt.text= list.id.toString()
        viewHolder.card_no_txt.text=list.card_num
        viewHolder.name_txt.text=list.username
        viewHolder.pin_txt.text=list.pin
        viewHolder.father_txt.text=list.father_name
        viewHolder.address_txt.text=list.address
        viewHolder.age_txt.text=list.age
        viewHolder.dob_txt.text=list.date
        viewHolder.mobile_txt.text=list.phone


        viewHolder.item_layout.setOnClickListener {
            if (position != -1) {
                Log.e("TAG", "POSITION: $position")
                // use callback function to Return the Position
                mListener.onClickButton(position, list)

            }
        }

        viewHolder.gen_qr_code_txt.setOnClickListener {
            viewHolder.qr_image.visibility=View.VISIBLE
        }
        viewHolder.update_details_txt.setOnClickListener {
            mListener.onClickButton_update(position, list)
        }



    }

    override fun getItemCount(): Int {
        return listItems.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id_txt: TextView
        var card_no_txt: TextView
        var name_txt: TextView
        var pin_txt: TextView
        var father_txt: TextView
        var address_txt: TextView
        var age_txt: TextView
        var dob_txt: TextView
        var mobile_txt: TextView
        var qr_image: ImageView
          var item_layout: CardView
          var gen_qr_code_txt: TextView
          var update_details_txt: TextView


        init {
            name_txt=itemView.findViewById(R.id.name_txt)
            id_txt=itemView.findViewById(R.id.id_txt)
            card_no_txt=itemView.findViewById(R.id.card_no_txt)
            item_layout=itemView.findViewById(R.id.item_layout)
            pin_txt=itemView.findViewById(R.id.pin_txt)
            father_txt=itemView.findViewById(R.id.father_txt)
            address_txt=itemView.findViewById(R.id.address_txt)
            age_txt=itemView.findViewById(R.id.age_txt)
            dob_txt=itemView.findViewById(R.id.dob_txt)
            mobile_txt=itemView.findViewById(R.id.mobile_txt)
            qr_image=itemView.findViewById(R.id.qr_image)
            gen_qr_code_txt=itemView.findViewById(R.id.gen_qr_code_txt)
            update_details_txt=itemView.findViewById(R.id.update_details_txt)
        }
    }


}
