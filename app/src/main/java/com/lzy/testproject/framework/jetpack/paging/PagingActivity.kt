package com.lzy.testproject.framework.jetpack.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lzy.testproject.R
import com.lzy.testproject.utils.Logger
import kotlinx.android.synthetic.main.activity_paging2.*
import java.util.concurrent.Executors

class PagingActivity : AppCompatActivity() {

    private val model: TestViewModel by viewModels {
        object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                Logger.d("create")
                return TestViewModel(TestRespository(Executors.newFixedThreadPool(5))) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging2)
        Logger.d("onCreate")
        val adapter = TestAdapter(this)
        list.adapter = adapter
        model.posts.observe(this, Observer {
            adapter.submitList(it)
        })

        model.refreshState.observe(this, Observer {
            swipe_refresh.isRefreshing = it == NetworkState.LOADING
        })
        swipe_refresh.setOnRefreshListener {
            model.refresh()
        }
    }
}
