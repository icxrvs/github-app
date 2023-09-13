package com.example.core.usecases.base

import com.example.core.util.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

abstract class UseCase<in P, R>{
    operator fun invoke(params: P): Flow<Status<R>> = flow{
        emit(Status.Loading)
        emit(doWork(params))
    }.catch { cause: Throwable ->  
        emit(Status.Error(cause))
    }
    protected abstract suspend fun doWork(params: P): Status<R>
}