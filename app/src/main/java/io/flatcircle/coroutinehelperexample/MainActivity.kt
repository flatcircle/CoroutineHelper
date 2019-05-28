package io.flatcircle.coroutinehelperexample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import io.flatcircle.coroutinehelper.ApiResult
import io.flatcircle.coroutinehelper.Failure
import io.flatcircle.coroutinehelper.Success
import io.flatcircle.coroutinehelper.onFail
import io.flatcircle.coroutinehelper.onSuccess
import io.flatcircle.coroutinehelper.then
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            launchLongRunningOperation()
        }

        launchLongRunningOperation()
    }

    fun launchLongRunningOperation() {
        // Not the right way to launch a coroutine, but useful for demonstrative purposes
        GlobalScope.launch(Dispatchers.Main) {

            longRunningOperation(100) then { apiResult ->
                if (apiResult.value > 80) {
                    Failure(IllegalStateException("`then` can throw an exception and pass it down the line"))
                } else {
                    apiResult
                }
            } onSuccess { value ->
                textView.text = "Result = $value"
            } onFail { throwable ->
                textView.text = "A failure happened, with details $throwable"
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    suspend fun longRunningOperation(input: Int): ApiResult<Int> {
        delay(5000)
        val value = Random.nextInt(0, input*2)

        return if (value > input) {
            Success(value)
        } else {
            Failure(SocketTimeoutException("This could be any exception. Faking an api timeout here."))
        }
    }
}
