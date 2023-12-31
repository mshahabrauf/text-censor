package com.mobitribe.open.censor.type

import java.util.regex.Pattern

abstract class Censor {

    protected var placeHolder: String = "[placeholder]"
    abstract fun getCensoredText(text: String): String
    abstract fun changePattern(pattern: String): Censor

    fun setPlaceholder(placeholder: String): Censor {
        this.placeHolder = placeholder
        return this
    }
}