package com.example.whitecardmanagement

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whitecardmanagement.adapter.NavigationDrawerAdapter
import com.example.whitecardmanagement.helper.CommonMethods
import com.example.whitecardmanagement.helper.NavDrawerItem
import com.example.whitecardmanagement.helper.RecyclerTouchListener


import com.google.android.material.bottomsheet.BottomSheetDialog



import javax.security.auth.callback.Callback


class FragmentDrawer : Fragment() {
    val TAG= FragmentDrawer::class.java.simpleName

    lateinit var recyclerView:RecyclerView
    private var mDrawerToggle: ActionBarDrawerToggle? = null
    private var mDrawerLayout: DrawerLayout? = null
    private lateinit var containerView: View
    lateinit var commonClass: CommonMethods
    private var drawerListener: FragmentDrawerListener? = null

    lateinit var commonMethods: CommonMethods
    private var dialog: AlertDialog?=null
  //  lateinit var radio2: RadioButton
    lateinit var rootView: View

    interface FragmentDrawerListener {
        fun onDrawerItemSelected(view: View, position: Int)
        fun onDrawerItemView(view: View)
    }

    fun setDrawerListener(listener: FragmentDrawerListener) {
        this.drawerListener = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView=inflater.inflate(R.layout.fragment_drawer,container,false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        commonMethods= CommonMethods(requireContext())
        recyclerView=view.findViewById(R.id.drawerList)

        commonClass= CommonMethods(requireContext())



        val list:List<NavDrawerItem> = commonClass.getNavigationList()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=LinearLayoutManager(context)
        if (list.size>0){
            val navigationDrawerAdapter= NavigationDrawerAdapter(requireContext(),list)
            recyclerView.adapter = navigationDrawerAdapter
        }






        recyclerView.addOnItemTouchListener(
                RecyclerTouchListener(
                    requireContext(),
                        recyclerView,
                        object : RecyclerTouchListener.ClickListener {
                            override fun onClick(view: View, i: Int) {
                                Log.e("onClick"," position "+i)
                                drawerListener!!.onDrawerItemSelected(view,i)
                                mDrawerLayout!!.closeDrawer(containerView)
//                                when(i){
//                                    0->{
//                                        setfragment(AdminHomeFragment())
//                                    }
//                                    1->{
//                                        commonClass.Logoutscreen()
//                                    }
//                                }
                            }
                            override fun onLongClick(view: View?, i: Int) {

                            }
                        })
        )



    }






    fun setUp(fragmentId: Int, drawerLayout: DrawerLayout, toolbar: Toolbar) {
        containerView = requireActivity().findViewById(fragmentId)
        mDrawerLayout = drawerLayout
        mDrawerToggle = object :
                ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                //if Home Button pressed hide the soft input
//                commonClass.hideKeyboard()
                activity!!.invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                //if Home Button pressed hide the soft input
//                commonClass.hideKeyboard()
                activity!!.invalidateOptionsMenu()
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                toolbar.alpha = 1 - slideOffset / 2
            }
        }

        mDrawerLayout!!.setDrawerListener(mDrawerToggle)
        toolbar.setNavigationIcon(R.drawable.ic_menunew)
        mDrawerToggle!!.setHomeAsUpIndicator(R.drawable.ic_menunew)
        mDrawerLayout!!.post{ (mDrawerToggle as ActionBarDrawerToggle).syncState() }

        toolbar.setNavigationOnClickListener {
            mDrawerLayout!!.openDrawer(Gravity.START)
            //if Home Button pressed hide the soft input
//            commonClass.hideKeyboard()
            requireActivity().invalidateOptionsMenu()
        }

    }

}