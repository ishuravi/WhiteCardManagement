package com.example.whitecardmanagement.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.whitecardmanagement.R
import com.example.whitecardmanagement.db.AppDatabase
import com.example.whitecardmanagement.db.OfficerRegisterDao
import com.example.whitecardmanagement.db.OfficerRegisterTable

class OfficerRegisterFragment:Fragment(),AdapterView.OnItemSelectedListener {
    lateinit var user_txt:EditText
    lateinit var email_txt:EditText
    lateinit var phone_txt:EditText
    lateinit var Designation_txt:EditText
    lateinit var passowrd_txt:EditText
    lateinit var spinner:Spinner
    lateinit var buttonLogin:Button
    private var spin_category :ArrayList<String>?=null
    var spinner_position = 0
    var spinner_type: String = " "
    lateinit var appDatabase: AppDatabase
    lateinit var officerRegisterDao: OfficerRegisterDao
    private var reg_list:ArrayList<OfficerRegisterTable>?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.officer_register_fragment,container,false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reg_list=ArrayList()
        spin_category=ArrayList()
        user_txt=view.findViewById(R.id.user_txt)
        email_txt=view.findViewById(R.id.email_txt)
        phone_txt=view.findViewById(R.id.phone_txt)
        Designation_txt=view.findViewById(R.id.Designation_txt)
        passowrd_txt=view.findViewById(R.id.passowrd_txt)
        spinner=view.findViewById(R.id.spinner)
        buttonLogin=view.findViewById(R.id.buttonLogin)
        reg_list = ArrayList()
        appDatabase= AppDatabase.getDatabase(requireActivity())

        officerRegisterDao=appDatabase.officerRegisterDao()


        spinner.onItemSelectedListener = this
        spin_category!!.add("Department")
        spin_category!!.add("RTO")
        spin_category!!.add("IT Return/PAN")
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
            val user=user_txt.text.toString()
            val email=email_txt.text.toString()
            val phone=phone_txt.text.toString()
            val Designa=Designation_txt.text.toString()
            val pass=passowrd_txt.text.toString()

            if (user.isEmpty()){
                user_txt.requestFocus()
                user_txt.error="Username is empty"
            }else if (email.isEmpty()){
                email_txt.requestFocus()
                email_txt.error="Email id is empty"
            }else if (phone.isEmpty()){
                phone_txt.requestFocus()
                phone_txt.error="Phone number is empty"
            }else if (phone.length<9){
                phone_txt.requestFocus()
                phone_txt.error="Phone number is invalid"
            }else if (Designa.isEmpty()){
                Designation_txt.requestFocus()
                Designation_txt.error="Designation is empty"
            }else if (pass.isEmpty()){
                passowrd_txt.requestFocus()
                passowrd_txt.error="Password is empty"
            }else{
                reg_list!!.add(OfficerRegisterTable(user,email,phone,Designa,pass,spinner_type))
                officerRegisterDao.insert(reg_list!!)
                Toast.makeText(requireActivity(),"Register Successfully ",Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        spinner_position = position
        spinner_type = spin_category!!.get(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}