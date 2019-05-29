package io.flatcircle.coroutinehelper

/**
 * Created by jacquessmuts on 2019-05-28
 * This class represent a result of an api call. It's a wrapper similar to Result<> in
 * Kotlin-experimental, or Swift. It's got the added benefit of doing a (context free) message
 * based on the exception it is provided.
 *
 * To handle the results in a railway manner, read more [here](https://proandroiddev.com/railway-oriented-programming-in-kotlin-f1bceed399e5)
 */

sealed class ApiResult<T> {

    companion object {

        /**
         * This maps a given ApiResult into the newly required ApiResult<T>. You just have to pass it the (nullable)
         * successvalue.
         *
         * Please note that if you pass Failure result, this will return a Failure regardless of successValue
         */
        fun <T> fromResult(originalResult: ApiResult<*>, successValue: T?): ApiResult<T> {
            return if (originalResult is Success && successValue != null) {
                Success(successValue)
            } else if (originalResult is Failure) {
                Failure(originalResult.error)
            } else {
                Failure(IllegalArgumentException("Cannot convert $originalResult without successvalue, $successValue"))
            }
        }

        fun <T> purposefullyLeftEmpty(): ApiResult<T> {
            return Failure(PurposeFullyLeftEmptyException("This API call is not implemented on this specific client."))
        }

        fun <T> notImplemented(): ApiResult<T> {
            return Failure(NotImplementedError())
        }

        fun <T> success(value: T): ApiResult<T> {
            return Success(value)
        }

        fun <T> fail(error: Throwable): ApiResult<T> {
            return Failure(error)
        }
    }

    /**
     * Returns `true` if this instance represents successful outcome.
     * In this case [isFailure] returns `false`.
     */
    val isSuccess: Boolean get() = this !is Failure

    /**
     * Returns `true` if this instance represents failed outcome.
     * In this case [isSuccess] returns `false`.
     */
    val isFailure: Boolean get() = this is Failure

    // value & exception retrieval

    /**
     * Returns the encapsulated value if this instance represents [success][Result.isSuccess] or `null`
     * if it is [failure][Result.isFailure].
     */
    fun getOrNull(): T? =
        when (this) {
            is Failure -> null
            is Success -> value
        }

    /**
     * Returns the encapsulated exception if this instance represents [failure][isFailure] or `null`
     * if it is [success][isSuccess].
     *
     * This function is shorthand for `fold(onSuccess = { null }, onFailure = { it })` (see [fold]).
     */
    fun exceptionOrNull(): Throwable? =
        when (this) {
            is Failure -> error
            else -> null
        }
}
data class Success<T>(val value: T) : ApiResult<T>()
data class Failure<T>(val error: Throwable) : ApiResult<T>()

// -- extensions ---

/**
 * Run the function to return the result, or pass on the failure
 */
suspend infix fun <T> ApiResult<T>.then(function: suspend (Success<T>) -> ApiResult<T>): ApiResult<T> =
    when (this) {
        is Success -> function(this)
        is Failure -> this
    }

/**
 * Run the function to return the result, or pass on the failure
 */
infix fun <T> ApiResult<T>.thenBlocking(function: (Success<T>) -> ApiResult<T>): ApiResult<T> =
    when (this) {
        is Success -> function(this)
        is Failure -> this
    }

/**
 * Run the function, and pass the value on regardless
 */
suspend infix fun <T> ApiResult<T>.onSuccess(function: suspend (T) -> Unit): ApiResult<T> =
    when (this) {
        is Success -> {
            function(this.value)
            this
        }
        is Failure -> this
    }

/**
 * Run the function, and pass the value on regardless
 */
infix fun <T> ApiResult<T>.onSuccessBlocking(function: (T) -> Unit): ApiResult<T> =
    when (this) {
        is Success -> {
            function(this.value)
            this
        }
        is Failure -> this
    }

/**
 * Pass on the success, or handle the failure
 */
suspend infix fun <T> ApiResult<T>.onFail(function: suspend (Throwable) -> Unit): ApiResult<T> =
    when (this) {
        is Success -> this
        is Failure -> {
            function(this.error)
            this
        }
    }

/**
 * Run the function, and pass the value on
 */
infix fun <T> ApiResult<T>.onFailBlocking(function: (Throwable) -> Unit): ApiResult<T> =
    when (this) {
        is Success -> this
        is Failure -> {
            function(this.error)
            this
        }
    }

/**
 * do something regardless of success or failure
 */
suspend infix fun <T> ApiResult<T>.finally(function: suspend (ApiResult<T>) -> Unit): ApiResult<T> {
    function(this)
    return this
}

/**
 * do something regardless of success or failure
 */
infix fun <T> ApiResult<T>.finallyBlocking(function: (ApiResult<T>) -> Unit): ApiResult<T> {
    function(this)
    return this
}

// infix fun <T,U> T.to(function: (T) -> ApiResult<U>) = Success(this).then(function)

class PurposeFullyLeftEmptyException(override val message: String?) : Exception()
