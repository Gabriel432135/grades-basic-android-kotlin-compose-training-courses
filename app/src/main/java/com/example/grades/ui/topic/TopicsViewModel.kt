// viewmodel/TopicViewModel.kt
package com.example.grades.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grades.model.Topic
import com.example.grades.model.TopicRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// O ViewModel herda de androidx.lifecycle.ViewModel
class TopicViewModel : ViewModel() {

    private val repository = TopicRepository()

    // 1. STATEFLOW PRIVADO E MUTÁVEL:
    //    Armazena o estado atual da lista de tópicos. Só pode ser modificado DENTRO do ViewModel.
    private val _topics = MutableStateFlow<List<Topic>>(emptyList())

    // 2. STATEFLOW PÚBLICO E IMUTÁVEL (READ-ONLY):
    //    A UI (View) irá observar este StateFlow para receber atualizações de forma reativa.
    //    Ele é uma versão pública e somente leitura do _topics.
    val topics: StateFlow<List<Topic>> = _topics.asStateFlow()

    // O bloco init é chamado quando o ViewModel é criado pela primeira vez.
    init {
        loadTopics()
    }

    // 3. FUNÇÃO PARA CARREGAR OS DADOS:
    //    Usa Coroutines (viewModelScope) para buscar os dados em uma thread de fundo. [1]
    private fun loadTopics() {
        // viewModelScope é um CoroutineScope ligado ao ciclo de vida do ViewModel.
        // A corrotina será cancelada automaticamente se o ViewModel for destruído.
        viewModelScope.launch {
            // Obtém os dados do repositório
            val topicList = repository.getTopics()
            // Atualiza o valor do StateFlow, o que notificará a UI automaticamente.
            _topics.value = topicList
        }
    }
}
