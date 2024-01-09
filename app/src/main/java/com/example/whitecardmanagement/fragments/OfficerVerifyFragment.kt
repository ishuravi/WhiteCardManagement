package com.example.whitecardmanagement.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.whitecardmanagement.R
import com.example.whitecardmanagement.adapter.OfficerVerifyAdapter
import com.example.whitecardmanagement.adapter.ViewCardStatusAdapter
import com.example.whitecardmanagement.db.AppDatabase
import com.example.whitecardmanagement.db.UpdateIT_Voting_Ration_Dao
import com.example.whitecardmanagement.db.UpdateIT_Voting_Ration_Table
import com.example.whitecardmanagement.helper.CommonMethods
import com.example.whitecardmanagement.helper.Constants

class OfficerVerifyFragment:Fragment(),OfficerVerifyAdapter.ListAdapterListener {
lateinit var buttonLogin:Button
lateinit var user_txt:EditText
lateinit var pin_txt:EditText
lateinit var recycler:RecyclerView
lateinit var updateitVotingRationDao: UpdateIT_Voting_Ration_Dao
private var list: MutableList<UpdateIT_Voting_Ration_Table>?=null
    lateinit var officerVerifyAdapter: OfficerVerifyAdapter
    lateinit var appDatabase: AppDatabase
    lateinit var commonMethods:CommonMethods

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.officer_verify_fragment,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonLogin=view.findViewById(R.id.buttonLogin)
        user_txt=view.findViewById(R.id.user_txt)
        pin_txt=view.findViewById(R.id.pin_txt)
        list=ArrayList()
        recycler=view.findViewById(R.id.recycler)
        appDatabase=AppDatabase.getDatabase(requireActivity())
        updateitVotingRationDao=appDatabase.updateIT_Voting_Ration_Dao()
        commonMethods= CommonMethods(requireActivity())

        buttonLogin.setOnClickListener {
            val user=user_txt.text.toString()
            val pin=pin_txt.text.toString()

            if (user.isEmpty()){
                user_txt.requestFocus()
                user_txt.error="Please enter the name"
            }else if (pin.isEmpty()){
                pin_txt.requestFocus()
                pin_txt.error="Please enter the Pin"
            }else{
                recycler.setHasFixedSize(true)
                recycler.layoutManager = LinearLayoutManager(requireActivity())
                list = updateitVotingRationDao.getall() as MutableList<UpdateIT_Voting_Ration_Table>
                Log.e("TAG","insertsetdata " + updateitVotingRationDao.getall().size)
                setAdapter(list as ArrayList<UpdateIT_Voting_Ration_Table>)
            }
        }
    }

    //set adapter
    private fun setAdapter(list: ArrayList<UpdateIT_Voting_Ration_Table>) {

        if (list.size > 0) {
            officerVerifyAdapter = OfficerVerifyAdapter(list,requireActivity(),this)
            recycler.adapter = officerVerifyAdapter
        } else {

        }
    }
    private fun setfragment(_fragment: Fragment) {
        val fm = requireActivity().supportFragmentManager
        val fragmentTransaction = fm!!.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, _fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onClickButton(position: Int, list: UpdateIT_Voting_Ration_Table) {

    }

    override fun onClickButtonapprove(position: Int, list: UpdateIT_Voting_Ration_Table) {
        commonMethods.sweetAlertDialog(resources.getString(R.string.app_name), "Approved successfully!", SweetAlertDialog.SUCCESS_TYPE)
    }

    override fun onClickButtonrejected(position: Int, list: UpdateIT_Voting_Ration_Table) {
        commonMethods.sweetAlertDialog(resources.getString(R.string.app_name), "Rejectd successfully!", SweetAlertDialog.ERROR_TYPE)
    }
}