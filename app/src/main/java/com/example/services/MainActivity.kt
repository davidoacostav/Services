package com.example.services

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.services.ui.theme.ServicesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ServicesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyButtons(
                        foregroundService = {
                            Intent(this, ForegroundService::class.java).also {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    startForegroundService(it)
                                    Toast.makeText(this,"Foreground clicked",Toast.LENGTH_LONG).show()
                                }
                            }
                        },
                        backgroundService = {
                            Intent(this, BackgroundService::class.java).also {
                                startService(it)
                            }
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun MyButtons(backgroundService: () -> Unit, foregroundService: () -> Unit) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            Button(onClick = { backgroundService.invoke() }) {
                Text(text = "Background Service")
            }
            Button(onClick = { foregroundService.invoke() }) {
                Text(text = "Foreground Service")
            }

        }
    }
}

