package com.example.whitecardmanagement.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "whitecard_register_table")
class WhiteCardRegisterTable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int?=null

    @ColumnInfo(name = "username")
    var username:String?=null

    @ColumnInfo(name = "father_name")
    var father_name:String?=null

    @ColumnInfo(name = "address")
    var address:String?=null

    @ColumnInfo(name = "city")
    var city:String?=null

    @ColumnInfo(name = "age")
    var age:String?=null

    @ColumnInfo(name = "date")
    var date:String?=null

    @ColumnInfo(name = "phone")
    var phone:String?=null

    @ColumnInfo(name = "card_num")
    var card_num:String?=null
    @ColumnInfo(name = "pin")
    var pin:String?=null

    constructor(
        username: String?,
        father_name: String?,
        address: String?,
        city: String?,
        age: String?,
        date: String?,
        phone: String?,
        card_num: String?,
        pin: String?
    ) {
        this.username = username
        this.father_name = father_name
        this.address = address
        this.city = city
        this.age = age
        this.date = date
        this.phone = phone
        this.card_num = card_num
        this.pin = pin
    }
}