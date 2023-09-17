package com.example.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Category
import com.example.domain.usecase.GetMeals
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(private val getMealsUseCase: GetMeals) : ViewModel() {
    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories = _categories.asStateFlow()

    // fun get meals from use case
    fun getMeals() {
        viewModelScope.launch {
            try {
                _categories.value = getMealsUseCase().categories
            } catch (e: Exception) {
                Log.e("Meals viewModel", e.message.toString())
            }
        }

    }
}