package com.example.watchlist.datamodel

import android.graphics.drawable.Drawable

data class Layout(
    val icon: Drawable,
    val name: String,
    var check: Boolean
)