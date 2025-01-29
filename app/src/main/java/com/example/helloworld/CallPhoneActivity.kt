package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.helloworld.ui.theme.HelloWorldTheme

class CallPhoneActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val phoneNumber = intent?.extras?.getString("phoneNumber")
        enableEdgeToEdge()
        setContent{
            HelloWorldTheme{
                ImagePhone(phoneNumber)
            }

        }

    }
}

@Composable
fun ImagePhone(message:String?){
    val image = painterResource(R.drawable.tel)
    Image(
        painter = image,
        contentDescription = message
    )
    Greeting(message.toString())
}