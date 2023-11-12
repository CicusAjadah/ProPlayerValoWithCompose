package com.tugas.proplayervalorantwithcompose

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tugas.proplayervalorantwithcompose.data.PlayerRepository
import com.tugas.proplayervalorantwithcompose.models.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProPlayerViewModel(private val repository: PlayerRepository) : ViewModel() {
    private val _groupedPlayers = MutableStateFlow(
        repository.getPlayers()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupedPlayers: StateFlow<Map<Char, List<Player>>> get() = _groupedPlayers

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedPlayers.value = repository.searchPlayes(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }
}

class ViewModelFactory(private val repository: PlayerRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProPlayerViewModel::class.java)) {
            return ProPlayerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}