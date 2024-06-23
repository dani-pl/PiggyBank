package com.danipl.piggybank.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.danipl.piggybank.Greeting
import com.danipl.piggybank.android.theme.PiggyBankTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PiggyBankTheme {
                Greeting()
            }
        }
    }
}
