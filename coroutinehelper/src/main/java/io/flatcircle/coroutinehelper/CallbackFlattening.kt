package io.flatcircle.coroutinehelper

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Created by jacquessmuts on 2019-05-21
 * TODO: Add a class header comment!
 */

fun functionWithCallback(input: String, callback: (String) -> Unit ) {
    callback(input+input)
}

fun functionWithCallbackTwo(inputOne: String, inputTwo: String, callback: (String) -> Unit ) {
    callback(inputOne+inputTwo)
}

fun callIt() {

    functionWithCallback("hello") { result ->
        println(result)
    }
}

suspend fun flattenedFunctionWithCallback(input: String) = suspendCoroutine<String> { continuation ->

    functionWithCallback(input) { result ->
        continuation.resume(result)
    }

}

suspend fun callFlattened() {

    val result = flattenedFunctionWithCallback("hello")
}

suspend fun callFlattenedGeneric() {

    val result = flattenCircle(::functionWithCallback,"hello")

    val resultTwo = flattenCircle(::functionWithCallbackTwo, "hello", "sir")
}

suspend fun <Input1, Input2, Output> flattenCircle(function: (Input1, Input2, (Output) -> Unit) -> Unit,
                                                   inputOne: Input1, inputTwo: Input2) =  suspendCoroutine<Output> { continuation ->
    function(inputOne, inputTwo) { output ->
        continuation.resume(output)
    }
}

suspend fun <Input, Output> flattenCircle(function: (Input, (Output) -> Unit) -> Unit,
                                          input: Input) =  suspendCoroutine<Output> { continuation ->
    function(input) { output ->
        continuation.resume(output)
    }
}

suspend fun callFlattenedInfix() {

    // Original
    functionWithCallback("hello") { result ->
        println(result)
    }

    // Using Function
    val resultFunction = flattenCircle(::functionWithCallback, "hello")
    println(resultFunction)

    // Using infix notation
    val resultInfix = ::functionWithCallback flatCircledWith "hello"
    println(resultInfix)

    val resultTwo = ::functionWithCallbackTwo flatCircledWith Pair("hello", "sir")
}

private suspend infix fun <Input, Output> ((Input, (Output) -> Unit) -> Unit).flatCircledWith(input: Input): Output {
    return flattenCircle(this, input)
}

private suspend infix fun <Input1, Input2, Output> ((Input1, Input2, (Output) -> Unit) -> Unit).flatCircledWith(pair: Pair<Input1, Input2>): Output {
    return flattenCircle(this, pair.first, pair.second)
}
