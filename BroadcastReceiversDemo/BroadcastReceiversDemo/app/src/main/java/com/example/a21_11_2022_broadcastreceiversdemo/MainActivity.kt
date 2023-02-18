package com.example.a21_11_2022_broadcastreceiversdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a21_11_2022_broadcastreceiversdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var myBroadcastReceiver : MyBroadcastReceiver? = null

    var brDownload = object : BroadcastReceiver(){

        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                val modeChanged = intent?.getBooleanExtra("state",false) ?:return
                if(modeChanged){
                    Toast.makeText(context,"Mode ON",Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context,"Mode OFF",Toast.LENGTH_LONG).show()
                }
                val path = intent.getStringExtra("path")
                binding.txtPath.text = path
                Toast.makeText(context,"download",Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegisterDownload.setOnClickListener {
            var intentFilter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            intentFilter.addAction("in.bitcode.DOWNLOAD_COMPLETE")
            registerReceiver(brDownload, intentFilter)
        }

        binding.btnRegister.setOnClickListener {
            myBroadcastReceiver = MyBroadcastReceiver()
            var intentFilter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            intentFilter.addAction(Intent.ACTION_BATTERY_LOW)
            intentFilter.addAction(Intent.ACTION_LOCALE_CHANGED)
            intentFilter.addAction(Intent.ACTION_WALLPAPER_CHANGED)
            intentFilter.addAction("in.bitcode.DOWNLOAD_COMPLETE")
            registerReceiver(myBroadcastReceiver,intentFilter)
        }

        binding.btnUnregister.setOnClickListener {
            unregisterReceiver(myBroadcastReceiver)
        }
    }

    override fun onDestroy() {
        unregisterReceiver(myBroadcastReceiver)
        super.onDestroy()
    }
}