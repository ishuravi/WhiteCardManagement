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
import com.example.whitecardmanagement.adapter.ViewOfficerAdapter
import com.example.whitecardmanagement.db.AppDatabase
import com.example.whitecardmanagement.db.OfficerRegisterDao
import com.example.whitecardmanagement.db.OfficerRegisterTable

class ViewOfficerFragment:Fragment(),ViewOfficerAdapter.ListAdapterListener {
    lateinit var recycer:RecyclerView
    lateinit var appDatabase: AppDatabase
    lateinit var officerRegisterDao: OfficerRegisterDao
    private var list: MutableList<OfficerRegisterTable>?=null
    lateinit var viewOfficerAdapter: ViewOfficerAdapter
    lateinit var autocomplete_txt:AutoCompleteTextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_offcier_fragment,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list=ArrayList()
        recycer=view.findViewById(R.id.recycer)
        appDatabase=AppDatabase.getDatabase(requireActivity())
        officerRegisterDao=appDatabase.officerRegisterDao()
        autocomplete_txt=view.findViewById(R.id.autocomplete_txt)

        recycer.setHasFixedSize(true)
        recycer.layoutManager = LinearLayoutManager(requireActivity())
        list = officerRegisterDao.getall() as MutableList<OfficerRegisterTable>
        Log.e("TAG","insertsetdata " + officerRegisterDao.getall().size)
        setAdapter(list as ArrayList<OfficerRegisterTable>)

        autocomplete_txt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                Log.e("TAG","textwatch: " + charSequence.toString())
                var fromlist= officerRegisterDao.getvalue(charSequence.toString())
                Log.e("TAG","list_size " + fromlist.size)

                recycer.setHasFixedSize(true)
                val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
                recycer.layoutManager = layoutManager
                val adapter = ViewOfficerAdapter(fromlist as ArrayList<OfficerRegisterTable>, requireActivity(),this@ViewOfficerFragment)
                recycer.adapter=adapter


            }
            override fun afterTextChanged(editable: Editable) {

            }
        })
    }



    //set adapter
    private fun setAdapter(list: ArrayList<OfficerRegisterTable>) {

        if (list.size > 0) {
            viewOfficerAdapter = ViewOfficerAdapter(list,requireActivity(),this)
            recycer.adapter = viewOfficerAdapter
        } else {

        }
    }

    override fun onClickButton(position: Int, list: OfficerRegisterTable) {

    }
}