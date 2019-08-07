package com.lzy.testproject.ui.customview.seekbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.SeekBar
import com.lzy.testproject.R
import kotlinx.android.synthetic.main.activity_snail.*

class SnailActivity : AppCompatActivity() {

    var len:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snail)

        seekBar.max = 100

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val message = Message()
                seekBar.progress
                var result = (seekBar.progress / seekBar.max) * 100
                val bundle = Bundle()
                bundle.putFloat("per", result.toFloat())
                message.data = bundle
                message.what = 0
                handler.sendMessage(message)

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        handler.postDelayed({ handler.sendEmptyMessage(1) }, 2000)
    }

    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 -> Log.i("lzy", "handleMessage: " + msg.data.getFloat("per") + "%")
                1 ->

                    if (seekBar.progress < 100) {

                        if (seekBar.progress < 20) {
                            len += 2
                            this.sendEmptyMessageDelayed(1, 500)
                            seekbar_status.text = "Normal download speed."
                        } else if (seekBar.progress > 21 && seekBar.progress < 26) {
                            len += 1
                            this.sendEmptyMessageDelayed(1, 1000)
                            seekbar_status.text = "Sundden speed down or disconnet..."
                        } else {
                            len += 2
                            this.sendEmptyMessageDelayed(1, 50)
                            seekbar_status.text = "Sundden speed up."
                        }
                        seekBar.progress = len

                    }
            }
        }

    }
}
