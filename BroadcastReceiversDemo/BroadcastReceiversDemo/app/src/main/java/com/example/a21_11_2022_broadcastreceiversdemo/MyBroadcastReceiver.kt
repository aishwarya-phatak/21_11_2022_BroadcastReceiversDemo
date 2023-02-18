package com.example.a21_11_2022_broadcastreceiversdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
       val modeChanged = intent?.getBooleanExtra("state",false) ?:return
        if(modeChanged){
            Toast.makeText(context,"Mode ON",Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context,"Mode OFF",Toast.LENGTH_LONG).show()
        }

        val batteryStatus = intent.getBooleanExtra("status",true)?:return
        if(batteryStatus){
            Toast.makeText(context,"Battery Low",Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context,"Battery not low",Toast.LENGTH_LONG).show()
        }
    }
}