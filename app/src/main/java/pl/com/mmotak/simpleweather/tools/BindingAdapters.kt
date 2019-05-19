package pl.com.mmotak.simpleweather.tools

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import arrow.core.Option
import arrow.core.getOrElse
import com.bumptech.glide.Glide


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

@BindingAdapter("optionText")
fun <T> TextView.setOptionalString(option: Option<T>?) {
    option?.let {
        if (it.isEmpty()) {
            visibility = View.GONE
        } else {
            visibility = View.VISIBLE
            text = it.getOrElse { "" }.toString()
        }
    }
}

@BindingAdapter("optionPng")
fun ImageView.setImage(option: Option<String>?) {
    option?.let {
        val name: String = it.getOrElse { "" }
        if (name.isNotEmpty()) {
            val url = "https://openweathermap.org/img/w/$name.png"
            Log.d("Weather", url)
            Glide.with(this)
                .load(url)
                .into(this)
            visibility = View.VISIBLE
        } else {
            visibility = View.GONE
        }
    }
}
