package com.example.helloworld

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge


class CallPhoneActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.call_phone_layout)
        val phoneNumber = intent?.extras?.getString("phoneNumber")
        enableEdgeToEdge()
        val textViewName : TextView = findViewById(R.id.idPhoneNumber)
        textViewName.text = phoneNumber.toString()
        val buttonCall = findViewById<Button>(R.id.button2)
        buttonCall.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            startActivity(intent)

        }


    }
}

