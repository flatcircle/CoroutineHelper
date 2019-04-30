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

| Delegate  | Description | Example |
| ------------- | ------------- | ------------- |
| : CoroutineScope by MainCoroutineScope | Implements a coroutine scope in the class on the Main/UI Dispatcher  | [Example](https://github.com/flatcircle/LiveDataHelper/blob/master/app/src/main/java/io/flatcircle/livedatahelperexample/MainActivity.kt#L34)  |
| : CoroutineScope by DefaultCoroutineScope | Implements a coroutine scope in the class on the Default Dispatcher  | [Example](https://github.com/flatcircle/LiveDataHelper/blob/master/app/src/main/java/io/flatcircle/livedatahelperexample/MainActivity.kt#L34)  |
| : CoroutineScope by IoCoroutineScope | Implements a coroutine scope in the class on the IO Dispatcher  | [Example](https://github.com/flatcircle/LiveDataHelper/blob/master/app/src/main/java/io/flatcircle/livedatahelperexample/MainActivity.kt#L34)  |
