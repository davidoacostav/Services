package com.example.services.ui

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class BoundService : Service() {
    private val binder = Binder()

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    fun fizzBuzzer(): Flow<String> {
        return flow {
            for (i in 1..1500) {
                val fizzBuzz = fizzBuzz(i)
                emit(fizzBuzz)
            }
        }
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






    inner class MyBinder : Binder() {
        fun getService() = this@BoundService
    }


}