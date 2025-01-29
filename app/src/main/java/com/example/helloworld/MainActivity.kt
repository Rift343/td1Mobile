package com.example.helloworld

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
            val surname = findViewById<EditText>(R.id.editTextText3)
            val name = findViewById<EditText>(R.id.editTextText2)
            val competency = findViewById<EditText>(R.id.editTextText4)
            val phoneNumber = findViewById<EditText>(R.id.editTextPhone)
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
    var ctx = LocalContext.current
    var showDialog by remember { mutableStateOf(true) }

    HelloWorldTheme {
        if (showDialog) {
            AlertDialogValidation(
                onDismissRequest = { showDialog = false },
                onConfirmation = {
                    val intent = Intent(ctx, RequestCallActivity::class.java)
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
