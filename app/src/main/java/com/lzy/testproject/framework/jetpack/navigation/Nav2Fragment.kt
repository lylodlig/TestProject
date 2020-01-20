package com.lzy.testproject.framework.jetpack.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lzy.testproject.R
import kotlinx.android.synthetic.main.fragment_nav2.view.*

class Nav2Fragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_nav2, container, false)
        v.btn1.setOnClickListener {
            findNavController().navigate(R.id.action_nav2Fragment_to_navigationFragment2)
        }
        return v
    }

}
