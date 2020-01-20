package com.lzy.testproject.framework.jetpack.navigation


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lzy.testproject.R
import com.lzy.testproject.utils.Logger
import kotlinx.android.synthetic.main.fragment_navi1.view.*

/**
 * A simple [Fragment] subclass.
 */
class Navi1Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Logger.d("onCreateView")
        var name = arguments?.getString("name")
        Logger.d("参数：$name")
        val v = inflater.inflate(R.layout.fragment_navi1, container, false)
        v.btn1.setOnClickListener {
            findNavController().navigate(R.id.action_navi1Fragment_to_nav2Fragment)
        }
        return v
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d("onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Logger.d("onAttach")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Logger.d("onActivityCreated")
    }

    override fun onResume() {
        super.onResume()
        Logger.d("onResume")
    }

    override fun onPause() {
        super.onPause()
        Logger.d("onPause")
    }

    override fun onStart() {
        super.onStart()
        Logger.d("onStart")
    }

    override fun onStop() {
        super.onStop()
        Logger.d("onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Logger.d("onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.d("onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Logger.d("onDetach")
    }
}
