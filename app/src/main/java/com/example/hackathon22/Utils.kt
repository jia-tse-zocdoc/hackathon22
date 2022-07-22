package com.example.hackathon22

import android.graphics.Color
import java.util.*

class Utils {
    companion object {

        private val mRandom: Random = Random(System.currentTimeMillis())

        fun generateRandomColor(): Int {
            // This is the base color which will be mixed with the generated one
            val baseColor: Int = Color.WHITE
            val baseRed: Int = Color.red(baseColor)
            val baseGreen: Int = Color.green(baseColor)
            val baseBlue: Int = Color.blue(baseColor)
            val red: Int = (baseRed + mRandom.nextInt(256)) / 2
            val green: Int = (baseGreen + mRandom.nextInt(256)) / 2
            val blue: Int = (baseBlue + mRandom.nextInt(256)) / 2
            return Color.rgb(red, green, blue)
        }
    }
}