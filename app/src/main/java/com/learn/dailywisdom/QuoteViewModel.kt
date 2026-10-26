package com.learn.dailywisdom

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {
    private val _quote = MutableStateFlow(Quote(0, "", ""))
    val quote = _quote.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    fun fetchQuote() {
        viewModelScope.launch {
            _loading.value = true
            try {
                _quote.value = RetrofitClient.api.getRandomQuote()
            } catch (e: Exception) {
                _quote.value = Quote(0, "", "")
            } finally {
                _loading.value = false
                Log.d("TAG", "fetchQuote: ${quote.value.quote} -${quote.value.author}")
            }
        }
    }
}