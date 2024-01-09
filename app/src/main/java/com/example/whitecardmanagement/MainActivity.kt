package com.example.whitecardmanagement

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.whitecardmanagement.fragments.HomeFragment
import com.example.whitecardmanagement.helper.CommonMethods

class MainActivity : AppCompatActivity(),FragmentDrawer.FragmentDrawerListener {
    private var drawerFragment: FragmentDrawer? = null
    lateinit var mToolbar: Toolbar
    var customdialog: CommonMethods? = null
    private var drawerLayout: DrawerLayout? = null
    var fragmentDrawerView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        customdialog = CommonMethods(this)
        mToolbar = findViewById(R.id.toolbar)

        setSupportActionBar(mToolbar)
        drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setTitle("")

        mToolbar.setNavigationIcon(R.drawable.ic_menunew)

        drawerFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_navigation_drawer) as FragmentDrawer
        drawerFragment!!.setUp(
            R.id.fragment_navigation_drawer,
            findViewById<DrawerLayout>(R.id.drawer_layout),
            mToolbar
        )


        drawerFragment!!.setDrawerListener(this)
        drawerLayout!!.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

        setfragment(HomeFragment())
    }

    override fun onDrawerItemSelected(view: View, position: Int) {
        displayPosition(position)
    }

    override fun onDrawerItemView(view: View) {
        fragmentDrawerView = view
    }

    private fun displayPosition(position: Int) {
//       var title = getString(R.string.app_name)

        when (position) {
            0 -> {
                setfragment(HomeFragment())
            }
            1 -> {
                logout()
            }


        }
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        val fragmentList = supportFragmentManager.fragments
        var result = removeAll(fragmentList)
        if (count == 0) {
            //additional code
            super.onBackPressed()
            customdialog!!.showConfirmation()
        } else if (count == 1) {
            customdialog!!.showConfirmation()

        } else {
            if (result.size > 0) {
                for (model in result) {
                    Log.e("TAG", " onBackPressed model " + model::class.java)
                    if (model::class.java.equals(HomeFragment::class.java)) {
                        customdialog!!.showConfirmation()
                    }else if (model::class.java.equals(HomeFragment::class.java)) {
                        setfragment(HomeFragment())
                    }else{
                        supportFragmentManager.popBackStackImmediate()
                    }
                }
            }
        }

    }

    internal fun removeAll(list: MutableList<Fragment>): MutableList<Fragment> {
        val mutableList: MutableList<Fragment> = ArrayList()
        for (number in list) {
            if (!number.toString().startsWith("SupportRequestManagerFragment") &&
                !number.toString().startsWith("FragmentDrawer")
            ) {
                mutableList.add(number)
            }
        }
        return mutableList
    }


    fun logout() {
        AlertDialog.Builder(CommonMethods.context).setTitle(CommonMethods.context.getString(R.string.confirmation))
            .setMessage(CommonMethods.context.getString(R.string.log_out))
            .setPositiveButton(CommonMethods.context.getString(R.string.yes)) { _, _ ->

                intent = Intent(this@MainActivity, ChoseLoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setNegativeButton(CommonMethods.context.getString(R.string.no)) { dialogInterface, i ->
                dialogInterface.dismiss()
            }.show()
    }


    private fun setfragment(_fragment: Fragment) {
        val fm = supportFragmentManager
        val fragmentTransaction = fm!!.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, _fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}