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
import com.example.whitecardmanagement.adapter.ViewWhiteCardUserAdapter
import com.example.whitecardmanagement.db.*
import com.example.whitecardmanagement.helper.Constants

class ViewWhiteCardUserFragment:Fragment(), ViewWhiteCardUserAdapter.ListAdapterListener {
    lateinit var recycer:RecyclerView
    lateinit var appDatabase: AppDatabase
    lateinit var whiteCardRegisterDao: WhiteCardRegisterDao
    private var list: MutableList<WhiteCardRegisterTable>?=null
    lateinit var viewWhiteCardUserAdapter: ViewWhiteCardUserAdapter
    lateinit var autocomplete_txt:AutoCompleteTextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_white_card_fragment,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list=ArrayList()
        recycer=view.findViewById(R.id.recycer)
        appDatabase=AppDatabase.getDatabase(requireActivity())
        whiteCardRegisterDao=appDatabase.whitecardrregDao()
        autocomplete_txt=view.findViewById(R.id.autocomplete_txt)

        recycer.setHasFixedSize(true)
        recycer.layoutManager = LinearLayoutManager(requireActivity())
        list = whiteCardRegisterDao.getall() as MutableList<WhiteCardRegisterTable>
        Log.e("TAG","insertsetdata " + whiteCardRegisterDao.getall().size)
        setAdapter(list as ArrayList<WhiteCardRegisterTable>)

        autocomplete_txt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                Log.e("TAG","textwatch: " + charSequence.toString())
                var fromlist= whiteCardRegisterDao.getvalue(charSequence.toString())
                Log.e("TAG","list_size " + fromlist.size)

                recycer.setHasFixedSize(true)
                val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
                recycer.layoutManager = layoutManager
                val adapter = ViewWhiteCardUserAdapter(fromlist as ArrayList<WhiteCardRegisterTable>, requireActivity(),this@ViewWhiteCardUserFragment)
                recycer.adapter=adapter


            }
            override fun afterTextChanged(editable: Editable) {

            }
        })
    }



    //set adapter
    private fun setAdapter(list: ArrayList<WhiteCardRegisterTable>) {

        if (list.size > 0) {
            viewWhiteCardUserAdapter = ViewWhiteCardUserAdapter(list,requireActivity(),this)
            recycer.adapter = viewWhiteCardUserAdapter
        } else {

        }
    }

    override fun onClickButton(position: Int, list: WhiteCardRegisterTable) {

    }

    override fun onClickButton_update(position: Int, list: WhiteCardRegisterTable) {
        setfragment(UpdateFragment(),list.id.toString(),list.card_num!!,list.username!!)
    }

    private fun setfragment(_fragment: Fragment,white_id:String,white_number:String,name:String) {
        var bundle=Bundle()
        bundle.putString(Constants.white_id,white_id)
        bundle.putString(Constants.white_number,white_number)
        bundle.putString(Constants.name,name)
        _fragment.arguments=bundle
        val fm = requireActivity().supportFragmentManager
        val fragmentTransaction = fm!!.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, _fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}