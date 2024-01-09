package com.example.whitecardmanagement.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.whitecardmanagement.R
import com.example.whitecardmanagement.db.AppDatabase
import com.example.whitecardmanagement.db.UpdateDetailsTable
import com.example.whitecardmanagement.db.UpdateIT_Voting_Ration_Dao
import com.example.whitecardmanagement.db.UpdateIT_Voting_Ration_Table
import com.example.whitecardmanagement.helper.Constants

class UpdateITVotingRationFragment:Fragment(), AdapterView.OnItemSelectedListener {
    internal  var title:String=""
    internal  var white_number:String=""
    internal  var name:String=""
    internal  var type:String=""
    lateinit var card_number_txt:TextView
    lateinit var name_txt:TextView
    lateinit var myTextView:TextView
    lateinit var lable_txt:TextView
    lateinit var pan_txt:EditText
    lateinit var year_txt:EditText
    lateinit var buttonLogin:Button
    lateinit var spinner: Spinner
    private var spin_category :ArrayList<String>?=null
    var spinner_position = 0
    var spinner_type: String = " "
    internal  var white_id:String=""
lateinit var appDatabase: AppDatabase
lateinit var updateitVotingRationDao: UpdateIT_Voting_Ration_Dao
    private var reg_list:ArrayList<UpdateIT_Voting_Ration_Table>?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.updateit_voting_ration_fragment,container,false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spin_category=ArrayList()
        reg_list=ArrayList()
        card_number_txt=view.findViewById(R.id.card_number_txt)
        name_txt=view.findViewById(R.id.name_txt)
        myTextView=view.findViewById(R.id.myTextView)
        lable_txt=view.findViewById(R.id.lable_txt)
        pan_txt=view.findViewById(R.id.pan_txt)
        year_txt=view.findViewById(R.id.year_txt)
        spinner=view.findViewById(R.id.spinner)
        buttonLogin=view.findViewById(R.id.buttonLogin)
        appDatabase=AppDatabase.getDatabase(requireActivity())
        updateitVotingRationDao=appDatabase.updateIT_Voting_Ration_Dao()

        spinner.onItemSelectedListener = this
        var bundle=arguments
        if (bundle!=null) {
            title= bundle.getString(Constants.title)!!
            white_number= bundle.getString(Constants.white_number)!!
            name= bundle.getString(Constants.name)!!
            type= bundle.getString(Constants.type)!!
            white_id= bundle.getString(Constants.white_id)!!
            Log.e("TAG","white_id : " + title)
            card_number_txt.text=white_number
            name_txt.text=name

            if (title=="IT Return Update"){
                myTextView.setText("IT Return Update")
                lable_txt.setText("Pan")
                pan_txt.hint="Pan"
                spin_category!!.add("Status")
                spin_category!!.add("Payed")
                spin_category!!.add("On Progress")
                spin_category!!.add("UnPayed")
            }else if (title=="Update Voting"){
                myTextView.setText("Voting Update")
                lable_txt.setText("Voting ID")
                pan_txt.hint="Voter ID"
                spin_category!!.add("Status")
                spin_category!!.add("Active")
                spin_category!!.add("InActive")

            }else{
                myTextView.setText("Ration Update")
                lable_txt.setText("Ration ID")
                pan_txt.hint="Ration ID"
                spin_category!!.add("Status")
                spin_category!!.add("Active")
                spin_category!!.add("InActive")
                spin_category!!.add("Closed")
            }
        }

        // Creating adapter for spinner
        val dataAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireActivity(), R.layout.spinner_item, spin_category!!)

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        spinner.adapter = dataAdapter

        buttonLogin.setOnClickListener {
            reg_list!!.add(UpdateIT_Voting_Ration_Table(white_id,white_number,name,pan_txt.text.toString(),year_txt.text.toString(),spinner_type,type))
            updateitVotingRationDao.insert(reg_list!!)
            Toast.makeText(requireActivity(),"Save Successfully ",Toast.LENGTH_SHORT).show()
        }
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        spinner_position = position
        spinner_type = spin_category!!.get(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}