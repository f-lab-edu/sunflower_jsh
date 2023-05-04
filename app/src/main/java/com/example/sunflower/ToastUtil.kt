package com.example.sunflower

import android.content.Context
import android.widget.Toast

object ToastUtil {
    fun addPlantToast(context: Context) {
        Toast.makeText(context, R.string.addToastMsg, Toast.LENGTH_SHORT).show()
    }
}
