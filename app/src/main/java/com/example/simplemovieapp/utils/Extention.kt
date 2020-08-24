package com.example.simplemovieapp.utils

fun convertDurationToHour(duration: Int): String? {
    val hours = duration / 3600
    val remainder = duration - hours * 3600
    val mins = remainder / 60
    return "$hours h $mins m"
}