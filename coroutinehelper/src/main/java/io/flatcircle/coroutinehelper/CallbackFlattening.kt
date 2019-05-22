package io.flatcircle.coroutinehelper

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Created by jacquessmuts on 2019-05-21
 * Functions that assist with turning callback functions into suspendCoroutines
 */

// Region One Output
suspend fun <Input, Output> suspendAsync(function: (Input, (Output) -> Unit) -> Unit,
                                         input: Input) =  suspendCoroutine<Output> { continuation ->
    function(input) { output ->
        continuation.resume(output)
    }
}

suspend fun <Input1, Input2, Output> suspendAsync(function: (Input1, Input2, (Output) -> Unit) -> Unit,
                                                  input1: Input1, input2: Input2) =  suspendCoroutine<Output> { continuation ->
    function(input1, input2) { output ->
        continuation.resume(output)
    }
}

suspend fun <Input1, Input2, Input3, Output> suspendAsync(function: (Input1, Input2, Input3, (Output) -> Unit) -> Unit,
                                                  input1: Input1, input2: Input2, input3: Input3) =  suspendCoroutine<Output> { continuation ->
    function(input1, input2, input3) { output ->
        continuation.resume(output)
    }
}

private suspend infix fun <Input, Output> ((Input, (Output) -> Unit) -> Unit).suspendAndInvokeWith(input: Input): Output {
    return suspendAsync(this, input)
}

private suspend infix fun <Input1, Input2, Output> ((Input1, Input2, (Output) -> Unit) -> Unit).suspendAndInvokeWith(pair: Pair<Input1, Input2>): Output {
    return suspendAsync(this, pair.first, pair.second)
}

private suspend infix fun <Input1, Input2, Input3, Output> ((Input1, Input2, Input3, (Output) -> Unit) -> Unit).suspendAndInvokeWith(triple: Triple<Input1, Input2, Input3>): Output {
    return suspendAsync(this, triple.first, triple.second, triple.third)
}

// Region Two Output

suspend fun <Input, Output1, Output2> suspendAsyncPairResult(function: (Input, (Output1, Output2) -> Unit) -> Unit,
                                                       input: Input) =  suspendCoroutine<Pair<Output1, Output2>> { continuation ->
    function(input) { output1, output2 ->
        continuation.resume(Pair(output1, output2))
    }
}

suspend fun <Input1, Input2, Output1, Output2> suspendAsyncPairResult(function: (Input1, Input2, (Output1, Output2) -> Unit) -> Unit,
                                                                      inputOne: Input1, inputTwo: Input2) =  suspendCoroutine<Pair<Output1, Output2>> { continuation ->
    function(inputOne, inputTwo) { output1, output2 ->
        continuation.resume(Pair(output1, output2))
    }
}

suspend fun <Input1, Input2, Input3, Output1, Output2> suspendAsyncPairResult(function: (Input1, Input2, Input3, (Output1, Output2) -> Unit) -> Unit,
                                                                      input1: Input1, input2: Input2, input3: Input3) =  suspendCoroutine<Pair<Output1, Output2>> { continuation ->
    function(input1, input2, input3) { output1, output2 ->
        continuation.resume(Pair(output1, output2))
    }
}

private suspend infix fun <Input, Output1, Output2> ((Input, (Output1, Output2) -> Unit) -> Unit).suspendAndInvokeWith2(input: Input): Pair<Output1, Output2> {
    return suspendAsyncPairResult(this, input)
}

private suspend infix fun <Input1, Input2, Output1, Output2> ((Input1, Input2, (Output1, Output2) -> Unit) -> Unit).suspendAndInvokeWith2(pair: Pair<Input1, Input2>): Pair<Output1, Output2> {
    return suspendAsyncPairResult(this, pair.first, pair.second)
}

private suspend infix fun <Input1, Input2, Input3, Output1, Output2> ((Input1, Input2, Input3, (Output1, Output2) -> Unit) -> Unit).suspendAndInvokeWith2(triple: Triple<Input1, Input2, Input3>): Pair<Output1, Output2> {
    return suspendAsyncPairResult(this, triple.first, triple.second, triple.third)
}

// Region Three Output

suspend fun <Input, Output1, Output2, Output3> suspendAsyncTripleResult(function: (Input, (Output1, Output2, Output3) -> Unit) -> Unit,
                                                             input: Input) =  suspendCoroutine<Triple<Output1, Output2, Output3>> { continuation ->
    function(input) { output1, output2, output3 ->
        continuation.resume(Triple(output1, output2, output3))
    }
}

suspend fun <Input1, Input2, Output1, Output2, Output3> suspendAsyncTripleResult(function: (Input1, Input2, (Output1, Output2, Output3) -> Unit) -> Unit,
                                                                      inputOne: Input1, inputTwo: Input2) =  suspendCoroutine<Triple<Output1, Output2, Output3>> { continuation ->
    function(inputOne, inputTwo) { output1, output2, output3 ->
        continuation.resume(Triple(output1, output2, output3))
    }
}

suspend fun <Input1, Input2, Input3, Output1, Output2, Output3> suspendAsyncTripleResult(function: (Input1, Input2, Input3, (Output1, Output2, Output3) -> Unit) -> Unit,
                                                                              input1: Input1, input2: Input2, input3: Input3) =  suspendCoroutine<Triple<Output1, Output2, Output3>> { continuation ->
    function(input1, input2, input3) {output1, output2, output3 ->
        continuation.resume(Triple(output1, output2, output3))
    }
}

private suspend infix fun <Input, Output1, Output2, Output3> ((Input, (Output1, Output2, Output3) -> Unit) -> Unit).suspendAndInvokeWith3(input: Input): Triple<Output1, Output2, Output3> {
    return suspendAsyncTripleResult(this, input)
}

private suspend infix fun <Input1, Input2, Output1, Output2, Output3> ((Input1, Input2, (Output1, Output2, Output3) -> Unit) -> Unit).suspendAndInvokeWith3(pair: Pair<Input1, Input2>): Triple<Output1, Output2, Output3> {
    return suspendAsyncTripleResult(this, pair.first, pair.second)
}

private suspend infix fun <Input1, Input2, Input3, Output1, Output2, Output3> ((Input1, Input2, Input3, (Output1, Output2, Output3) -> Unit) -> Unit).suspendAndInvokeWith3(triple: Triple<Input1, Input2, Input3>): Triple<Output1, Output2, Output3> {
    return suspendAsyncTripleResult(this, triple.first, triple.second, triple.third)
}