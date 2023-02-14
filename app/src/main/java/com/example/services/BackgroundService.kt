package com.example.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class BackgroundService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        for (i in 1..1500) {
            val fizzBuzz = fizzBuzz(i)
            Log.i(TAG, "$i is $fizzBuzz")
        }
        return START_STICKY
    }


    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
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

    companion object {
        const val TAG = "BACKGROUND_SERVICE"
    }

}
