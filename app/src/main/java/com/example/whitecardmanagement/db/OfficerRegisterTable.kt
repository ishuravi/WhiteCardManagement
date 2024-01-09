package com.example.whitecardmanagement.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "officer_register_table")
class OfficerRegisterTable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int?=null

    @ColumnInfo(name = "username")
    var username:String?=null

    @ColumnInfo(name = "email")
    var email:String?=null

    @ColumnInfo(name = "phone")
    var phone:String?=null

    @ColumnInfo(name = "designation")
    var designation:String?=null

    @ColumnInfo(name = "password")
    var password:String?=null

    @ColumnInfo(name = "designation_spinner")
    var designation_spinner:String?=null

    constructor(

        username: String?,
        email: String?,
        phone: String?,
        designation: String?,
        password: String?,
        designation_spinner: String?
    ) {

        this.username = username
        this.email = email
        this.phone = phone
        this.designation = designation
        this.password = password
        this.designation_spinner = designation_spinner
    }
}