package com.example.helloworld

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.example.helloworld.ui.theme.HelloWorldTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fomulaire)

        val buttonValidate = findViewById<Button>(R.id.buttonValidate)
        buttonValidate.setOnClickListener {
            var ctx = LocalContext
            val surname = findViewById<EditText>(R.id.editTextText3).text
            val name = findViewById<EditText>(R.id.editTextText2).text
            val competency = findViewById<EditText>(R.id.editTextText4).text
            val phoneNumber = findViewById<EditText>(R.id.editTextPhone).text

            if (surname.toString()=="" ||name.toString()==""||competency.toString()==""||phoneNumber.toString()=="")
            {
                val toast = Toast.makeText(this, R.string.notComplete, Toast.LENGTH_LONG) // in Activity
                toast.show()
                return@setOnClickListener

            }

            val composeView = ComposeView(this).apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(lifecycle))
                setContent {
                    MyComposeContent(name.toString(),surname.toString(),competency.toString(),phoneNumber.toString())
                }
            }
            addContentView(composeView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

        }
    }
}

@Composable
fun MyComposeContent(name: String, surname: String, competency: String, phoneNumber : String) {
    var ctx = LocalContext.current
    var showDialog by remember { mutableStateOf(true) }

    HelloWorldTheme {
        if (showDialog) {
            AlertDialogValidation(
                onDismissRequest = { showDialog = false },
                onConfirmation = {
                    val intent = Intent(ctx, RequestCallActivity::class.java)
                    intent.putExtra("name", name)
                    intent.putExtra("surname", surname)
                    intent.putExtra("competency", competency)
                    intent.putExtra("phoneNumber", phoneNumber)


                    ctx.startActivity(intent)
                    showDialog = false
                },
                dialogTitle = ctx.getString(R.string.confirmed),
                dialogText =ctx.getString(R.string.confirmationMessage)
            )
        }
    }
}

@Composable
fun AlertDialogValidation(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String
) {
    var ctx = LocalContext.current
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(ctx.getString(R.string.confirmed))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(ctx.getString(R.string.refused))
            }
        }
    )
}
