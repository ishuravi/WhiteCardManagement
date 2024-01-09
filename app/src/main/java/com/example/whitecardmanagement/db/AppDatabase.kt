package com.example.whitecardmanagement.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [OfficerRegisterTable::class,WhiteCardRegisterTable::class,UpdateDetailsTable::class,UpdateIT_Voting_Ration_Table::class], version = 9, exportSchema = false)


    abstract class AppDatabase : RoomDatabase() {
    abstract  fun officerRegisterDao():OfficerRegisterDao
    abstract  fun whitecardrregDao():WhiteCardRegisterDao
    abstract  fun updatedetailsDao():UpdateDetailsDao
    abstract  fun updateIT_Voting_Ration_Dao():UpdateIT_Voting_Ration_Dao


    companion object {
        private var INSTANCE: AppDatabase? = null

        internal fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {

                        INSTANCE = Room.databaseBuilder(
                                context.applicationContext,
                                AppDatabase::class.java, "Medical.db"
                        )
                                .allowMainThreadQueries()
                                .fallbackToDestructiveMigration()
                                .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }


}
