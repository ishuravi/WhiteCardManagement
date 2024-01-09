package com.example.whitecardmanagement.fragments

import android.app.DatePickerDialog
import android.location.Address
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.whitecardmanagement.R
import com.example.whitecardmanagement.db.AppDatabase
import com.example.whitecardmanagement.db.OfficerRegisterTable
import com.example.whitecardmanagement.db.WhiteCardRegisterDao
import com.example.whitecardmanagement.db.WhiteCardRegisterTable
import com.example.whitecardmanagement.helper.CommonMethods
import com.example.whitecardmanagement.helper.Constants
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CreateWhiteCardUser: Fragment() {
    lateinit var user_txt:EditText
    lateinit var father_txt:EditText
    lateinit var adress_txt:EditText
    lateinit var city_txt:EditText
    lateinit var age_txt:EditText
    lateinit var date_txt:TextView
    lateinit var date_image:ImageView
    lateinit var phone_txt:EditText
    lateinit var buttonLogin:Button
    lateinit var commonMethods:CommonMethods
    lateinit var appDatabase: AppDatabase
    lateinit var whiteCardRegisterDao: WhiteCardRegisterDao
    private var reg_list:ArrayList<WhiteCardRegisterTable>?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.create_white_caard_user,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reg_list= ArrayList()
        user_txt=view.findViewById(R.id.user_txt)
        father_txt=view.findViewById(R.id.father_txt)
        adress_txt=view.findViewById(R.id.adress_txt)
        city_txt=view.findViewById(R.id.city_txt)
        age_txt=view.findViewById(R.id.age_txt)
        date_txt=view.findViewById(R.id.date_txt)
        date_image=view.findViewById(R.id.date_image)
        phone_txt=view.findViewById(R.id.phone_txt)
        buttonLogin=view.findViewById(R.id.buttonLogin)
        appDatabase=AppDatabase.getDatabase(requireActivity())
        whiteCardRegisterDao=appDatabase.whitecardrregDao()

        commonMethods= CommonMethods(requireActivity())
        date_txt.text = commonMethods.date(Constants.dateformat1)
        date_image.setOnClickListener {
            clickdate(true)


        }

        buttonLogin.setOnClickListener {


            val user=user_txt.text.toString()
            val f_name=father_txt.text.toString()
            val address=adress_txt.text.toString()
            val city=city_txt.text.toString()
            val age=age_txt.text.toString()
            val phone=phone_txt.text.toString()

            if (user.isEmpty()){
                user_txt.requestFocus()
                user_txt.error="Username is empty"
            }else if (f_name.isEmpty()){
                father_txt.requestFocus()
                father_txt.error="Father name is empty"
            }else if (address.isEmpty()){
                adress_txt.requestFocus()
                adress_txt.error="Address is empty"
            }else if (city.isEmpty()){
                city_txt.requestFocus()
                city_txt.error="City is empty"
            }else if (age.isEmpty()){
                age_txt.requestFocus()
                age_txt.error="Age is empty"
            }else if(phone.isEmpty()){
                phone_txt.requestFocus()
                phone_txt.error="Phone is empty"
            }else if (phone.length<9){
                phone_txt.requestFocus()
                phone_txt.error="Phone number is invalid"
            }else{
                val number = Math.floor(Math.random() * 900000000000L).toLong() + 100000000000L
                Log.e("TAG","random : " +  number)

                val pin = Math.floor(Math.random() * 9000L).toLong() + 1000L
                Log.e("TAG","pin : " +  pin)
                reg_list!!.add(WhiteCardRegisterTable(user,f_name,address,city,age,date_txt.text.toString(),phone,number.toString(),pin.toString()))
                whiteCardRegisterDao.insert(reg_list!!)
                Toast.makeText(requireActivity(),"Post Complaint successfully ",Toast.LENGTH_SHORT).show()
            }
        }


    }


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