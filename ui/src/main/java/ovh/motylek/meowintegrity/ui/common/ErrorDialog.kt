@file:Suppress("DEPRECATION")

package ovh.motylek.meowintegrity.ui.common

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.coroutines.flow.Flow
import ovh.motylek.meowintegrity.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorDialog(
    errorText: String,
    onDismiss: () -> Unit
) {
    val clipboardManager = getSystemService(LocalContext.current, ClipboardManager::class.java) as ClipboardManager
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = { Icon(Icons.Filled.Error, contentDescription = null) },
        title = { Text(text = stringResource(R.string.error)) },
        text = {
            Box(
                modifier = Modifier.height(150.dp).verticalScroll(rememberScrollState())
            ) {
                Text(text = errorText)
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    clipboardManager.setPrimaryClip(ClipData.newPlainText("", errorText))
                }
            ) {
                Text(text = stringResource(R.string.copy))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(R.string.ok))
            }
        }
    )
}
