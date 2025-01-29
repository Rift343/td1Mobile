package com.example.helloworld

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.helloworld.ui.theme.HelloWorldTheme

class RequestCallActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirmation_layout)
        enableEdgeToEdge()
        val name = intent?.extras?.getString("name").toString()
        val surname = intent?.extras?.getString("surname").toString()
        val competency = intent?.extras?.getString("competency").toString()
        val phoneNumber = intent?.extras?.getString("phoneNumber").toString()
        val textViewName : TextView = findViewById(R.id.textView2)
        val textViewSurname : TextView = findViewById(R.id.textView5)
        val textViewCompetency : TextView = findViewById(R.id.textView8)
        val textViewPhoneNumber : TextView = findViewById(R.id.textView9)
        textViewName.text = name
        textViewSurname.text = surname
        textViewCompetency.text = competency
        textViewPhoneNumber.text = phoneNumber

        val buttonOK = findViewById<Button>(R.id.button3)//on recupère le button pour continuer
        val buttonReturn = findViewById<Button>(R.id.button4)//on recupére le button de retour
        //Maintenant on créé pour ces buttons de listener pour réaliser les bonnes actions

        buttonOK.setOnClickListener {
            val intentOK = Intent(this,CallPhoneActivity::class.java)
            intentOK.putExtra("phoneNumber",phoneNumber)
            startActivity(intentOK)
        }

        buttonReturn.setOnClickListener {
            var intentReturn = Intent(this,MainActivity::class.java)
            startActivity(intentReturn)
        }




    }
}



