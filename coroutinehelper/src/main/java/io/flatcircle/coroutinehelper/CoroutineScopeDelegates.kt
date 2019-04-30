package io.flatcircle.coroutinehelper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

/**
 * Created by jacquessmuts on 2019-04-30
 * TODO: Add a class header comment!
 */


/**
 * Created by jacquessmuts on 2019-04-10
 * delegate classes to be used by anything that implements CoroutineScope
 */

/**
 * Use for services and background tasks. Will probably be replaced by "DefaultScope()" delegate
 */
open class DefaultCoroutineScope : CoroutineScope {
    val job: Job by lazy { SupervisorJob() }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    fun clearJobs() {
        coroutineContext.cancelChildren()
    }
}

/**
 * Use for Views, Viewmodels and things that operate on the Main/UI thread. Will probably be replaced by "MainScope()" delegate
 */
open class MainCoroutineScope : CoroutineScope {
    val job: Job by lazy { SupervisorJob() }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    protected fun clearJobs() {
        coroutineContext.cancelChildren()
    }
}

/**
 * Use for Repositories, database and things that operate on the IO thread. Will probably be replaced by "IoScope()" delegate
 */
open class IoCoroutineScope : CoroutineScope {
    val job: Job by lazy { SupervisorJob() }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    protected fun clearJobs() {
        coroutineContext.cancelChildren()
    }
}