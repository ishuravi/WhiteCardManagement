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
import com.example.whitecardmanagement.adapter.ViewCardStatusAdapter
import com.example.whitecardmanagement.adapter.ViewOfficerAdapter
import com.example.whitecardmanagement.adapter.ViewWhiteCardUserAdapter
import com.example.whitecardmanagement.db.*
import com.example.whitecardmanagement.helper.Constants

class ViewCardStatusFragment:Fragment(), ViewCardStatusAdapter.ListAdapterListener {
    lateinit var recycer:RecyclerView
    lateinit var appDatabase: AppDatabase
    lateinit var updateitVotingRationDao: UpdateIT_Voting_Ration_Dao
    private var list: MutableList<UpdateIT_Voting_Ration_Table>?=null
    lateinit var viewCardStatusAdapter: ViewCardStatusAdapter
    lateinit var autocomplete_txt:AutoCompleteTextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_card_status_fragment,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list=ArrayList()
        recycer=view.findViewById(R.id.recycer)
        appDatabase=AppDatabase.getDatabase(requireActivity())
        updateitVotingRationDao=appDatabase.updateIT_Voting_Ration_Dao()
        autocomplete_txt=view.findViewById(R.id.autocomplete_txt)

        recycer.setHasFixedSize(true)
        recycer.layoutManager = LinearLayoutManager(requireActivity())
        list = updateitVotingRationDao.getall() as MutableList<UpdateIT_Voting_Ration_Table>
        Log.e("TAG","insertsetdata " + updateitVotingRationDao.getall().size)
        setAdapter(list as ArrayList<UpdateIT_Voting_Ration_Table>)

        autocomplete_txt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                Log.e("TAG","textwatch: " + charSequence.toString())
                var fromlist= updateitVotingRationDao.getvalue(charSequence.toString())
                Log.e("TAG","list_size " + fromlist.size)

                recycer.setHasFixedSize(true)
                val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
                recycer.layoutManager = layoutManager
                val adapter = ViewCardStatusAdapter(fromlist as ArrayList<UpdateIT_Voting_Ration_Table>, requireActivity(),this@ViewCardStatusFragment)
                recycer.adapter=adapter


            }
            override fun afterTextChanged(editable: Editable) {

            }
        })
    }



    //set adapter
    private fun setAdapter(list: ArrayList<UpdateIT_Voting_Ration_Table>) {

        if (list.size > 0) {
            viewCardStatusAdapter = ViewCardStatusAdapter(list,requireActivity(),this)
            recycer.adapter = viewCardStatusAdapter
        } else {

        }
    }





    override fun onClickButton(position: Int, list: UpdateIT_Voting_Ration_Table) {

    }

    private fun setfragment(_fragment: Fragment) {
        val fm = requireActivity().supportFragmentManager
        val fragmentTransaction = fm!!.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, _fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}