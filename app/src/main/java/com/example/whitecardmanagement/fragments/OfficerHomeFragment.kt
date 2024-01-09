package com.example.whitecardmanagement.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.example.whitecardmanagement.R
import com.example.whitecardmanagement.helper.Constants

class OfficerHomeFragment:Fragment() {
    lateinit var relative:RelativeLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.officer_home_fragment,container,false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        relative=view.findViewById(R.id.relative)

        relative.setOnClickListener {
            setfragment(OfficerVerifyFragment())
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