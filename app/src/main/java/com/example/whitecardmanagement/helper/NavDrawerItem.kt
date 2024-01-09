package com.example.whitecardmanagement.helper

import java.io.Serializable

class NavDrawerItem : Serializable {
    var isShowNotify: Boolean = false
    var title: String? = null
    var image:String?=null




    constructor(title: String?,image:String?) {
        this.title = title
        this.image = image

    }
}
