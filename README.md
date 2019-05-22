# CoroutineHelper
Extension functions, utility functions and delegates for Coroutines

[![CircleCI](https://circleci.com/gh/flatcircle/CoroutineHelper.svg?style=svg)](https://circleci.com/gh/flatcircle/CoroutineHelper) [ ![Download](https://api.bintray.com/packages/flatcircle/CoroutineHelper/coroutinehelper/images/download.svg) ](https://bintray.com/flatcircle/CoroutineHelper/coroutinehelper/_latestVersion)

Installation
--------

```groovy
implementation 'io.flatcircle:coroutinehelper:{version}'
```

Usage
-----

The library comes with a few delegate functions to make declaring your CoroutineScope easier

| Delegate  | Description | Example |
| ------------- | ------------- | ------------- |
| : CoroutineScope by MainCoroutineScope | Implements a coroutine scope in the class on the Main/UI Dispatcher  | [Example](https://github.com/flatcircle/LiveDataHelper/blob/master/app/src/main/java/io/flatcircle/livedatahelperexample/MainActivity.kt#L34)  |
| : CoroutineScope by DefaultCoroutineScope | Implements a coroutine scope in the class on the Default Dispatcher  | [Example](https://github.com/flatcircle/LiveDataHelper/blob/master/app/src/main/java/io/flatcircle/livedatahelperexample/MainActivity.kt#L34)  |
| : CoroutineScope by IoCoroutineScope | Implements a coroutine scope in the class on the IO Dispatcher  | [Example](https://github.com/flatcircle/LiveDataHelper/blob/master/app/src/main/java/io/flatcircle/livedatahelperexample/MainActivity.kt#L34)  |

The library also has some convenience functions for reducing callback hell.

```kotlin
    // The original callback function. This could be anything
    functionWithCallback1("hello") { result ->
        println(result)
    }

    // Using suspendAsync function
    val result = suspendAsync(::functionWithCallback1, "hello")
    println(result)

    // You can also call a function of an instance of a class with instance::method reference, like so
    val userInstance = User.getInstance()
    val result = suspendAsync(userInstance::functionWithCallback1, "hello")
    println(result)

    // Using infix notation
    val result = ::functionWithCallback1 suspendAndInvokeWith "hello"
    println(result)

    // You can have up to three inputs, but you have to pass in a Pair()/Triple when using infix
    val result = ::functionWithCallback2 suspendAndInvokeWith Pair("hello", 2)
    val result = ::functionWithCallback3 suspendAndInvokeWith Triple("Hello", 2, "goodbye")
    println(result)

    // You can have up to three outputs using suspendAndInvokeWith2/3, but you have to receive it as a pair/triple
    val (result1, result2) = ::functionWithCallback22 suspendAndInvokeWith2 Pair("hello", 2)
    val (result1, result2, result3) = ::functionWithCallback33 suspendAndInvokeWith3 Triple("hello", 2, "goodbye")
    println(result1 + result2 + result3)

```

You can find some additional reading on the callback hell functions [in this blog post](https://jacquessmuts.github.io/post/callback_hell/)
