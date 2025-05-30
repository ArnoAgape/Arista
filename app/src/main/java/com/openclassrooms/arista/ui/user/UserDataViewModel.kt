package com.openclassrooms.arista.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
import com.openclassrooms.arista.domain.usecase.DeleteExerciseUseCase
import com.openclassrooms.arista.domain.usecase.GetAllExercisesUseCase
import com.openclassrooms.arista.domain.usecase.GetUserUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDataViewModel @Inject constructor(
    private val getAllUser: GetUserUsecase,
    private val addNewExerciseUseCase: AddNewExerciseUseCase,
    private val deleteExerciseUseCase: DeleteExerciseUseCase,
) : ViewModel() {
    private val _exercisesFlow = MutableStateFlow<List<Exercise>>(emptyList())
    val exercisesFlow: StateFlow<List<Exercise>> = _exercisesFlow.asStateFlow()


    init {
        loadAllExercises()
    }


    fun deleteExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteExerciseUseCase.execute(exercise)
            loadAllExercises()
        }
    }


    private fun loadAllExercises() {
        viewModelScope.launch(Dispatchers.IO) {
            val exercises = getAllExercisesUseCase.execute()
            _exercisesFlow.value = exercises
        }
    }


    fun addNewExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            addNewExerciseUseCase.execute(exercise)
            loadAllExercises()
        }
    }
}
