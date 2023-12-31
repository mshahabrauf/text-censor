package com.mobitribe.open.censor.type

class PhoneCensor : Censor() {

    var phoneRegex = "^((\\+0?1\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4})|((\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4})|(\\d{3}[\\s.-]?\\d{4})|(\\d{3})|(\\d{4})\$"
    override fun getCensoredText(text: String): String {
        return Regex(phoneRegex).replace(text, placeHolder)
    }

    override fun changePattern(pattern: String): Censor {
        this.phoneRegex = pattern
        return this
    }
}