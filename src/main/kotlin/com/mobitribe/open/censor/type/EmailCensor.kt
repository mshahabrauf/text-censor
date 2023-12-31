package com.mobitribe.open.censor.type

class EmailCensor : Censor() {

    var emailPattern = "([a-zA-Z0-9._-]+\\s*@[ a-zA-Z0-9._-]+\\.\\s*[a-zA-Z0-9_-]+)"
    override fun getCensoredText(text: String): String {
        return Regex(emailPattern).replace(text, placeHolder)
    }

    override fun changePattern(pattern: String): EmailCensor {
        this.emailPattern = pattern
        return this
    }
}