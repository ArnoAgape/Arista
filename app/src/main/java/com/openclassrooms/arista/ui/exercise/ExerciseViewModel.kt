package com.openclassrooms.arista.ui.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
import com.openclassrooms.arista.domain.usecase.DeleteExerciseUseCase
import com.openclassrooms.arista.domain.usecase.GetAllExercisesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val getAllExercisesUseCase: GetAllExercisesUseCase,
    private val addNewExerciseUseCase: AddNewExerciseUseCase,
    private val deleteExerciseUseCase: DeleteExerciseUseCase,
) : ViewModel() {

    private val _exercisesFlow = MutableStateFlow<List<Exercise>>(emptyList())
    val exercisesFlow: StateFlow<List<Exercise>> = _exercisesFlow.asStateFlow()

    private val _errorFlow = MutableStateFlow<String?>(null)
    val errorFlow: StateFlow<String?> = _errorFlow.asStateFlow()

    init {
        loadAllExercises()
    }

    fun deleteExercise(exercise: Exercise) {
        viewModelScope.launch {
            val result = deleteExerciseUseCase.execute(exercise)
            if (result.isSuccess) {
                loadAllExercises()
            } else {
                _errorFlow.value = result.exceptionOrNull()?.message ?: "Error while deleting exercises"
            }
        }
    }

    private fun loadAllExercises() {
        viewModelScope.launch {
            val result = getAllExercisesUseCase.execute()
            if (result.isSuccess) {
                _exercisesFlow.value = result.getOrNull() ?: emptyList()
            } else {
                _errorFlow.value = result.exceptionOrNull()?.message ?: "Error loading the exercises"
            }
        }
    }

    fun addNewExercise(exercise: Exercise) {
        viewModelScope.launch {
            val result = addNewExerciseUseCase.execute(exercise)
            if (result.isSuccess) {
                loadAllExercises()
            } else {
                _errorFlow.value = result.exceptionOrNull()?.message ?: "Error while adding an exercise"
            }
        }
    }
}

