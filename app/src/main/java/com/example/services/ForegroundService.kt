package com.example.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ForegroundService: Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        CoroutineScope(Dispatchers.Main).launch {
            for (i in 1..15000) {
                val fizzBuzz = fizzBuzz(i)
                val notification = NotificationCompat.Builder(this@ForegroundService, CHANNEL_ID)
                    .setContentTitle("FizzFuzz Foreground Service")
                    .setContentText("$i is $fizzBuzz !!")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .build()

                startForeground(1, notification)
                delay(5000)
            }
        }
        return START_STICKY
    }

    private fun fizzBuzz(number: Int): String {
        val value = when {
            number % 3 == 0 -> "fizz"
            number % 5 == 0 -> "buzz"
            number % 3 == 0 && number % 5 == 0 -> "fizz-buzz"
            else -> "no fizz nor buzz"

        }
        return value
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager
            manager.createNotificationChannel(serviceChannel)
        }
    }

    companion object {
        const val CHANNEL_ID = "Foreground Service Channel"
    }

}
