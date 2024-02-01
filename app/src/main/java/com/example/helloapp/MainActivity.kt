package com.example.helloapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.helloapp.ui.theme.HelloAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier,
             ) {
    Surface(color = MaterialTheme.colorScheme.primary,

        ) { val context = LocalContext.current
        val keyboardController = LocalSoftwareKeyboardController.current
        val tokenValue = remember {
            mutableStateOf(TextFieldValue())
        }
        val store = UserStore(context)
        val tokenText = store.getAccessToken.collectAsState(initial = "")

        Column(
            modifier = Modifier.clickable { keyboardController?.hide() },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Text(text = "DataStorage Example", fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(15.dp))

            Text(text = tokenText.value)

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                value = tokenValue.value,
                onValueChange = { tokenValue.value = it },
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        store.saveToken(tokenValue.value.text)
                    }
                }
            ) {
                Text(text = "Update Token")
            }
        }
    }
    }

class UserStore(context: Context) {
    val getAccessToken: Any

    fun saveToken(text: String) {

    }

}
}
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloAppTheme {
        Greeting("varinder")

        }
    }
