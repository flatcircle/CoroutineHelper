# ViewHelpers
Extension functions, utility functions and delegates for Views

[![CircleCI](https://circleci.com/gh/flatcircle/CoroutineHelper.svg?style=svg)](https://circleci.com/gh/flatcircle/CoroutineHelper) [ ![Download](https://api.bintray.com/packages/flatcircle/ViewHelper/viewhelper/images/download.svg) ](https://bintray.com/flatcircle/ViewHelper/viewhelper/_latestVersion)

Installation
--------

```groovy
implementation 'io.flatcircle:coroutinehelper:{version}'
```


Usage
-----

| Function  | Description | Example |
| ------------- | ------------- | ------------- |
| ViewHelperUtil.hideSoftKeyboard() | Hides the software keyboard if it is up | [Example](https://github.com/flatcircle/LiveDataHelper/blob/master/app/src/main/java/io/flatcircle/livedatahelperexample/MainActivity.kt#L34)  |
| ViewHelperUtil.showSoftKeyboard() | Opens the software keyboard if it is down | [Example](https://github.com/flatcircle/LiveDataHelper/blob/master/app/src/main/java/io/flatcircle/livedatahelperexample/MainActivity.kt#L34) |
| ViewHelperUtil.getPxFromDp() | Calculates pixels from dp input | [Example](https://github.com/flatcircle/LiveDataHelper/blob/master/app/src/main/java/io/flatcircle/livedatahelperexample/MainActivity.kt#L34) |
| ViewHelperUtil.getDpfromPx() | Vice versa | [Example](https://github.com/flatcircle/LiveDataHelper/blob/master/app/src/main/java/io/flatcircle/livedatahelperexample/MainActivity.kt#L34) |
| ViewHelperUtil.showShareDialog() | Opens a generic "share with" dialogue | [Example](https://github.com/flatcircle/LiveDataHelper/blob/master/app/src/main/java/io/flatcircle/livedatahelperexample/MainActivity.kt#L34) |
| ViewHelperUtil.showEmailDialog() | Opens a send-email intent | [Example](https://github.com/flatcircle/LiveDataHelper/blob/master/app/src/main/java/io/flatcircle/livedatahelperexample/MainActivity.kt#L34) |
| ViewHelperUtil.showConfirmation() | Shows a generic confirmation dialog | [Example](https://github.com/flatcircle/LiveDataHelper/blob/master/app/src/main/java/io/flatcircle/livedatahelperexample/MainActivity.kt#L34) |



| Extension  | Description | Example |
| ------------- | ------------- | ------------- |
| view.show() | Makes the View go GONE | [Example](https://github.com/flatcircle/LiveDataHelper/blob/master/app/src/main/java/io/flatcircle/livedatahelperexample/MainActivity.kt#L34)  |
| view.hide() | Makes the View go VISIBLE | [Example](https://github.com/flatcircle/LiveDataHelper/blob/master/app/src/main/java/io/flatcircle/livedatahelperexample/MainActivity.kt#L34)  |
