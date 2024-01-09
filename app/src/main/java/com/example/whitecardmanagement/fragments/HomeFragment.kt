package com.example.whitecardmanagement.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.whitecardmanagement.R


class HomeFragment:Fragment() {
lateinit var officer_card:CardView
lateinit var view_officer_layout:CardView
lateinit var white_card_layout:CardView
lateinit var view_white_card_layout:CardView
lateinit var view_card_info_layout:CardView
lateinit var card_status_layout:CardView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        officer_card=view.findViewById(R.id.officer_card)
        view_officer_layout=view.findViewById(R.id.view_officer_layout)
        white_card_layout=view.findViewById(R.id.white_card_layout)
        view_white_card_layout=view.findViewById(R.id.view_white_card_layout)
        view_card_info_layout=view.findViewById(R.id.view_card_info_layout)
        card_status_layout=view.findViewById(R.id.card_status_layout)

        officer_card.setOnClickListener {
         setfragment(OfficerRegisterFragment())
        }
        view_officer_layout.setOnClickListener {
            setfragment(ViewOfficerFragment())
        }

        white_card_layout.setOnClickListener {
            setfragment(CreateWhiteCardUser())
        }

        view_white_card_layout.setOnClickListener {
            setfragment(ViewWhiteCardUserFragment())
        }

        view_card_info_layout.setOnClickListener {
            setfragment(ViewCardDetailsFragment())
        }

        card_status_layout.setOnClickListener {
            setfragment(ViewCardStatusFragment())
        }
    }


    private fun setfragment(_fragment: Fragment) {
        val fm = requireActivity().supportFragmentManager
        val fragmentTransaction = fm!!.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, _fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}