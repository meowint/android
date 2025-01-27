package ovh.motylek.meowintegrity.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.android.play.core.integrity.IntegrityManagerFactory
import com.google.android.play.core.integrity.IntegrityTokenRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.json.Json

@KoinViewModel
class StartViewModel : ViewModel() {

    val isLoading = MutableStateFlow(false)
    val showError = MutableStateFlow(false)
    val errorData = MutableStateFlow("")
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            Json {
                ignoreUnknownKeys = true
            }
        }
    }
    @OptIn(ExperimentalUuidApi::class)
    fun getPlayIntegrityVerdict(
        context: Context
    ) {
        isLoading.update { true }

        val integrityManager = IntegrityManagerFactory.create(context)
        val tokenRequest = IntegrityTokenRequest.builder()
            .setNonce(Uuid.random().toString())
            .setCloudProjectNumber(290635528609)
            .build()
        val tokenTask = integrityManager.requestIntegrityToken(tokenRequest)
        tokenTask.addOnFailureListener {
            showError.update { true }
            errorData.value = it.stackTraceToString()
            isLoading.update { false }
        }
        tokenTask.addOnSuccessListener {

        }

    }
}