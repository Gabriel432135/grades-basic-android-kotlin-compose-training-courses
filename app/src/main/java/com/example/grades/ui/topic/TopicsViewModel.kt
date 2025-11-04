package com.example.grades.ui.topic

import androidx.lifecycle.ViewModel
import com.example.grades.model.DataSource
import com.example.grades.model.Topic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Represents the UI state for the topics screen.
 */
data class TopicsUiState(
    val topics: List<Topic> = emptyList()
)

/**
 * ViewModel for the topics screen.
 */
class TopicsViewModel : ViewModel() {

    // Private mutable state flow
    private val _uiState = MutableStateFlow(TopicsUiState())
    // Public immutable state flow
    val uiState: StateFlow<TopicsUiState> = _uiState.asStateFlow()

    init {
        // Load topics when the ViewModel is created
        loadTopics()
    }

    private fun loadTopics() {
        // Get data from the Model (DataSource)
        val topics = DataSource.topics
        // Update the UI state
        _uiState.update {
            it.copy(topics = topics)
        }
    }
}
