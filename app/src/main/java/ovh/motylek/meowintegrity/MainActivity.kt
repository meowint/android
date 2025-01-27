package ovh.motylek.meowintegrity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ovh.motylek.meowintegrity.ui.MeowNavigation
import ovh.motylek.meowintegrity.ui.theme.MeowIntegrityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeowIntegrityTheme {
                MeowNavigation()
            }
        }
    }
}