package ui.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Inject
import moe.tlaster.precompose.viewmodel.ViewModel

@Inject
class RequestFeatureViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(RequestFeatureState())
    val uiState: StateFlow<RequestFeatureState> = _uiState.asStateFlow()

    fun updateRequestSubject(subject: String) {
        _uiState.update { currentState ->
            currentState.copy(
                requestSubject = subject
            )
        }
    }

    fun updateRequestBody(body: String) {
        _uiState.update { currentState ->
            currentState.copy(
                requestBody = body
            )
        }
    }
}

data class RequestFeatureState(
    val requestSubject: String = "",
    val requestBody: String = ""
)
