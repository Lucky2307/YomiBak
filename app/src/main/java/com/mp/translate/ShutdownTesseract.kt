package com.mp.translate

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ShutdownTesseract: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        var command: String? = p1?.getStringExtra("command")
        val serviceIntent = Intent(p0, TesseractService::class.java)
        val runIntent = Intent(p0, TesseractAPI::class.java)
        Toast.makeText(p0, "Service shutdown", Toast.LENGTH_SHORT).show()
        p0?.stopService(p1)
    }
}