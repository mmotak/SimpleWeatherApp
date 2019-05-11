package pl.com.mmotak.simpleweather.tools

import android.view.View
import androidx.databinding.BindingAdapter


@BindingAdapter("isLoading")
fun View.setLoading(isloading: Boolean?) {
    visibility = when (isloading) {
        true -> View.VISIBLE
        null, false -> View.GONE
    }
}

@BindingAdapter("isNotLoading")
fun View.setNotLoading(isNotLoading: Boolean?) {
    visibility = when (isNotLoading) {
        null, true -> View.GONE
        false -> View.VISIBLE
    }
}