package com.thies.hw6_exercise2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.thies.hw6_exercise1.Crime
import com.thies.hw6_exercise1.CrimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class CrimeDetailViewModel(crimeId: UUID) : ViewModel() {
    private val crimeRepository = CrimeRepository.get()

    private val _crime: MutableStateFlow<Crime?> = MutableStateFlow(null)
    val crime: StateFlow<Crime?> = _crime.asStateFlow()

    init {
        viewModelScope.launch {
            _crime.value = crimeRepository.getCrime(crimeId)
        }
    }

    fun updateCrime(onUpdate: (Crime) -> Crime) {
        _crime.update {
            oldCrime ->
            oldCrime?.let {onUpdate(it)}
        }
    }
}

class CrimeDetailViewModelFactory(private val crimeId: UUID) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        return CrimeDetailViewModel(crimeId) as T
    }
}