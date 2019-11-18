package com.example.mywheaterapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel: ViewModel(),CoroutineScope {

    private val job = SupervisorJob()
    //protected val ioScope = CoroutineScope(Dispatchers.Default)

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
       // ioScope.coroutineContext.cancel()
    }
}