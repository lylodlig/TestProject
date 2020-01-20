package com.lzy.testproject.framework.jetpack.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lzy.testproject.R
import kotlinx.android.synthetic.main.fragment_nav0.view.*

/**
 * A simple [Fragment] subclass.
 */
class Nav0Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_nav0, container, false)
        v.btn1.setOnClickListener {
            var args = Bundle()
            args.putString("name", "传递的参数")
            findNavController().navigate(R.id.action_nav0Fragment_to_navi1Fragment, args)
        }
        v.btn3.setOnClickListener {
            findNavController().navigate(R.id.action_nav0Fragment_to_nav2Fragment)
        }
        return v
    }


}
