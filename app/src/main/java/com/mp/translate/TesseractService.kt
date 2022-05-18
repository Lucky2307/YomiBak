package com.mp.translate

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Environment
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.googlecode.tesseract.android.TessBaseAPI
import java.io.File

class TesseractService: Service() {


    override fun onCreate() {

        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(this, 0, notificationIntent,
                    PendingIntent.FLAG_IMMUTABLE)
            }

        val runIntent = Intent(this, NotificationReceiver::class.java).putExtra("command", "run")
        val runPendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0, runIntent, 0)

        val shutdownIntent = Intent(this, ShutdownTesseract::class.java).putExtra("command", "shutdown")
        val shutdownPendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0, shutdownIntent, PendingIntent.FLAG_CANCEL_CURRENT)

        val notification: Notification = NotificationCompat.Builder(this, "TesseractServiceChannel")
            .setContentTitle("Tesseract Service")
            .setContentText("Yomi is running")
            .setSmallIcon(R.drawable.ic_baseline_android_24)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_baseline_android_24, "Run", runPendingIntent)
            .addAction(R.drawable.ic_baseline_android_24, "Shutdown", shutdownPendingIntent)
            .build()

// Notification ID cannot be 0.
        startForeground(1, notification)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        stopSelf()
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}