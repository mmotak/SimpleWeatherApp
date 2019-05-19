package pl.com.mmotak.simpleweather.tools.coroutines

import android.util.Log
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class BackgroundScope : CoroutineScope {
    private lateinit var job : Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO + CoroutineName("background_worker")

    fun onStart() {
        Log.d("Weather", "BackgroundScope onStart $this")
        job = Job()
    }

    fun onStop() {
        Log.d("Weather", "BackgroundScope onStop $this")
        job.cancel()
    }
}