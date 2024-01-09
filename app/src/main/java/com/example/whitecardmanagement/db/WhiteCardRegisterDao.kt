package com.example.whitecardmanagement.db

import androidx.room.*



@Dao
interface WhiteCardRegisterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tablelist: ArrayList<WhiteCardRegisterTable>)

    @Query("Select * from whitecard_register_table")
    fun  getall():List<WhiteCardRegisterTable>

    @Query("SELECT * FROM whitecard_register_table WHERE username LIKE '%' || :search || '%'")
    fun getvalue(search: String?):List<WhiteCardRegisterTable>
}