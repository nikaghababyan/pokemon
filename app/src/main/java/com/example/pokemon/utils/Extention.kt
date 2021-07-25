package com.example.pokemon.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun Context.hasNetworkAvailable(): Boolean {
    val service = Context.CONNECTIVITY_SERVICE
    val manager = this.getSystemService(service) as ConnectivityManager?
    val network = manager?.activeNetworkInfo

    return (network != null)
}

