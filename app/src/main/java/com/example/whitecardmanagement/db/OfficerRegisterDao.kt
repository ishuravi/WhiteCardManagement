package com.example.whitecardmanagement.db

import androidx.room.*



@Dao
interface OfficerRegisterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tablelist: ArrayList<OfficerRegisterTable>)

    @Query("Select * from officer_register_table")
    fun  getall():List<OfficerRegisterTable>

    //login
    @Query("select * from officer_register_table where username=:username and password=:password and  designation_spinner=:designation_spinner ")
    fun login( username:String,password:String,designation_spinner:String): OfficerRegisterTable

    @Query("SELECT * FROM officer_register_table WHERE username LIKE '%' || :search || '%'")
    fun getvalue(search: String?):List<OfficerRegisterTable>
}