package com.example.whitecardmanagement.helper

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.LocationRequest
import android.os.Handler
import android.util.Base64
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.whitecardmanagement.R


import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.ByteArrayOutputStream
import java.net.URI.create
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class CommonMethods {

    private val TAG=CommonMethods::class.java.simpleName
    private var picker: DatePickerDialog? = null
    constructor(context: Context){
        CommonMethods.context = context
    }

    /* Context is converted to Activity.*/
    fun convertActivity(context: Context): Activity {
        return context as Activity
    }


    companion object {
        internal lateinit var context: Context
        private var Date: String? = null
    }

    fun date(input: String): String {
        val df = SimpleDateFormat(input)
        return df.format(Calendar.getInstance().time).toString()
    }

    // Check  the Device Location  is Enabled








//    // load the url image
//    fun loadImage(context: Context, imageView: ImageView, url: String) {
//        Log.e(TAG, "loadimage" + url)
//        Glide.with(context)
//            .asBitmap()
//            //.placeholder(R.drawable.img_loading)
//           .load(url)
//            .into(imageView)
//    }

    fun immHidingKeyboard(_context: Context) {
        val activity = _context as Activity
        val view = activity.currentFocus
        val imm = _context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (view!=null){
            imm.hideSoftInputFromWindow(view!!.windowToken, 0)
        }

    }

    fun immHidingKeyboard(_context: Context, view: View) {
        val imm = _context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

//    getDecimalPoint
    fun getDecimalPoint(value: Int, input: String):String{
        val digit="%."+value+ "f"
        var result=""
        try{
            if(input.length>0) {
                val convertValue = input.toDouble()
                Log.e(TAG, " digit " + digit)
                result = String.format(digit, convertValue)
                Log.e(TAG, " RESULT" + result)
            }
        }catch(e: IllegalFormatConversionException)
        {

        }
        return  result
    }

    //Show the Loading
//    fun loading(context: Context): BottomSheetDialog {
//        val dialog = BottomSheetDialog(context)
//        dialog.setContentView(R.layout.custom_progress)
//        dialog.window!!.attributes.gravity = Gravity.BOTTOM
//        //set the dialog transparent
//        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.show()
//        dialog.setCancelable(false)
//        return dialog
//    }

    fun dismissLoading(dialog: BottomSheetDialog) {
        if (dialog != null && dialog.isShowing) {
            dialog.dismiss()
        }
    }

    fun sweetAlertDialog(title: String, content: String, alertDialog: Int) {
        val sw = SweetAlertDialog(context, alertDialog)
        sw.titleText= title
        sw.contentText = content
        //sw.confirmButtonBackgroundColor=Color.parseColor("#1e98a3")



        try {
            sw.show()
            Handler().postDelayed({ sw.dismissWithAnimation() }, 2000)
        }
        catch ( e:Exception) {
            //use a log message
        }

    }

//    fun sweetAlertDialog(title: String, content: String, alertDialog: Int) {
//        val sw = SweetAlertDialog(context, alertDialog)
//        sw.titleText = title
//        sw.contentText = content
//        sw.show()
//        Handler().postDelayed({ sw.dismissWithAnimation() }, 2000)
//    }


//    fun loadingaalret(context: Context?): androidx.appcompat.app.AlertDialog? {
//        val dialog: androidx.appcompat.app.AlertDialog
//        val alertDialog = androidx.appcompat.app.AlertDialog.Builder(context!!)
//        val layoutInflater = LayoutInflater.from(context)
//        val view = layoutInflater.inflate(R.layout.custom_progress, null)
//
//        //        Glide.with(context)
////                .load(R.drawable.doublering100px)
////                .into(imageView);
//        alertDialog.setView(view)
//        dialog = alertDialog.show()
//        dialog.setCancelable(false)
//        return dialog
//    }

    //navigation drawer list items:
    fun getNavigationList(): List<NavDrawerItem> {
        val allItems = ArrayList<NavDrawerItem>()
        allItems.add(NavDrawerItem("Home",R.drawable.ic_home_icon.toString()))
        allItems.add(NavDrawerItem("Logout",R.drawable.ic_logout.toString()))
        return allItems
    }

    fun showConfirmation() {
        AlertDialog.Builder(context).setTitle(context.getString(R.string.confirmation))
                .setMessage(context.getString(R.string.confirmation_message))
                .setPositiveButton(context.getString(R.string.yes)) { _, _ ->
                    val activity = context as Activity
                    activity.finish()
                }
                .setNegativeButton(context.getString(R.string.no)) { dialogInterface, i ->
                    dialogInterface.dismiss()
                }.show()
    }

    fun code(input: String?): String? {
        val pattern: Pattern = Pattern.compile("(\\d{6})")
        //   \d is for a digit
//   {} is the number of digits here 4.
        val matcher: Matcher = pattern.matcher(input)
        var `val` = ""
        if (matcher.find()) {
            `val` = matcher.group(1) // 4 digit number
        }
        Log.e(TAG, "code: $`val`")
        return `val`
    }



//    //used in old inward outward registration
//    fun clickDate(data : TextView) {
//        val cldr = Calendar.getInstance()
//        val day = cldr.get(Calendar.YEAR)
//        val month = cldr.get(Calendar.YEAR)
//        val year = cldr.get(Calendar.YEAR)
//        picker = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//            var monthOfYear = monthOfYear
//            monthOfYear = monthOfYear + 1
//            var fm = "" + monthOfYear
//            var fd = "" + dayOfMonth
////            if (monthOfYear < 10) {
////                fm = "0$monthOfYear"
////            }
////            if (dayOfMonth < 10) {
////                fd = "0$dayOfMonth"
////            }
//            //                        _Date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
//            Date = "$fd-$fm-$year"
//            data.setText(Date)
//        }, year, month, day)
//        picker!!.show()
//        val minDate = getDate(Constants.dateformat1, getdate(Constants.ddmmyyyy))
//        Log.e(TAG, "DATE VALIDATION " + minDate)
//        picker!!.datePicker.minDate = minDate
//    }
    //convert byte[] to base64
    fun getBaseImage(image: ByteArray): String {
        return Base64.encodeToString(image,
            Base64.NO_WRAP)

    }

    // convert from bitmap to byte array
    @Throws(Exception::class)
    fun getBytes(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    // get Date from
    fun getdate(input: String): String {
        val df = SimpleDateFormat(input)
        return df.format(Calendar.getInstance().time)
    }
    fun getDate(inputFormat: String, input: String): Long {
        val format = SimpleDateFormat(inputFormat)
        val date = format.parse(input)
        return date.time

    }

    private fun createDialogWithoutDateField(): DatePickerDialog? {
        val dpd = DatePickerDialog(context, null, 2014, 1, 24)
        try {
            val datePickerDialogFields =
                dpd.javaClass.declaredFields
            for (datePickerDialogField in datePickerDialogFields) {
                if (datePickerDialogField.name == "mDatePicker") {
                    datePickerDialogField.isAccessible = true
                    val datePicker = datePickerDialogField[dpd] as DatePicker
                    val datePickerFields = datePickerDialogField.type.declaredFields
                    for (datePickerField in datePickerFields) {
                        Log.i("test", datePickerField.name)
                        if ("mDaySpinner" == datePickerField.name) {
                            datePickerField.isAccessible = true
                            val dayPicker = datePickerField[datePicker]
                            (dayPicker as View).visibility = View.GONE
                        }
                    }
                }
            }
        } catch (ex: Exception) {
        }
        return dpd
    }
}