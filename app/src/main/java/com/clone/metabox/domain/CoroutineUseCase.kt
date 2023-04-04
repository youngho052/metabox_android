package com.clone.metabox.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import com.clone.metabox.result.Result
import timber.log.Timber

abstract class UseCase <in P,R> (private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    Result.Success(it)
                }
            }
        } catch (e: Exception) {
            Timber.d(e)
            Result.Error(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}