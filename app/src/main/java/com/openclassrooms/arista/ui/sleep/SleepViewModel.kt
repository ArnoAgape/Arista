package com.openclassrooms.arista.ui.sleep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
import com.openclassrooms.arista.domain.usecase.DeleteExerciseUseCase
import com.openclassrooms.arista.domain.usecase.GetAllSleepsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SleepViewModel @Inject constructor(
    private val getAllSleepsUseCase: GetAllSleepsUseCase,
    private val addNewSleepUseCase: AddNewExerciseUseCase,
    private val deleteSleepUseCase: DeleteExerciseUseCase,
) : ViewModel() {
    private val _sleepsFlow = MutableStateFlow<List<Sleep>>(emptyList())
    val sleepsFlow: StateFlow<List<Sleep>> = _sleepsFlow.asStateFlow()


    init {
        loadAllSleeps()
    }


    fun deleteSleep(sleep: Sleep) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteSleepUseCase.execute(sleep)
            loadAllSleeps()
        }
    }


    private fun loadAllSleeps() {
        viewModelScope.launch(Dispatchers.IO) {
            val sleeps = getAllSleepsUseCase.execute()
            _sleepsFlow.value = sleeps
        }
    }


    fun addNewExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            addNewSleepUseCase.execute(exercise)
            loadAllSleeps()
        }
    }
}
