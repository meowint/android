package ovh.motylek.meowintegrity.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
class About()

@Composable
fun AboutScreen() {
    Text(text = "About")
}