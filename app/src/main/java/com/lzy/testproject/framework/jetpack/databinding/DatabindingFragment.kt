package com.lzy.testproject.framework.jetpack.databinding


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lzy.testproject.R
import com.lzy.testproject.databinding.FragmentDatabindingBinding

class DatabindingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val bind = FragmentDatabindingBinding.inflate(inflater, container, false)
        bind.animal= Animal()
        bind.btnName.setOnClickListener {
            bind.animal?.name = "Name"
            bind.animal?.age?.set(45)
        }
        return bind.root
    }


}
