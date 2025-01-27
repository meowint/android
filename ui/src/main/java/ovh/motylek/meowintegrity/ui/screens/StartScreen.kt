package ovh.motylek.meowintegrity.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import ovh.motylek.meowintegrity.ui.R
import ovh.motylek.meowintegrity.ui.common.ErrorDialog
import ovh.motylek.meowintegrity.ui.viewmodels.StartViewModel


@Serializable
class Start()

@Composable
fun StartScreen(
    viewModel: StartViewModel = koinViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val isError by viewModel.showError.collectAsStateWithLifecycle()
    val errorText by viewModel.errorData.collectAsStateWithLifecycle()
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        contentAlignment = Alignment.BottomCenter
    ) {
        OutlinedButton(
            onClick = {
                viewModel.getPlayIntegrityVerdict(context)
            },
            enabled = !isLoading,
            modifier = Modifier.padding(16.dp).fillMaxWidth().align(Alignment.BottomCenter)
        ) {
            Text(stringResource(R.string.check))
        }
    }
    if (isError) {
        ErrorDialog(
            errorText = errorText,
            onDismiss = { viewModel.showError.value = false }
        )
    }
}