package cz.vican.scratchapp.system

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cz.vican.scratchapp.device.GlobalNavigationController
import cz.vican.scratchapp.feature.main.system.MainScreen
import cz.vican.scratchapp.ui.theme.ScratchAppTheme

class MainActivity : ComponentActivity() {

    private val navigationController by lazy {
        GlobalNavigationController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScratchAppTheme {
                NavGraph()
            }


//            ScratchAppTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScratchAppTheme {
        Greeting("Android")
    }
}