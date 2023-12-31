package com.mobitribe.open.censor.type

class DomainCensor : Censor() {

    var domainPattern = "([ a-zA-Z0-9._-]+\\.\\s*[a-zA-Z0-9_-]+)"
    override fun getCensoredText(text: String): String {
        return Regex(domainPattern).replace(text, placeHolder)
    }

    override fun changePattern(pattern: String): Censor {
        this.domainPattern = pattern
        return this
    }
}