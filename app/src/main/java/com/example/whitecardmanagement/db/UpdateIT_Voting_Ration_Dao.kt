package com.example.whitecardmanagement.db

import androidx.room.*



@Dao
interface UpdateIT_Voting_Ration_Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tablelist: ArrayList<UpdateIT_Voting_Ration_Table>)

    @Query("Select * from update_voting_ration_table")
    fun  getall():List<UpdateIT_Voting_Ration_Table>

    @Query("SELECT * FROM update_details_table WHERE name LIKE '%' || :search || '%'")
    fun getvalue(search: String?):List<UpdateIT_Voting_Ration_Table>
}