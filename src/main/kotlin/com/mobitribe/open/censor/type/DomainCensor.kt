package com.mobitribe.open.censor.type

class DomainCensor : Censor() {

    var domainPattern = "(?:(?:https?|ftp):\\/\\/)?[\\w/\\-?=%.]+\\.[\\w/\\-&?=%.]+"
    override fun getCensoredText(text: String): String {
        return Regex(domainPattern).replace(text, placeHolder)
    }

    override fun changePattern(pattern: String): Censor {
        this.domainPattern = pattern
        return this
    }
}