package com.example.helloworld

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.example.helloworld.ui.theme.HelloWorldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fomulaire)

        val buttonValidate = findViewById<Button>(R.id.buttonValidate)
        buttonValidate.setOnClickListener {
            val composeView = ComposeView(this).apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(lifecycle))
                setContent {
                    MyComposeContent()
                }
            }
            addContentView(composeView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        }
    }
}

@Composable
fun MyComposeContent() {
    var showDialog by remember { mutableStateOf(true) }

    HelloWorldTheme {
        if (showDialog) {
            AlertDialogValidation(
                onDismissRequest = { showDialog = false },
                onConfirmation = {
                    println("Confirmation registered")
                    showDialog = false
                },
                dialogTitle = "Alert dialog example",
                dialogText = "This is an example of an alert dialog with buttons."
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
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Refuse")
            }
        }
    )
}
