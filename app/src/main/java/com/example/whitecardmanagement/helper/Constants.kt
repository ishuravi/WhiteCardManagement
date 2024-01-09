package com.example.whitecardmanagement.helper


import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.*

/**
 * Created by admin on 16-Feb-18.
 */

class Constants {
    fun killAllToast() {
        //        Log.e(TAG, "killAllToast: size : "+msjsToast.size() );
        if (msjsToast != null) {
            for (t in msjsToast!!) {
                t.cancel()
            }
            msjsToast!!.clear()
        }
    }

    companion object {
        const  val dateformat1="dd-MM-yyyy"
        var white_id="white_id"
        var white_number="white_number"
        var title="title"
        var name="name"
        var type="type"

        internal var TAG = Constants::class.java.simpleName

        //location
        const val REQUEST_CHECK_SETTINGS = 3



        var msjsToast: ArrayList<Toast>? = ArrayList()
        var idleCheck: ArrayList<Toast>? = ArrayList()
        var connectionDialog: ArrayList<AlertDialog>? = ArrayList()



        var View = "view"


    }


}
