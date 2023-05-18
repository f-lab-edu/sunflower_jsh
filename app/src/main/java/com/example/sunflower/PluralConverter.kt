package com.example.sunflower

import android.icu.text.MessageFormat
import android.icu.util.ULocale

object PluralConverter {
    private const val PLURAL_PATTERN = "{count, plural, " +
        "=1{# day}" +
        "other{# days}}"

    fun dayToPlural(count: Int): String {
        val messageFormat = MessageFormat(
            PLURAL_PATTERN,
            ULocale.ENGLISH,
        )
        val arguments = mutableMapOf<String, Int>()
        arguments["count"] = count
        return messageFormat.format(arguments)
    }
}
