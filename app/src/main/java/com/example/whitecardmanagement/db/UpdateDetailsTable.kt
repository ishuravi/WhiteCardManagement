package com.example.whitecardmanagement.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "update_details_table")
class UpdateDetailsTable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int?=null

    @ColumnInfo(name = "white_id")
    var white_id:String?=null

    @ColumnInfo(name = "white_card_no")
    var white_card_no:String?=null

    @ColumnInfo(name = "name")
    var name:String?=null

    @ColumnInfo(name = "pan_Ration_Voter")
    var pan_Ration_Voter:String?=null

    @ColumnInfo(name = "valid_upto")
    var valid_upto:String?=null

    @ColumnInfo(name = "address")
    var address:String?=null

    @ColumnInfo(name = "date")
    var date:String?=null

    @ColumnInfo(name = "department")
    var department:String?=null

    constructor(
        white_id: String?,
        white_card_no: String?,
        name: String?,
        pan_Ration_Voter: String?,
        valid_upto: String?,
        address: String?,
        date: String?,
        department: String?
    ) {
        this.white_id = white_id
        this.white_card_no = white_card_no
        this.name = name
        this.pan_Ration_Voter = pan_Ration_Voter
        this.valid_upto = valid_upto
        this.address = address
        this.date = date
        this.department = department
    }
}