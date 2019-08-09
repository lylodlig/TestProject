package com.lzy.testproject.other.secretcode

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony.Sms.Intents.SECRET_CODE_ACTION
import android.util.Log
import com.lzy.testproject.test.TestActivity

// 拨号输入监听
class SecretCodeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("lzy","收到SecretCodeReceiver")
        if (intent != null && SECRET_CODE_ACTION == intent.action) {
            val i = Intent(context, TestActivity::class.java!!)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context?.startActivity(i)
        }
    }
}