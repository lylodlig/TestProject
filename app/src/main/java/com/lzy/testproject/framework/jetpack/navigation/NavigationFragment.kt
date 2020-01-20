package com.lzy.testproject.framework.jetpack.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lzy.testproject.R
import com.lzy.testproject.databinding.FragmentNavigationBinding
import com.lzy.testproject.framework.jetpack.databinding.User

class NavigationFragment : Fragment() {
    private lateinit var bind: FragmentNavigationBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        bind = FragmentNavigationBinding.inflate(inflater, container, false)
        bind.user = User("张三", 4)
        bind.btnDatabinding.setOnClickListener(onClickListener)
        bind.btn1.setOnClickListener(onClickListener)
        return bind.root
    }


    private val onClickListener = View.OnClickListener {
        when (it) {
            bind.btnDatabinding -> findNavController().navigate(R.id.action_navigationFragment_to_databindingFragment)
            bind.btn1 -> findNavController().navigate(R.id.action_navigationFragment_to_nav0Fragment)
        }
    }
}
