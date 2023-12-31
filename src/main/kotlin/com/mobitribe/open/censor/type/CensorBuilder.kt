package com.mobitribe.open.censor.type

object CensorBuilder {
    val censors = ArrayList<Censor>()

    fun addCensors(vararg censor: Censor): CensorBuilder {
        censors.addAll(censor)
        return this
    }

    fun getCensoredText(text: String): String {
        var textNeedsToBeCensored = text
        censors.forEach {
            textNeedsToBeCensored = it.getCensoredText(textNeedsToBeCensored)
        }
        return textNeedsToBeCensored
    }
}