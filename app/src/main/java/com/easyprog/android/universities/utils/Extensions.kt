package com.easyprog.android.universities.utils

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.easyprog.android.universities.R
import com.google.android.material.snackbar.Snackbar

fun <T> ImageView.load(image: T) {
    Glide.with(this).load(image).fitCenter().centerCrop().into(this)
}

fun AppCompatActivity.openFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .addToBackStack(null)
        .commit()
}

fun String.fromHtmlToString(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}

fun showSnackbar(view: View, message: Int) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}