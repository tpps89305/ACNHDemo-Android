package com.dispy.acnhdemo.view

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    /**
     * Initial Action Bar
     * @param title Page's title
     * @param enableHomeButton Set home button show or hidden
     */
    fun initActionBar(title: String, enableHomeButton: Boolean) {
        val actionBar = (activity as AppCompatActivity).supportActionBar!!
        actionBar.title = title
        actionBar.setDisplayHomeAsUpEnabled(enableHomeButton)
        setHasOptionsMenu(true)
    }

}