package com.example.whitecardmanagement.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.whitecardmanagement.R
import com.example.whitecardmanagement.db.AppDatabase
import com.example.whitecardmanagement.db.UpdateDetailsDao
import com.example.whitecardmanagement.db.UpdateDetailsTable
import com.example.whitecardmanagement.db.WhiteCardRegisterTable
import com.example.whitecardmanagement.helper.CommonMethods
import com.example.whitecardmanagement.helper.Constants
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UpdateFragment:Fragment(), AdapterView.OnItemSelectedListener {
    lateinit var spinner: Spinner
    lateinit var buttonLogin: Button
    private var spin_category :ArrayList<String>?=null
    var spinner_position = 0
    var spinner_type: String = " "
    internal  var white_id:String=" "
    internal  var white_number:String=""
    internal  var name:String=""
    lateinit var card_number_txt:TextView
    lateinit var name_txt:TextView
    lateinit var pan_ration_voter_txt:EditText
    lateinit var valid_upto_txt:EditText
    lateinit var address_txt:EditText
    lateinit var date_txt:TextView
    lateinit var date_image:ImageView
    lateinit var commonMethods:CommonMethods
    lateinit var appDatabase: AppDatabase
    lateinit var updateDetailsDao: UpdateDetailsDao
    private var reg_list:ArrayList<UpdateDetailsTable>?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.update_fragment,container,false)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spin_category=ArrayList()
        reg_list= ArrayList()
        card_number_txt=view.findViewById(R.id.card_number_txt)
        name_txt=view.findViewById(R.id.name_txt)
        pan_ration_voter_txt=view.findViewById(R.id.pan_ration_voter_txt)
        valid_upto_txt=view.findViewById(R.id.valid_upto_txt)
        address_txt=view.findViewById(R.id.address_txt)
        date_txt=view.findViewById(R.id.date_txt)
        spinner=view.findViewById(R.id.spinner)
        date_image=view.findViewById(R.id.date_image)
        buttonLogin=view.findViewById(R.id.buttonLogin)
        commonMethods= CommonMethods(requireActivity())
        appDatabase=AppDatabase.getDatabase(requireActivity())
        updateDetailsDao=appDatabase.updatedetailsDao()

        var bundle=arguments
        if (bundle!=null) {
            white_id= bundle.getString(Constants.white_id)!!
            white_number= bundle.getString(Constants.white_number)!!
            name= bundle.getString(Constants.name)!!
            Log.e("TAG","white_id : " + white_id)
            card_number_txt.text=white_number
            name_txt.text=name
        }


        commonMethods= CommonMethods(requireActivity())
             date_txt.text = commonMethods.date(Constants.dateformat1)
        date_image.setOnClickListener {
            clickdate(true)
        }

        spinner.onItemSelectedListener = this
        spin_category!!.add("Department")
        spin_category!!.add("RTO")
        spin_category!!.add("IT Return")
        spin_category!!.add("Voter")
        spin_category!!.add("Ration")

        // Creating adapter for spinner
        val dataAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireActivity(), R.layout.spinner_item, spin_category!!)

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        spinner.adapter = dataAdapter

        buttonLogin.setOnClickListener {
         val name=name_txt.text.toString()
         val pan_ration_voter=pan_ration_voter_txt.text.toString()
         val valid_upto=valid_upto_txt.text.toString()
         val address=address_txt.text.toString()

            if (pan_ration_voter.isEmpty()){
                pan_ration_voter_txt.requestFocus()
                pan_ration_voter_txt.error="Pan / Ration / Voter is empty"
            }else if (valid_upto.isEmpty()){
                valid_upto_txt.requestFocus()
                valid_upto_txt.error="Valid upto is empty"
            }else if (address.isEmpty()){
                address_txt.requestFocus()
                address_txt.error="Address is empty"
            }else {
                reg_list!!.add(UpdateDetailsTable(white_id,white_number,name,pan_ration_voter,valid_upto,address,date_txt.text.toString(),spinner_type))
                updateDetailsDao.insert(reg_list!!)
                Toast.makeText(requireActivity(),"Update Successfully ",Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        spinner_position = position
        spinner_type = spin_category!!.get(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    @SuppressLint("SimpleDateFormat")
    private fun clickdate(status:Boolean){
        val formatter = SimpleDateFormat(Constants.dateformat1)
        val  now = formatter.parse(date_txt.text.toString()) as Date

        val cal = Calendar.getInstance()
        cal.time = now
        val datePicker= DatePickerDialog(
            requireActivity(), { _, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)

                val s = DateFormat.format(Constants.dateformat1, cal.time)
                date_txt.text=s
            },
            cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)

        )
        if (status)
            datePicker.show()
    }


}