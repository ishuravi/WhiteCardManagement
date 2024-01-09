package com.example.whitecardmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.whitecardmanagement.db.AppDatabase
import com.example.whitecardmanagement.db.OfficerRegisterDao

class OffcierLoginActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {
    lateinit var user_txt:EditText
    lateinit var editTextPassword:EditText
    lateinit var buttonLogin:Button
    private var spin_category :ArrayList<String>?=null
    var spinner_position = 0
    var spinner_type: String = " "
    lateinit var spinner: Spinner
    lateinit var appDatabase: AppDatabase
    lateinit var officerRegisterDao: OfficerRegisterDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.officer_login)
        spin_category=ArrayList()
        user_txt=findViewById(R.id.user_txt)
        spinner=findViewById(R.id.spinner)
        editTextPassword=findViewById(R.id.editTextPassword)
        buttonLogin=findViewById(R.id.buttonLogin)
        appDatabase=AppDatabase.getDatabase(this@OffcierLoginActivity)
        officerRegisterDao=appDatabase.officerRegisterDao()

        spinner.onItemSelectedListener = this@OffcierLoginActivity
        spin_category!!.add("Department")
        spin_category!!.add("RTO")
        spin_category!!.add("IT Return/PAN")
        spin_category!!.add("Voter")
        spin_category!!.add("Ration")

        // Creating adapter for spinner
        val dataAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this@OffcierLoginActivity, R.layout.spinner_item, spin_category!!)

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        spinner.adapter = dataAdapter

        buttonLogin.setOnClickListener {
            val user=user_txt.text.toString()
            val pass=editTextPassword.text.toString()

            if (user.isEmpty()){
                user_txt.requestFocus()
                user_txt.error="Username is empty"
            }else if (pass.isEmpty()){
                editTextPassword.requestFocus()
                editTextPassword.error="Password is empty"
            }else {
                var loginvalue=officerRegisterDao.login(user,pass,spinner_type)
                if (loginvalue!=null){
                    Toast.makeText(this@OffcierLoginActivity,"Login successfully",Toast.LENGTH_SHORT).show()
                    val intent=Intent(this@OffcierLoginActivity,OfficerMainActivity::class.java)
                    startActivity(intent)

                }else{
                    Toast.makeText(this@OffcierLoginActivity,"Username or password is invalid ",Toast.LENGTH_SHORT).show()
                }
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