package ovh.motylek.meowintegrity.ui.viewmodels

import androidx.lifecycle.ViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class StartViewModel : ViewModel() {
    init {
        println("Viewmodels worke")
    }
}