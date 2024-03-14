package ui.viewmodels

import kotlinx.coroutines.flow.StateFlow

interface IBaseViewModel {
    // TODO: Implement this interface for testing purposes
    val uiState: StateFlow<Unit>
}