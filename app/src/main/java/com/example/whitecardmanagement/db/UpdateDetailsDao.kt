package com.example.whitecardmanagement.db

import androidx.room.*



@Dao
interface UpdateDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tablelist: ArrayList<UpdateDetailsTable>)

    @Query("Select * from update_details_table")
    fun  getall():List<UpdateDetailsTable>

    @Query("SELECT * FROM update_details_table WHERE name LIKE '%' || :search || '%'")
    fun getvalue(search: String?):List<UpdateDetailsTable>
}