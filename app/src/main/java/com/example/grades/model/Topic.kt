package com.example.grades.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val textRes: Int,
    val availableCourses: Int,
    @DrawableRes val imageRes: Int
)
