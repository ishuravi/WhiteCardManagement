package com.example.whitecardmanagement.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whitecardmanagement.R
import com.example.whitecardmanagement.adapter.ViewCardDetailsAdapter
import com.example.whitecardmanagement.adapter.ViewOfficerAdapter
import com.example.whitecardmanagement.adapter.ViewWhiteCardUserAdapter
import com.example.whitecardmanagement.db.*
import com.example.whitecardmanagement.helper.Constants

class ViewCardDetailsFragment:Fragment(), ViewCardDetailsAdapter.ListAdapterListener {
    lateinit var recycer:RecyclerView
    lateinit var appDatabase: AppDatabase
    lateinit var updateDetailsDao: UpdateDetailsDao
    private var list: MutableList<UpdateDetailsTable>?=null
    lateinit var viewCardDetailsAdapter: ViewCardDetailsAdapter
    lateinit var autocomplete_txt:AutoCompleteTextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_card_details_fragment,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list=ArrayList()
        recycer=view.findViewById(R.id.recycer)
        appDatabase=AppDatabase.getDatabase(requireActivity())
        updateDetailsDao=appDatabase.updatedetailsDao()
        autocomplete_txt=view.findViewById(R.id.autocomplete_txt)

        recycer.setHasFixedSize(true)
        recycer.layoutManager = LinearLayoutManager(requireActivity())
        list = updateDetailsDao.getall() as MutableList<UpdateDetailsTable>
        Log.e("TAG","insertsetdata " + updateDetailsDao.getall().size)
        setAdapter(list as ArrayList<UpdateDetailsTable>)

        autocomplete_txt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                Log.e("TAG","textwatch: " + charSequence.toString())
                var fromlist= updateDetailsDao.getvalue(charSequence.toString())
                Log.e("TAG","list_size " + fromlist.size)

                recycer.setHasFixedSize(true)
                val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
                recycer.layoutManager = layoutManager
                val adapter = ViewCardDetailsAdapter(fromlist as ArrayList<UpdateDetailsTable>, requireActivity(),this@ViewCardDetailsFragment)
                recycer.adapter=adapter


            }
            override fun afterTextChanged(editable: Editable) {

            }
        })
    }



    //set adapter
    private fun setAdapter(list: ArrayList<UpdateDetailsTable>) {

        if (list.size > 0) {
            viewCardDetailsAdapter = ViewCardDetailsAdapter(list,requireActivity(),this)
            recycer.adapter = viewCardDetailsAdapter
        } else {

        }
    }





    override fun onClickButton(position: Int, list: UpdateDetailsTable) {

    }

    override fun onClickButton_update(position: Int, list: UpdateDetailsTable) {
        setfragment(UpdateITVotingRationFragment(),"IT Return Update",list.white_card_no.toString(),list.name!!,list.department!!,list.white_id!!)
    }

    override fun onClickButton_voting_update(position: Int, list: UpdateDetailsTable) {
        setfragment(UpdateITVotingRationFragment(),"Update Voting",list.white_card_no.toString(),list.name!!,list.department!!,list.white_id!!)
    }

    override fun onClickButton_ration_update(position: Int, list: UpdateDetailsTable) {
        setfragment(UpdateITVotingRationFragment(),"Update Ration",list.white_card_no.toString(),list.name!!,list.department!!,list.white_id!!)

    }

    private fun setfragment(_fragment: Fragment,title:String,white_number:String,name:String,type:String,white_id:String) {
        var bundle=Bundle()
        bundle.putString(Constants.title,title)
        bundle.putString(Constants.white_number,white_number)
        bundle.putString(Constants.name,name)
        bundle.putString(Constants.type,type)
        bundle.putString(Constants.white_id,white_id)
        _fragment.arguments=bundle
        val fm = requireActivity().supportFragmentManager
        val fragmentTransaction = fm!!.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, _fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}